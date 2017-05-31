package com.sstgroup.xabaapp.ui.activities;


import android.content.Intent;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.data.XabaDatabaseHelper;
import com.sstgroup.xabaapp.models.LocationResponse;
import com.sstgroup.xabaapp.models.LocationStructure;
import com.sstgroup.xabaapp.models.ProfessionResponse;
import com.sstgroup.xabaapp.models.ProfessionStructure;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.utils.Constants;

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

        getLocations();
        getProfessions();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void getLocations() {

        Call<LocationResponse> call = RestClient.getService().getLocations(Constants.AGENT_APP_VALUE);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.isSuccessful()) {
                    LocationStructure locationStructure = response.body().getLocationStructure();
                    mXabaDatabaseHelper = XabaDatabaseHelper.getInstance(SplashActivity.this);
                    mXabaDatabaseHelper.deleteLocationTables();
                    mXabaDatabaseHelper.insertOrReplaceLanguages(locationStructure.getLanguages());
                    mXabaDatabaseHelper.insertOrReplaceCountries(locationStructure.getCountries()); // insert all countries, counties and subCounties
                }
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }

    private void getProfessions() {

        Call<ProfessionResponse> call = RestClient.getService().getProfessions(Constants.AGENT_APP_VALUE);
        call.enqueue(new Callback<ProfessionResponse>() {
            @Override
            public void onResponse(Call<ProfessionResponse> call, Response<ProfessionResponse> response) {
                if (response.isSuccessful()) {
                    ProfessionStructure professionStructure = response.body().getProfessionStructure();
                    mXabaDatabaseHelper = XabaDatabaseHelper.getInstance(SplashActivity.this);
                    mXabaDatabaseHelper.deleteProfessionTables();
                    mXabaDatabaseHelper.insertOrReplaceIndustries(professionStructure.getIndustries()); // insert all industries, categories and professions
                }
            }

            @Override
            public void onFailure(Call<ProfessionResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
