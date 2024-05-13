package com.warehouse.clustereddata.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeUtil {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String FRIENDLY_DATE_TIME_PATTERN = "MMM/dd/yyyy HH:mm:ss";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private DateTimeUtil() {

    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(FRIENDLY_DATE_TIME_PATTERN));
    }
    
    public static boolean validDateTime(String dateTime) {
		try{
			LocalDateTime.parse(dateTime, FORMATTER);
		}catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}	
}