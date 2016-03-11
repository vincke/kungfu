package org.kungfu.util;

/**
 *  created by xiaofeixiang on 2016/1/11 20:39:18
 */
public class MySqlKit {
	
	/**
	 * return need quotes by mysql data type.
	 * 1. column of mysql type: varchar, char, enum, set, text, tinytext, mediumtext, longtext  need quotes
	 * 2. column of mysql type: int, integer, tinyint(n) n > 1, smallint, mediumint, long, double need't quotes
	 */
	public static boolean needQuotes(String dataType) {
		return dataType.contains("char") || dataType.contains("text") || dataType.contains("enum")
				|| dataType.contains("set") || dataType.contains("time") || dataType.contains("date") ? true : false;
	}

}
