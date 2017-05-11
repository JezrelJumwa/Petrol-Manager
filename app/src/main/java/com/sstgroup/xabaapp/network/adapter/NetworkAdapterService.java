package com.sstgroup.xabaapp.network.adapter;

import com.sstgroup.xabaapp.network.adapter.objects.ProfileModel;
import com.sstgroup.xabaapp.network.mock.NetworkMockService;

import retrofit.Callback;

/**
 * Created by julianlubenov on 5/10/17.
 */

public class NetworkAdapterService {
    public static void login(String pin, String nationalId, Callback<ProfileModel> callback) {
        NetworkMockService.login(pin, nationalId, callback);
    }
}
