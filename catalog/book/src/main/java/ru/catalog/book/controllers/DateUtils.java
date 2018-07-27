package ru.catalog.book.controllers;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class DateUtils {
    private static String formatDate = "dd.MM.yyyy";

    public static Date getDateFromString(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
        return formatter.parse(dateString);
    }

    public static String dateToStr(Date date){
        Format formatter = new SimpleDateFormat(formatDate);
        return formatter.format(date);
    }
}
