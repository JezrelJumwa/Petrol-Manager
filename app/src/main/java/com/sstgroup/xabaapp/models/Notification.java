package com.sstgroup.xabaapp.models;

import java.util.Date;

/**
 * Created by rosenstoyanov on 6/13/17.
 */

public class Notification {
    public static final int PAY_TYPE = 1;
    public static final int VALIDATED_TYPE = 2;

    private int type;
    private String text;
    private String name;
    private Date date;

    public Notification(int type, String text, String name, Date date) {
        this.type = type;
        this.text = text;
        this.name = name;
        this.date = date;
    }


    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
