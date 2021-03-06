package com.sstgroup.xabaapp.network.retrofit.objects.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by julianlubenov on 5/11/17.
 */

public abstract class RetrofitListResponse <T> implements RetrofitModelListResponse<T> {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;
}
