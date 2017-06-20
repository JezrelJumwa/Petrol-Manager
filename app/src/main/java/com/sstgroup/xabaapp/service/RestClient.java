package com.sstgroup.xabaapp.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sstgroup.xabaapp.utils.Constants;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static Retrofit retrofit;
    private static XabaService service;

    static {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = gsonBuilder.create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        service = retrofit.create(XabaService.class);
    }

    public static XabaService getService() {
        return service;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
