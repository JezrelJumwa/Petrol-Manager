package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

import static com.sstgroup.xabaapp.utils.Constants.BODY;
import static com.sstgroup.xabaapp.utils.Constants.STATUS;

public class XabaResponse<T> {

    @SerializedName(STATUS)
    private String status;

    @SerializedName(BODY)
    private T body;

    public String getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }
}
