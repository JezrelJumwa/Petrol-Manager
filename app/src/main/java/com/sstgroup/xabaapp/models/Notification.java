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
    private Long id;
    private String type;
    private String description;
    private String text;
    private String subtext;
    @SerializedName("created_at")
    private Date date;
    @Generated(hash = 1357746584)
    public Notification(Long id, String type, String description, String text,
            String subtext, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.text = text;
        this.subtext = subtext;
        this.date = date;
    }
    @Generated(hash = 1855225820)
    public Notification() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
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
