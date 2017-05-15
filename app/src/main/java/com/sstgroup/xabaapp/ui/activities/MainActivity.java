package com.sstgroup.xabaapp.ui.activities;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.network.adapter.NetworkAdapterService;
import com.sstgroup.xabaapp.network.adapter.objects.CountryModel;
import com.sstgroup.xabaapp.network.adapter.objects.IndustryModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    public NetworkAdapterService networkService;

    @Override
    protected int getLayoutId() {
        return R.layout.landing;
    }

    @Override
    protected void init() {
        Timber.d("MainActivity onCreate called!");

        networkService = NetworkAdapterService.getInstance(this);

        networkService.industries("hash", new Callback<ArrayList<IndustryModel>>() {
            @Override
            public void success(ArrayList<IndustryModel> industryModel, Response response) {
                Timber.d("Industries :" + industryModel);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d("Industries failed to load:" + error);
            }
        });

        networkService.countries("hash", new Callback<ArrayList<CountryModel>>() {
            @Override
            public void success(ArrayList<CountryModel> countryModel, Response response) {
                Timber.d("Countries :" + countryModel);
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d("Countries failed to load:" + error);
            }
        });
    }
}
