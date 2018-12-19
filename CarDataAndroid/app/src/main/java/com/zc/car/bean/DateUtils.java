package com.zc.car.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZC on 2017/4/1.
 */

public class DateUtils {
    public static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");


    public static String getDateTime() {
        return DATE_FORMAT_DATETIME.format(System.currentTimeMillis());
    }

    // /**
    //  * 得到今天的时间
    //  *
    //  * @return 字符串 yyyy-MM-dd
    //  */
    // public static String getStringToday() {
    //     Date currentTime = new Date();
    //     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //     String dateString = formatter.format(currentTime);
    //     return dateString;
    // }


    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = DATE_FORMAT_DATETIME.format(currentTime);
        return dateString;
    }

}
