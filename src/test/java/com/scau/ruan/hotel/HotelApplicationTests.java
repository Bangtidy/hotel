package com.scau.ruan.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class HotelApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        Date one = new Date();
        Date two = new Date();

        String workingHour=String.valueOf((two.getTime()-one.getTime())/(1000 * 60 * 60));
        System.out.println(workingHour);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff ;
        if(time1<time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        hour = day*24 + hour + min>0 ? 1:0;
        System.out.println(hour);
    }

    @Test
    //计算时间差
    public static long[] getDistanceTimes(Date one, Date two) {
        one = new Date();
        two = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff ;
        if(time1<time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        long[] times = {day, hour, min, sec};
        return times;
    }
}
