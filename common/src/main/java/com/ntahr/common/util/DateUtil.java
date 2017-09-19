package com.ntahr.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
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

    public static void main(String... args) {
        LocalDate localDate = LocalDate.now();
        log("LocalDate " + localDate);

        LocalTime localTime = LocalTime.now();
        log("LocalTime " + localTime);

        LocalDate utcDate = LocalDate.now(Clock.systemUTC());
        log("UTCDate " + utcDate);

        LocalTime nowInUtc = LocalTime.now(Clock.systemUTC());
        log("UTCTime " + nowInUtc);

        log("TimeZone " + Clock.systemDefaultZone());
        // log("UTCTime "+nowInUtc.);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        log("Current Time Millis " + new Date().getTime());
        try {
            log("Current Time Millis " + getCalendar("14-09-2008").getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void log(String logData) {
        System.out.println(">> " + logData);
    }

}
