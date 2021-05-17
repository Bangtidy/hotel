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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdayTime = dateFormat.format(new Date());
        Date nowDate = dateFormat.parse(nowdayTime);
        System.out.println(nowDate);
    }

}
