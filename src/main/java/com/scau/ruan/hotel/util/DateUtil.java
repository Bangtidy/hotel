package com.scau.ruan.hotel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //计算时间差
    public static Integer getDistanceTimes(Date one, Date two) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Long time1 = one.getTime();
        Long time2 = two.getTime();
        Long diff ;
        if(time1<time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        Long day = diff / (24 * 60 * 60 * 1000);
        Long hour = (diff / (60 * 60 * 1000) - day * 24)+day*24 ;
        Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        if(min >0){
            hour++;
        }

        //System.out.println(hour);
        return hour.intValue();
    }
}
