package com.clh.iot.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ClhDateUtil {
    private static String UTC_PATTERN="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static String DATETIME_PATTERN="yyyy-MM-dd HH:mm:ss";

    private static final SimpleDateFormat UTC_FORMAT=new SimpleDateFormat(UTC_PATTERN);
    private static final SimpleDateFormat DATETIME_FORMAT=new SimpleDateFormat(DATETIME_PATTERN);
    public static void main(String[] args) {
//        Date date = new Date();
//        String xxx=DateToUTC(date);
//        System.out.println(xxx);

    }

    /**
     * 将Date日期转为String类型UTC String时间
     * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     * @param date
     * @return
     */
    public static String DateToUTC(Date date){
            return UTC_FORMAT.format(date);
    }

    /**
     * 将UTC String 转为Date
     * @param UTC
     * @return
     */
    public static Date UTCToDate(String UTC){
        SimpleDateFormat utcFormater = new SimpleDateFormat(UTC_PATTERN);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(UTC);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return gpsUTCDate;
    }

    /**
     * 将Date 转为Datetime String
     * Thu May 28 02:56:11 CST 2020
     * @param date
     * @return
     */
    public static String DateToDateTime(Date date){
        return DATETIME_FORMAT.format(date);
    }
}
