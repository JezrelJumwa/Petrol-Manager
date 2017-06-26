package com.sstgroup.xabaapp.utils;

import com.sstgroup.xabaapp.models.DatePeriod;

import java.util.Calendar;
import java.util.Date;

public class DatePeriodUtils {

    public static DatePeriod getPeriod(int period) {
        if (period == Constants.COMMISSIONS_PERIOD_TODAY) {
            return getTodayPeriod();
        } else if (period == Constants.COMMISSIONS_PERIOD_LAST_WEEK) {
            return getLast7DaysPeriod();
        } else if (period == Constants.COMMISSIONS_PERIOD_LAST_30_DAYS) {
            return getLast30DaysPeriod();
        }

        throw new IllegalStateException("Invalid period type");
    }

    private static DatePeriod getTodayPeriod() {
        Date toDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 1);
        Date fromDate = calendar.getTime();

        return new DatePeriod(fromDate, toDate);
    }

    private static DatePeriod getLast30DaysPeriod() {
        Date toDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        Date fromDate = calendar.getTime();

        return new DatePeriod(fromDate, toDate);
    }

    private static DatePeriod getLast7DaysPeriod() {
        Date toDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date fromDate = calendar.getTime();

        return new DatePeriod(fromDate, toDate);
    }
}
