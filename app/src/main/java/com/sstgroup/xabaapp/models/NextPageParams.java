package com.sstgroup.xabaapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rosenstoyanov on 6/15/17.
 */

public class NextPageParams {
    @SerializedName("from_id")
    private Integer fromId;

    public Integer getFromId() {
        return fromId;
    }
}
