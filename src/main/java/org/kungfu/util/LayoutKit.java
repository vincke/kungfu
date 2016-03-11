package org.kungfu.util;

/**
 * 排版工具类
 * created by xiaofeixiang on 2016/1/11 17:08:18
 */
public class LayoutKit {
	
	public static boolean isFieldBegin(String displayNo) {
		// 1-1,1-2
		// 1-2-1,1-2-2
		if (!displayNo.contains("-")) {
			return true;
		}
		
		if (displayNo.substring(displayNo.indexOf('-') + 1, displayNo.indexOf('-') + 2).equals("1")) {
			if (getCharNumber(displayNo, "-") > 1) {
				if (displayNo.endsWith("1")) {
					return true;
				}
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public static boolean isFieldEnd(String displayNo) {
		
		if (!displayNo.contains("-")) {
			return true;
		}
		
		if (displayNo.substring(displayNo.indexOf('-') + 1, displayNo.indexOf('-') + 2).equals("2")) {
			if (getCharNumber(displayNo, "-") > 1) {
				if (displayNo.endsWith("2")) {
					return true;
				}
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public static int getCharNumber(String displayNo, String ch){
		int num = 0;
		for (int i = 0; i < displayNo.length(); i++) {
			String c = displayNo.substring(i , i + 1);
			if(c.equals(ch)) {
				num ++ ;
			}
		}
		return num;
	}

}
