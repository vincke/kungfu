package org.kungfu.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;

/**
 * created by xiaofeixiang on 2016/1/10 00:28:18.
 */
public class JFinalModelGenerator extends ModelGenerator {
	protected String packageTemplate =
			"package %s.%s;%n%n";
	protected String importTemplate =
			"import %s.%s.%s;%n%n";

	public JFinalModelGenerator(String modelPackageName, String baseModelPackageName, String modelOutputDir) {
		super(modelPackageName, baseModelPackageName, modelOutputDir);
	}
	
	protected void genModelContent(TableMeta tableMeta) {
		StringBuilder ret = new StringBuilder();
		genPackage(tableMeta, ret);
		genImport(tableMeta, ret);
		genClassDefine(tableMeta, ret);
		genDao(tableMeta, ret);
		ret.append(String.format("}%n"));
		tableMeta.modelContent = ret.toString();
	}
	
	protected void genPackage(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(packageTemplate, modelPackageName, tableMeta.name.toLowerCase().replaceAll("_", "")));
	}
	
	protected void genImport(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(importTemplate, baseModelPackageName, tableMeta.name.toLowerCase().replaceAll("_", ""), tableMeta.baseModelName));
	}

	/**
	 * 若 model 文件存在，则不生成，以免覆盖用户手写的代码
	 */
	protected void wirtToFile(TableMeta tableMeta) throws IOException {
		File dir = new File(modelOutputDir + File.separator + tableMeta.name.toLowerCase().replaceAll("_", "") );
		if (!dir.exists())
			dir.mkdirs();
		
		String target = modelOutputDir + File.separator + tableMeta.name.toLowerCase().replaceAll("_", "") + File.separator + tableMeta.modelName + ".java";
		
		File file = new File(target);
		if (file.exists()) {
			return ;	// 若 Model 存在，不覆盖
		}
		
		FileWriter fw = new FileWriter(file);
		try {
			fw.write(tableMeta.modelContent);
		}
		finally {
			fw.close();
		}
	}
}
