package com.sstgroup.xabaapp.network.mock;

import com.sstgroup.xabaapp.network.adapter.objects.ProfileModel;

import java.io.IOError;
import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by julianlubenov on 5/10/17.
 */

public class NetworkMockService {
    public void login(String pin, String nationalId, Callback<ProfileModel> callback) {
        callback.failure(RetrofitError.networkError("login", new IOException()));
    }
}
