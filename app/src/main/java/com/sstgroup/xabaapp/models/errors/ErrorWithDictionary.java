package com.sstgroup.xabaapp.models.errors;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
        private Integer code;
        private String message;

        public ArrayList<String> getOldPin() {
            return oldPin;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
