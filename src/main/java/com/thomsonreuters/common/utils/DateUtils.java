package com.thomsonreuters.common.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * DateUtils extends org.apache.commons.lang.time.DateUtils
 * 
 * @author
 * @version
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
			"yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * getCurrentDateStr. format: yyyy-MM-dd
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * getCurrentDateStr. format: yyyy-MM-dd, pattern: "yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * getCurrentDateStr. default format: yyyy-MM-dd, pattern: "yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * getCurrentDateStr. convert format: yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * getCurrentTimeStr. format: HH:mm:ss
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * getCurrentDateTimeStr. format: yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * getCurrentYearStr. format: yyyy
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * getCurrentMonthStr. format: MM
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * getCurrentDayStr. format: dd
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * getCurrentWeekStr. format: E 
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * StringToDate 
	 * { "yyyy-MM-dd", 
	 * "yyyy-MM-dd HH:mm:ss", 
	 * "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", 
	 * "yyyy/MM/dd HH:mm:ss", 
	 * "yyyy/MM/dd HH:mm", 
	 * "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", 
	 * "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * getPastDays
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * getPastHours
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * getPastMinutes
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * formatDateTime format: day,hour:minute:second.millisecond
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60
				* 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
				+ sss;
	}

	/**
	 * getDistanceOfTwoDate
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
}
