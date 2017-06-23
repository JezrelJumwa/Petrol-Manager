package com.sstgroup.xabaapp.models.errors;

public class XabaError {
    private String status;
    private String errorMessage;
    private Integer code;

    public XabaError(String status, String errorMessage, Integer code) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getCode() {
        return code;
    }
}
