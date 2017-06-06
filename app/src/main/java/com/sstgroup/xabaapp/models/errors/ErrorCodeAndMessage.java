package com.sstgroup.xabaapp.models.errors;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rosenstoyanov on 6/6/17.
 */

public class ErrorCodeAndMessage {
    @SerializedName("status")
    private String status;
    @SerializedName("errors")
    private Error errors;

    public ErrorCodeAndMessage(String status, Error errors) {
        this.status = status;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public Error getErrors() {
        return errors;
    }

    public static class Error {
        @SerializedName("code")
        private Integer code;
        @SerializedName("message")
        private String message;

        public Error(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
