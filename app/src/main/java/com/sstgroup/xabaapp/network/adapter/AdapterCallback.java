package com.sstgroup.xabaapp.network.adapter;

/**
 * Created by julianlubenov on 6/5/17.
 */

public interface AdapterCallback<T> {
    void onResponse(T object, String jsonBody, int httpCode);

    void onFailure(String message, int httpCode, Throwable t);

}
