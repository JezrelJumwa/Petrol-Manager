package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

public class NextPageParams {
    @SerializedName("from_id")
    private Integer fromId;

    public Integer getFromId() {
        return fromId;
    }
}
