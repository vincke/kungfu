package org.kungfu.gen;

import java.util.List;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.generator.MappingKitGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;

/**
 * created by xiaofeixiang on 2016/1/10 00:28:18.
 */
public class JFinalMappingKitGenerator extends MappingKitGenerator {
	
	protected String importModelTemplate =
			"import %s.%s.%s;%n";
	protected String importTemplate =
			"%nimport com.jfinal.plugin.activerecord.ActiveRecordPlugin;%n%n";
	
	protected String modelPackageName;

	public JFinalMappingKitGenerator(String mappingKitPackageName, String mappingKitOutputDir, String modelPackageName) {
		super(mappingKitPackageName, mappingKitOutputDir);
		this.modelPackageName = modelPackageName;
	}
	
	public void setModelPackageName(String modelPackageName) {
		if (StrKit.notBlank(modelPackageName))
			this.modelPackageName = modelPackageName;
	}
	
	public void generate(List<TableMeta> tableMetas) {
		System.out.println("Generate MappingKit file ...");
		StringBuilder ret = new StringBuilder();
		genPackage(ret);
		for (TableMeta tableMeta : tableMetas) 
			genModelImport(tableMeta, ret);
		genImport(ret);
		genClassDefine(ret);
		genMappingMethod(tableMetas, ret);
		ret.append(String.format("}%n%n"));
		wirtToFile(ret);
	}
	
	protected void genImport(StringBuilder ret) {
		ret.append(String.format(importTemplate));
	}
	
	protected void genModelImport(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(importModelTemplate, modelPackageName, tableMeta.name.toLowerCase().replaceAll("_", ""), tableMeta.modelName));
	}

}
