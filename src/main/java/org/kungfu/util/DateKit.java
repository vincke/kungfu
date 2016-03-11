package org.kungfu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by yangfq on 15/11/19.
 */
public class DateKit {

    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_ONLY_DATE = "yyyyMMdd";
    public static final String FORMAT_DATE_ZN = "yyyy年MM月dd";

    public static String formatDateTime(Date date) {
        if(date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }
    
    public static String formatDate(Date date, String format) {
        if(date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }
    
    public static String formatOnlyDate(Date date) {
        if(date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_ONLY_DATE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }


    public static String formatDateTime(Date date, String style) {
        if(date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * 字符串转时间
     * @param dateString
     * @param style
     * @return
     */
    public static Date string2Date(String dateString, String style) {
        if (StrKit.isBlank(dateString)) return null;
        Date date = new Date();
        SimpleDateFormat strToDate = new SimpleDateFormat(style);
        try {
            date = strToDate.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断传入的时间是否在当前时间之后，返回boolean值
     * true: 过期
     * false: 还没过期
     * @param date
     * @return
     */
    public static boolean isExpire(Date date) {
        if(date.before(new Date())) return true;
        return false;
    }

    public static Date getHourAfter(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.roll(11, hour);
        return calendar.getTime();
    }

    public static Date getHourBefore(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.roll(11, -hour);
        return calendar.getTime();
    }

    public static Date getDateBefore(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.roll(5, -day);
        return calendar.getTime();
    }

    public static Date getDateAfter(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(5, now.get(5) + day);
        return now.getTime();
    }

    public static Date getMinuteAfter(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Date getMinuteBefore(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }
    
    public static String plusYear(String date) {
    	return (Integer.parseInt(date.substring(0,4)) + 1) + date.substring(4);
    }
    
    public static int getMonthSpace(String date1, String date2) {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_ONLY_DATE);

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		try {
			c1.setTime(sdf.parse(date1));
			c2.setTime(sdf.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    int years = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

		int months = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		
		result = 12 * Math.abs(years) + Math.abs(months);

		return result;

	}
    
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static void main(String[] args) {
		System.out.println(getMonthSpace("20140920", "20160328"));
		
		System.out.println(getWeekOfDate(new Date()));
	}
}
