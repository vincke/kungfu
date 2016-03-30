package org.kungfu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangfq on 15/11/19.
 */
public class StrKit {
	public static String firstUpperCamelCase(String tableName) {
		return tableName.substring(0, 1).toUpperCase() + format(tableName.substring(1));
	}
	
	public static String toCamelCase(String tableName) {
		return format(tableName);
	}
	
	public static String toLowerCaseAll(String tableName) {
		return format(tableName).toLowerCase();
	}
	
	private static String format(String field) {
		if (field == null)
			return null;
		String[] strs = field.split("_");
		field = "";
		for (int m = 0, length = strs.length; m < length; m++) {
			if (m > 0) {
				String tempStr = strs[m].toLowerCase();
				tempStr = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1, tempStr.length());
				field += tempStr;
			} 
			else {
				field += strs[m].toLowerCase();
			}
		}
		return field;
	}
	
	public static boolean isNull(String str) {
		return str == null;
	}
	
	public static boolean notNull(String str) {
		return str != null;
	}
	
	/**
	 * 字符串为 null 或者为  "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * 字符串不为 null 而且不为  "" 时返回 true
	 */
	public static boolean notBlank(String str) {
		return str != null && !"".equals(str.trim());
	}
	
	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}
	
	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}
	
	public static String join(String[] stringArray) {
		StringBuilder sb = new StringBuilder();
		for (String s : stringArray)
			sb.append(s);
		return sb.toString();
	}
	
	public static String join(String[] stringArray, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<stringArray.length; i++) {
			if (i>0)
				sb.append(separator);
			sb.append(stringArray[i]);
		}
		return sb.toString();
	}
	
	public static boolean hasDigit(String content) {
		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(content);
		if (m.matches())
			flag = true;
		return flag;
	}
	
}
