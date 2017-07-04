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

    public static final String HOME_PAGE = "homePage";
    public static final String MAIN_PAGE = "mainScreen";
    public static final String GO_TO = "goTo";

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
                    startPartnerActivity(HOME_PAGE);
                } else {
                    startPartnerActivity(MAIN_PAGE);
                }
            }
        }, Constants.SPLASH_DURATION);
    }

    private void startPartnerActivity(String screen) {
        Intent intent = new Intent(this, PartnerActivity.class);
        intent.putExtra(GO_TO, screen);
        startActivity(intent);
        finish();
    }

    private void getLocations() {

        final String savedLocationHash = Preferences.getLocationHash(SplashActivity.this);

        Call<LocationResponse> call = RestClient.getService().getLocations(XabaApplication.getInstance().getLanguageCode(), Constants.AGENT_APP_VALUE, savedLocationHash);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.isSuccessful()) {
                    LocationStructure locationStructure = response.body().getLocationStructure();

                    if (!locationStructure.isNotModified) {
                        if (!savedLocationHash.equals(locationStructure.getHash())) {
                            Preferences.setLocationHash(SplashActivity.this, locationStructure.hash);
                            xabaDbHelper.deleteLocationTables();
                            xabaDbHelper.insertOrReplaceLanguages(locationStructure.getLanguages());
                            xabaDbHelper.insertOrReplaceCurrencies(locationStructure.getCurrencies());
                            xabaDbHelper.insertOrReplaceCountries(locationStructure.getCountries()); // insert all countries, counties and subCounties
                        }
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

        final String savedProfessionHash = Preferences.getProfessionHash(SplashActivity.this);

        Call<ProfessionResponse> call = RestClient.getService().getProfessions(XabaApplication.getInstance().getLanguageCode(),
                Constants.AGENT_APP_VALUE, savedProfessionHash);
        call.enqueue(new Callback<ProfessionResponse>() {
            @Override
            public void onResponse(Call<ProfessionResponse> call, Response<ProfessionResponse> response) {
                if (response.isSuccessful()) {
                    ProfessionStructure professionStructure = response.body().getProfessionStructure();

                    if (!professionStructure.isNotModified) {
                        if (!savedProfessionHash.equals(professionStructure.getHash())) {
                            Preferences.setProfessionHash(SplashActivity.this, professionStructure.hash);
                            xabaDbHelper.deleteProfessionTables();
                            xabaDbHelper.insertOrReplaceIndustries(professionStructure.getIndustries()); // insert all industries, categories and professions
                            xabaDbHelper.insertOrReplacePrograms(professionStructure.getPrograms());
                        }
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
