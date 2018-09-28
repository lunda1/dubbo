package com.liupeng.learning.basic;

import java.util.Calendar;
import java.util.TimeZone;

public class TestCalendar {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());

        calendar.add(Calendar.HOUR_OF_DAY,-3);
        System.out.println(calendar.getTime());

        System.out.println(calendar.getTimeZone());

        System.out.println(TimeZone.getDefault());
    }

}
