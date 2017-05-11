package com.sstgroup.xabaapp.network.mock;

import com.sstgroup.xabaapp.network.adapter.objects.CountryModel;
import com.sstgroup.xabaapp.network.adapter.objects.IndustryModel;
import com.sstgroup.xabaapp.network.adapter.objects.ProfileModel;
import com.sstgroup.xabaapp.network.retrofit.objects.IndustriesRetrofitResponse;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Created by julianlubenov on 5/10/17.
 */

public class NetworkMockService {

    private Response getMockResponse() {
        return new Response("login", 200, "ok", new ArrayList<Header>(), new TypedInput() {
            @Override
            public String mimeType() {
                return null;
            }

            @Override
            public long length() {
                return 0;
            }

            @Override
            public InputStream in() throws IOException {
                return null;
            }
        });
    }

    public void login(String pin, String nationalId, Callback<ProfileModel> callback) {
        callback.success(new ProfileModel(), getMockResponse());
    }

    public void countries(String hash, Callback<ArrayList<CountryModel>> callback) {
        ArrayList<CountryModel> countries = new ArrayList<CountryModel>();
        countries.add(new CountryModel());
        callback.success(countries, getMockResponse());
    }

    public void industries(String hash, Callback<ArrayList<IndustryModel>> callback) {
        ArrayList<IndustryModel> industries = new ArrayList<IndustryModel>();
        industries.add(new IndustryModel());
        callback.success(industries, getMockResponse());
    }
}
