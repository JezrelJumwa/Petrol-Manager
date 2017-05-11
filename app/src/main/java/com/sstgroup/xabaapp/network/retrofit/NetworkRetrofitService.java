package com.sstgroup.xabaapp.network.retrofit;

import com.sstgroup.xabaapp.network.retrofit.objects.CountriesRetrofitResponse;
import com.sstgroup.xabaapp.network.retrofit.objects.IndustriesRetrofitResponse;
import com.sstgroup.xabaapp.network.retrofit.objects.ProfileRetrofitResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by julianlubenov on 5/10/17.
 */

public interface NetworkRetrofitService {

    @POST(NetworkConstants.LoginPath)
    public void login(@Path("i18n") String i18n, @Query("pin") String pin, @Query("national_id") String phone, Callback<ProfileRetrofitResponse> callback);

    @GET(NetworkConstants.CountriesPath)
    public void countries(@Path("i18n") String i18n, @Query("hash") String hash, Callback<CountriesRetrofitResponse> callback);

    @GET(NetworkConstants.IndustriesPath)
    public void industries(@Path("i18n") String i18n, @Query("hash") String hash, Callback<IndustriesRetrofitResponse> callback);
}
