package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("currency_id")
    private Long currencyId;
    @SerializedName("code")
    private String code;

    public Long getCurrencyId() {
        return currencyId;
    }

    public String getCode() {
        return code;
    }
}
