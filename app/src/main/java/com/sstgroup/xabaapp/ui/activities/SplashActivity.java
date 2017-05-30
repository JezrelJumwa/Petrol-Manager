package com.sstgroup.xabaapp.ui.activities;


import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.data.XabaDatabaseHelper;
import com.sstgroup.xabaapp.models.Language;
import com.sstgroup.xabaapp.models.LocationResponse;
import com.sstgroup.xabaapp.models.LocationStructure;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SplashActivity extends BaseActivity {

    private XabaDatabaseHelper mXabaDatabaseHelper;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        mXabaDatabaseHelper = XabaDatabaseHelper.getInstance(this);

        List<Language> languages = mXabaDatabaseHelper.getLanguages();
        Timber.d("TEST");
        Timber.d("TEST" + languages.get(0).getLanguageCode());
        Timber.d("TEST" + languages.get(1).getIsRightToLeft());

//        getLocations();
    }


    private void getLocations() {

        Timber.d("getLocations");

        Call<LocationResponse> call = RestClient.getService().getLocations(Constants.AGENT_APP_VALUE);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.isSuccessful()) {
                    LocationStructure locationStructure = response.body().getLocationStructure();
                    Timber.d("onResponse");
                    mXabaDatabaseHelper.insertOrReplaceLanguages(locationStructure.getLanguages());
                }
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
