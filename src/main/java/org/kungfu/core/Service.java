package org.kungfu.core;

import java.util.List;

import org.kungfu.sql.DynamicSQL;
import org.kungfu.util.ModelKit;
import org.kungfu.util.MySqlKit;
import org.kungfu.util.StrKit;

import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * created by xiaofeixiang on 2016/1/9 15:28:18.
 */
public class Service {
	private static final String columnSql = "SELECT COLUMN_NAME as columnName, DATA_TYPE as dataType FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA = ? and TABLE_NAME = ? ";
	
	public static Model<?> json2Model(String jsonStr, Class<?> clazz) {
		return (Model<?>) Json.getJson().parse(jsonStr, clazz);
	}

	public static  String model2Json(Model<?> model) {
		return Json.getJson().toJson(model);
	}
	
	public boolean save(Model<?> model) {
		return model.save();
	}
	
	public boolean update(Model<?> model) {
		return model.update();
	}
	
	public int batchSave(List<Model<?>> list) {
		return Db.batchSave(list, list.size()).length;
	}
	
	public int batchUpdate(List<Model<?>> list) {
		return Db.batchUpdate(list, list.size()).length;
	}
	
	public int saveOrUpdate(List<Model<?>> list, boolean flag) {
		if (flag) {
			return Db.batchUpdate(list, list.size()).length;
		}
		else {
			return Db.batchSave(list, list.size()).length;
		}
	}
	
	protected static boolean isExist(String sql, Object... params) {
		// sql : select id from table_name where  ...
		return Db.queryInt(sql, params) == null ? false : true;
	}
	
	protected static int batchDelete(String tableName, String ids) {

		return Db.update(String.format("delete from %s where id in (%s) ", tableName, ids));
	}
	
	
	
	// 获取动态SQL占位符
	private static String placeholder(String dataType) {
		return MySqlKit.needQuotes(dataType) ? "#" :  "$";
	}
	
	protected static String selectSql(String database, String tableName, Model<?> model) {
		DynamicSQL dsql = new DynamicSQL();
		dsql.append("  select * from " + tableName);
		dsql.append("  where 1= 1 " );
		List<Record> columns = Db.find(columnSql, database, tableName);
		
		for (Record rd : columns) 
			if (StrKit.isNull(rd.getStr("columnName"))) 
				dsql.isNotEmpty(String.format(" and %s = %s ", rd.getStr("columnName"), placeholder(rd.getStr("dataType"))),  model.get(rd.getStr("columnName")));
		
		String sql = dsql.toString();
		dsql.clear();
		
		return sql;
	}
	
	// INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
	protected static String insertSql(String database, String tableName, Model<?> model) {
		DynamicSQL dsql = new DynamicSQL();
		dsql.append("  insert into  " + tableName);
		dsql.append("  ( " );
		List<Record> columns = Db.find(columnSql, database, tableName);
		
		// INSERT INTO table_name (列1, 列2,...) 
		for (Record rd : columns) 
			if (ModelKit.recordNotBlank(model.get(rd.getStr("columnName")))) 
				dsql.isNotEmpty(" $, ",  rd.getStr("columnName"));
		
		dsql.append("  ) values( " );
		// VALUES (值1, 值2,....)
		for (Record rd : columns) 
			if (StrKit.notNull(rd.getStr("columnName"))) 
				dsql.isNotEmpty(String.format(" %s, ", placeholder(rd.getStr("dataType"))),  model.get(rd.getStr("columnName")));
		
		dsql.append("  ) " );
		String sql = dsql.toString();
		sql = sql.replaceAll(", \\)", ")");
		dsql.clear();
		
		return sql;
	}
	
	//UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
		protected static String dynamicUpdate(String database, String tableName, Model<?> model) {
			DynamicSQL dsql = new DynamicSQL();
			dsql.append("  update  " + tableName);
			dsql.append("  set " );
			List<Record> columns = Db.find(columnSql, database, tableName);
			for (Record rd : columns) 
				if (StrKit.notNull(rd.getStr("columnName"))) 
					dsql.isNotEmpty(String.format(" %s = %s , ", rd.getStr("columnName"), placeholder(rd.getStr("dataType"))),  model.get(rd.getStr("columnName")));
			
			String sql = dsql.toString();
			sql = sql.substring(0, sql.lastIndexOf(','));
			dsql.clear();
			
			return sql;
		}
		
		// delete from table_name where id in (1,2,3)
		protected static String deleteBatch(String tableName, String ids) {
			DynamicSQL dsql = new DynamicSQL();
			
			dsql.isNotEmpty(String.format("delete from %s where id in ($) ", tableName), ids);
			
			String sql = dsql.toString();
			dsql.clear();
			
			return sql;
		}
	
}
