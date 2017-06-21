package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity
public class CommissionLog {

    @Id
    @SerializedName("id")
    private Integer id;
    @SerializedName("amount")
    private String amount;
    @SerializedName("currency_id")
    private String currencyId;
    @SerializedName("type")
    private String type;
    @SerializedName("description")
    private String description;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("text")
    private String text;
    @Generated(hash = 189566262)
    public CommissionLog(Integer id, String amount, String currencyId, String type,
            String description, Date createdAt, String text) {
        this.id = id;
        this.amount = amount;
        this.currencyId = currencyId;
        this.type = type;
        this.description = description;
        this.createdAt = createdAt;
        this.text = text;
    }
    @Generated(hash = 346068186)
    public CommissionLog() {
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCurrencyId() {
        return this.currencyId;
    }
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
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
    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }

}
