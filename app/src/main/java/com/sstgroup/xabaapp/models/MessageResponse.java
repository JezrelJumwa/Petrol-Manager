package com.sstgroup.xabaapp.models;


import com.google.gson.annotations.SerializedName;

import static com.sstgroup.xabaapp.utils.Constants.STATUS;

public class MessageResponse {

    @SerializedName(STATUS)
    private String status;

    public String getStatus() {
        return status;
    }
}
