package com.sstgroup.xabaapp.ui.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.network.adapter.NetworkAdapterService;
import com.sstgroup.xabaapp.ui.fragments.WizardStepOneFragment;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Validator;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    public NetworkAdapterService networkService;

    private boolean mRequestedPermissionsOnce = false;

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

    @Override
    protected void onResume() {
        super.onResume();

        if (!mRequestedPermissionsOnce) {
            requestSmsPermission();
            mRequestedPermissionsOnce = true;
        }
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.RECEIVE_SMS)) {
                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("SMS permissions required").setMessage("The Xaba app needs SMS permissions in order to automatically parse verification codes received by SMS. Alternatively, you can also enter SMS verification codes manually.").show();
                mRequestedPermissionsOnce = false;
            } else {
                String[] permission_list = new String[1];
                permission_list[0] = permission;
                ActivityCompat.requestPermissions(this, permission_list, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Timber.d("sms permission granted");

            } else {
                Timber.d("sms permission not granted");
            }
        }

    }
}
