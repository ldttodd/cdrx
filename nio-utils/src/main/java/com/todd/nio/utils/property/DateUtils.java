package com.todd.nio.utils.property;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *                       
 * @Filename DateUtils.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年6月17日</li>
 *
 */
public final class DateUtils {
	
	private DateUtils() {
	}
	
	/**
	 * yyyy-MM-dd HH:ss
	 */
	public static final String	d1	= "yyyy-MM-dd HH:ss";
	/**
	 * yyyy-MM-dd
	 */
	public static final String	d2	= "yyyy-MM-dd";
	/**
	 * yyyy/MM/dd
	 */
	public static final String	d3	= "yyyy/MM/dd";
	/**
	 * 
	 */
	public static final String	d4	= "yyyy-MM-dd HH:ss:SS";
	
	/**
	 * yyyy-MM-dd HH:ss:SS
	 * @return yyyy-MM-dd HH:ss
	 */
	public static String getZhDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(d1);
		return sdf.format(new Date());
	}
	
	public static String toCustTime(String times) {
		SimpleDateFormat sdf = new SimpleDateFormat(d1);
		return sdf.format(new Date(Long.valueOf(times)).getTime());
	}
	
	
	/**
	 * 
	 * @param format
	 * @return format date
	 */
	public static String getCustFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
		
	}
	
	/**
	 * 
	 * @return System.currentTimeMillis()
	 */
	public static String getTime() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	public static Long getTime(String timeStr) {
		timeStr = (timeStr.split(":")[2].length() > 2) ? timeStr.substring(0, timeStr.length() - 1)
			: timeStr;
		SimpleDateFormat sdf = new SimpleDateFormat(d1);
		try {
			return sdf.parse(timeStr).getTime();
		} catch (ParseException e) {
			return System.currentTimeMillis();
		}
	}
	
	public static Timestamp getMssqlTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Timestamp getMssqlTime(String d) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(d);
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			return new Timestamp(System.currentTimeMillis());
		}
	}
	public static Timestamp getMssqlTimeFull(String d) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = format.parse(d);
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			return new Timestamp(System.currentTimeMillis());
		}
	}
	public static Long getMssqlTimeToLong(String d) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(d);
			return date.getTime();
		} catch (Exception e) {
			return System.currentTimeMillis();
		}
	}
	
}
