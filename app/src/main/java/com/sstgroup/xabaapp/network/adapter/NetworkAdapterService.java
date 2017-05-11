package com.sstgroup.xabaapp.network.adapter;

import android.content.Context;
import android.telecom.Call;

import com.sstgroup.xabaapp.network.adapter.objects.CountryModel;
import com.sstgroup.xabaapp.network.adapter.objects.IndustryModel;
import com.sstgroup.xabaapp.network.adapter.objects.ProfileModel;
import com.sstgroup.xabaapp.network.mock.NetworkMockService;
import com.sstgroup.xabaapp.network.retrofit.NetworkRetrofitService;
import com.sstgroup.xabaapp.network.retrofit.NetworkRetrofitServiceCreator;
import com.sstgroup.xabaapp.network.retrofit.objects.CountriesRetrofitResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by julianlubenov on 5/10/17.
 */

public class NetworkAdapterService {

    private static Context context;
    private static NetworkAdapterService instance;

    private NetworkRetrofitService apiService;
    private NetworkMockService mockService;


    private NetworkAdapterService() {
        apiService = NetworkRetrofitServiceCreator.createService(context);
        mockService = new NetworkMockService();
    }

    public static NetworkAdapterService getInstance(Context context) {
        if (instance == null) {
            NetworkAdapterService.context = context;
            instance = new NetworkAdapterService();
        }
        return instance;
    }

    public void login(String pin, String nationalId, Callback<ProfileModel> callback) {
        mockService.login(pin, nationalId, callback);
    }

    public void countries(String hash, Callback<ArrayList<CountryModel>> callback) {
        mockService.countries(hash, callback);
    }

    public void industries(String hash, Callback<ArrayList<IndustryModel>> callback) {
        mockService.industries(hash, callback);
    }
}
