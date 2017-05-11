package com.sstgroup.xabaapp.network.retrofit;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.sstgroup.xabaapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import timber.log.Timber;

/**
 * Created by julianlubenov on 5/11/17.
 */

public class NetworkRetrofitServiceCreator {
    private static NetworkRetrofitService apiService;
    private static RestAdapter adapter;
    private static Context context;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static RestAdapter getRestAdapter(Context context) {
        if (adapter == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();

            RestAdapter.Builder adapterBuilder = new RestAdapter.Builder()
                    .setEndpoint(NetworkConstants.API_SERVICE_URL)
                    .setClient(new OkClient(getClient()))
                    .setConverter(new GsonConverter(gsonBuilder
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .setDateFormat(DATE_FORMAT)
                            .create()));

            if (BuildConfig.DEBUG) {
                adapterBuilder.setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String s) {
                        Timber.d(s);
                    }
                }).setLogLevel(RestAdapter.LogLevel.FULL);
            } else {
                adapterBuilder.setLogLevel(RestAdapter.LogLevel.NONE);
            }
            adapter = adapterBuilder.build();
        }
        return adapter;
    }

    public static NetworkRetrofitService createService(Context context) {
        if (apiService == null) {
            apiService = getRestAdapter(context).create(NetworkRetrofitService.class);
        }
        return apiService;
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(15 * 10000, TimeUnit.SECONDS);
        client.setReadTimeout(15 * 10000, TimeUnit.SECONDS);
        return client;
    }

}
