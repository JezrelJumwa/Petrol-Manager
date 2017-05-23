package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("value")
    private String value;
    @SerializedName("expires")
    private String expires;

    public String getValue() {
        return value;
    }

    public String getExpires() {
        return expires;
    }
}
