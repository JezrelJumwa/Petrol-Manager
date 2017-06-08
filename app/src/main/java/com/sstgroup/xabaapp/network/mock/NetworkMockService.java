package com.sstgroup.xabaapp.network.mock;

import com.sstgroup.xabaapp.network.adapter.AdapterCallback;
import com.sstgroup.xabaapp.network.adapter.objects.CountryModel;
import com.sstgroup.xabaapp.network.adapter.objects.IndustryModel;
import com.sstgroup.xabaapp.network.adapter.objects.ProfileModel;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 5/10/17.
 */

public class NetworkMockService {

    public void login(String pin, String nationalId, AdapterCallback<ProfileModel> callback) {
        callback.onResponse(new ProfileModel(), "", 200);
    }

    public void countries(String hash, AdapterCallback<ArrayList<CountryModel>> callback) {
        ArrayList<CountryModel> countries = new ArrayList<CountryModel>();
        countries.add(new CountryModel());
        callback.onResponse(countries, "", 200);
    }

    public void industries(String hash, AdapterCallback<ArrayList<IndustryModel>> callback) {
        ArrayList<IndustryModel> industries = new ArrayList<IndustryModel>();
        industries.add(new IndustryModel());
        callback.onResponse(industries, "", 200);
    }
}
