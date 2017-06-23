package com.sstgroup.xabaapp.ui.activities;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.network.adapter.NetworkAdapterService;
import com.sstgroup.xabaapp.ui.fragments.WizardStepOneFragment;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Validator;

public class MainActivity extends BaseActivity {

    public NetworkAdapterService networkService;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        WizardStepOneFragment wizardStepOneFragment = new WizardStepOneFragment();
        openFragment(wizardStepOneFragment, false);

        if (getIntent().getExtras() != null){
            String message = getIntent().getExtras().getString(Constants.LOG_OUT_MESSAGE, "");

            if (!Validator.isEmpty(message)){
                ToastInterval.showToast(this, message);
            }
        }
  /*      Timber.d("MainActivity onCreate called!");

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
        });*/
    }
}
