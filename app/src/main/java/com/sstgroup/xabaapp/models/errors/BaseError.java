package com.sstgroup.xabaapp.models.errors;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rosenstoyanov on 6/6/17.
 */

public class BaseError<T> {
    @SerializedName("status")
    private String status;
    @SerializedName("errors")
    private T errors;

    public BaseError(String status, T errors) {
        this.status = status;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public T getErrors() {
        return errors;
    }
}
