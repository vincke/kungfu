package org.kungfu.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jfinal.plugin.activerecord.generator.BaseModelGenerator;
import com.jfinal.plugin.activerecord.generator.ColumnMeta;
import com.jfinal.plugin.activerecord.generator.TableMeta;

/**
 * created by xiaofeixiang on 2016/1/10 00:28:18.
 */
public class JFinalBaseModelGenerator extends BaseModelGenerator {
	protected String packageTemplate =
			"package %s.%s;%n%n";

	public JFinalBaseModelGenerator(String baseModelPackageName, String baseModelOutputDir) {
		super(baseModelPackageName, baseModelOutputDir);
	}
	
	protected void genBaseModelContent(TableMeta tableMeta) {
		StringBuilder ret = new StringBuilder();
		genPackage(tableMeta, ret);
		genImport(ret);
		genClassDefine(tableMeta, ret);
		for (ColumnMeta columnMeta : tableMeta.columnMetas) {
			genSetMethodName(columnMeta, ret);
			genGetMethodName(columnMeta, ret);
		}
		ret.append(String.format("}%n"));
		tableMeta.baseModelContent = ret.toString();
	}
	
	protected void genPackage(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(packageTemplate, baseModelPackageName, tableMeta.name.toLowerCase().replaceAll("_", "")));
	}
	
	/**
	 * base model 覆盖写入
	 */
	protected void wirtToFile(TableMeta tableMeta) throws IOException {
		File dir = new File(baseModelOutputDir + File.separator + tableMeta.name.toLowerCase().replaceAll("_", "") );
		if (!dir.exists())
			dir.mkdirs();
		
		String target = baseModelOutputDir + File.separator + tableMeta.name.toLowerCase().replaceAll("_", "") + File.separator + tableMeta.baseModelName + ".java";
		FileWriter fw = new FileWriter(target);
		try {
			fw.write(tableMeta.baseModelContent);
		}
		finally {
			fw.close();
		}
	}

}
