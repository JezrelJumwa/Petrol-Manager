package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Country {

    @SerializedName("country_id")
    private Long countryId;
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("currency_id")
    private Long currencyId;
    @SerializedName("timezone_id")
    private Long timezoneId;
    @SerializedName("counties")
    public ArrayList<County> counties;

    public Long getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public Long getTimezoneId() {
        return timezoneId;
    }

    public ArrayList<County> getCounties() {
        return counties;
    }
}
