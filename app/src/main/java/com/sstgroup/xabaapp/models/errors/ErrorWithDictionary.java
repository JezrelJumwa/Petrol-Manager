package com.sstgroup.xabaapp.models.errors;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rosenstoyanov on 6/22/17.
 */

public class ErrorWithDictionary {
    @SerializedName("status")
    private String status;
    @SerializedName("errors")
    private Error errors;

    public ErrorWithDictionary(String status, Error errors) {
        this.status = status;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public Error getErrors() {
        return errors;
    }

    public class Error {
        @SerializedName("old_pin")
        private ArrayList<String> oldPin;

        public ArrayList<String> getOldPin() {
            return oldPin;
        }
    }
}
