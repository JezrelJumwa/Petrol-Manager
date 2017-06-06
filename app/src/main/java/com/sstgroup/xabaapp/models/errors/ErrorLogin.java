package com.sstgroup.xabaapp.models.errors;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rosenstoyanov on 6/6/17.
 */

public class ErrorLogin {
    private String status;
    @SerializedName("errors")
    private String error;

    public ErrorLogin(String status, String error) {
        this.status = status;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
