package com.sstgroup.xabaapp.models.errors;


import com.google.gson.annotations.SerializedName;

public class ErrorStatusAndError {
    @SerializedName("status")
    private String status;
    @SerializedName("errors")
    private String error;

    public ErrorStatusAndError(String status, String error) {
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
