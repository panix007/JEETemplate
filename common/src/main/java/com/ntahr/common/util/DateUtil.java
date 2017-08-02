package com.ntahr.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static String DEFAULT_DATE_FORMAT = "dd-MM-yyyy"; 

	public static Calendar getCalendar(String date) throws ParseException{
		return getCalendar(date, DEFAULT_DATE_FORMAT);		
	}

	public static Calendar getCalendar(String date, String pattern) throws ParseException{
		Date parsedDate = new SimpleDateFormat(pattern).parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parsedDate);
		return calendar;		
	}

}
