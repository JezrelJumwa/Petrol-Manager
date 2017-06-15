package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * Created by rosenstoyanov on 6/13/17.
 */
@Entity
public class Notification {

    @Id
    private Integer id;
    private String type;
    private String text;
    private String subtext;
    @SerializedName("created_at")
    private Date date;

    @Generated(hash = 1536748666)
    public Notification(Integer id, String type, String text, String subtext,
            Date date) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.subtext = subtext;
        this.date = date;
    }
    @Generated(hash = 1855225820)
    public Notification() {
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getSubtext() {
        return this.subtext;
    }
    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    
}
