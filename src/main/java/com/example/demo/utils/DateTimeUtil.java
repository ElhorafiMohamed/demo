package com.example.demo.utils;

import org.springframework.stereotype.Component;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
public final class DateTimeUtil {
    private static final String TIMEZONE_HEADER_NAME = "Timezone-Val";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String parseDate(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        return formattedDate;
    }

    public static String dateAsString(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static LocalDate StringAsDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }






}
