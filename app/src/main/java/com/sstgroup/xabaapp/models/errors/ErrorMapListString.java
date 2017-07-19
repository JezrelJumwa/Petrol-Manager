package com.sstgroup.xabaapp.models.errors;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class ErrorMapListString {
    private String status;
    @SerializedName("errors")
    private Map<String, ArrayList<String>> error;

    public ErrorMapListString(String status, Map<String, ArrayList<String>> error) {
        this.status = status;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public Map<String, ArrayList<String>> getError() {
        return error;
    }
}
