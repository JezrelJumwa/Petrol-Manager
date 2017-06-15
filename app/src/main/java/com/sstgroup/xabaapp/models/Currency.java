package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Currency {

    @Id
    @SerializedName("currency_id")
    private Long currencyId;
    @SerializedName("code")
    private String code;

    @Generated(hash = 475914759)
    public Currency(Long currencyId, String code) {
        this.currencyId = currencyId;
        this.code = code;
    }

    @Generated(hash = 1387171739)
    public Currency() {
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public String getCode() {
        return code;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
