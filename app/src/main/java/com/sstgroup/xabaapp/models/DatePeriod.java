package com.sstgroup.xabaapp.models;

import java.util.Date;

public class DatePeriod {
    private Date from;
    private Date to;

    public DatePeriod(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
