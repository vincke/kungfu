package org.kungfu.sql;

import java.io.IOException;

public class DynSQLDemo {
	
	public static void main(String[] args) throws IOException {
		DynamicSQL dsql = new DynamicSQL();
		dsql.append("SELECT s.dept_name, s.id, s.name, t.year, t.semester, t.course_id, t.grade  ");
		dsql.append("  FROM student s   ");
		dsql.append("  LEFT JOIN takes t ON s.id = t.id ");
		dsql.append("  where s.id = '1'      ");
		dsql.isNotEmpty(" and t.year = #    ", "2015");
		dsql.isNotEmpty(" and t.month = #    ", "");
		dsql.isEqual(" and t.semester = $  ", 2, 2);

		System.out.println(dsql.getSql());
		System.out.println(dsql.getParams());
		System.out.println(dsql);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		dsql.clear();
		
		dsql.append("SELECT s.dept_name, s.id, s.name, t.year, t.semester, t.course_id, t.grade  ");
		dsql.append("  FROM student s   ");
		dsql.append("  LEFT JOIN takes t ON s.id = t.id ");
		dsql.append("  where s.id = '1'      ");
		dsql.isNotEmpty(" and t.year = #    ", "2015");
		dsql.isNotEmpty(" and t.month = $    ", "");
		dsql.isEqual(" and t.semester = $  ", new Integer(3), new Integer(3));
		dsql.isEqual(" and t.semester2 = $  ", 5, 5);

		System.out.println(dsql.getSql());
		System.out.println(dsql.getParams());
		System.out.println(dsql);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		dsql.clear();
		
		
		String ids = "1, 2, 3, 4, 5";
		//dsql.append("delete from sdt_bdi_std_type ");
		//dsql.isNotEmpty("  where id in ($) ", ids);
		dsql.isNotEmpty(String.format("delete from %s where id in ($) ", "sdt_bdi_std_type"), ids);
		System.out.println(dsql.getSql());
		System.out.println(dsql.getParams());
		System.out.println(dsql);
		
	
	}
}
