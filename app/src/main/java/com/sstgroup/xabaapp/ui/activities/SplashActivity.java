package com.sstgroup.xabaapp.ui.activities;


import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.LocationResponse;
import com.sstgroup.xabaapp.models.LocationStructure;
import com.sstgroup.xabaapp.models.ProfessionResponse;
import com.sstgroup.xabaapp.models.ProfessionStructure;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {

        getLocations();
        getProfessions();

        startTimerForSplash();

        LinearLayout centerItems = (LinearLayout) findViewById(R.id.center_items);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadeinout);
        centerItems.startAnimation(animation);
    }

    private void startTimerForSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (XabaApplication.getInstance().isAuthenticated()) {
                    goToHome();
                } else {
                    goToMainScreen();
                }
            }
        }, Constants.SPLASH_DURATION);
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void getLocations() {

        Call<LocationResponse> call = RestClient.getService().getLocations(Constants.AGENT_APP_VALUE);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.isSuccessful()) {
                    LocationStructure locationStructure = response.body().getLocationStructure();

                    String savedLocationHash = Preferences.getLocationHash(SplashActivity.this);

                    if (!savedLocationHash.equals(locationStructure.getHash())) {
                        Preferences.setLocationHash(SplashActivity.this, locationStructure.hash);
                        xabaDbHelper.deleteLocationTables();
                        xabaDbHelper.insertOrReplaceLanguages(locationStructure.getLanguages());
                        xabaDbHelper.insertOrReplaceCountries(locationStructure.getCountries()); // insert all countries, counties and subCounties
                    }
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

                    String savedProfessionHash = Preferences.getProfessionHash(SplashActivity.this);

                    if (!savedProfessionHash.equals(professionStructure.getHash())) {
                        Preferences.setProfessionHash(SplashActivity.this, professionStructure.hash);
                        xabaDbHelper.deleteProfessionTables();
                        xabaDbHelper.insertOrReplaceIndustries(professionStructure.getIndustries()); // insert all industries, categories and professions
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfessionResponse> call, Throwable t) {
                Timber.d("onFailure" + t.toString());
            }
        });
    }
}
