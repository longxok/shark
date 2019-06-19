package com.cloudwalk.shark.api.portal.exercise;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static ThreadLocal<DateFormat> dateFormatThreadLocal  = new ThreadLocal<DateFormat>(){

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static  String formatDate(Date date)throws ParseException{
        return dateFormatThreadLocal.get().format(date);
    }

    public static Date parse(String strDate) throws ParseException{
        return dateFormatThreadLocal.get().parse(strDate);
    }
}