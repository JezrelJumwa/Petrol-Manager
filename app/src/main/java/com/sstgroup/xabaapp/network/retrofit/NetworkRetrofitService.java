package com.sstgroup.xabaapp.network.retrofit;

import com.sstgroup.xabaapp.network.retrofit.objects.ProfileRetrofitObject;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by julianlubenov on 5/10/17.
 */

public interface NetworkRetrofitService {

    @POST(NetworkConstants.LoginPath)
    public void login(@Query("pin") String pin, @Query("national_id") String phone, Callback<ProfileRetrofitObject> callback);
}
