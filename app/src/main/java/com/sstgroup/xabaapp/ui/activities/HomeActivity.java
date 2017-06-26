package com.sstgroup.xabaapp.ui.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.UserResponse;
import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.fragments.ContactFragment;
import com.sstgroup.xabaapp.ui.fragments.DashboardFragment;
import com.sstgroup.xabaapp.ui.fragments.FaqFragment;
import com.sstgroup.xabaapp.ui.fragments.MyProfileFragment;
import com.sstgroup.xabaapp.ui.fragments.NavigationDrawerFragment;
import com.sstgroup.xabaapp.ui.fragments.NotificationsFragment;
import com.sstgroup.xabaapp.ui.fragments.RegisterWorkerByAgentFragment;
import com.sstgroup.xabaapp.ui.fragments.ReportsFragment;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeActivity extends BaseActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private NavigationDrawerFragment mDrawer;
    private boolean mFirstResume = true;
    private boolean mFirstRun;
    protected boolean mInForeground = true;

    private boolean mRequestedPermissionsOnce = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
//        ViewCompat.setElevation(mToolbar, 32);
        mFirstRun = true;
        setupToolbar(mToolbar, R.drawable.ic_hamburger);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mInForeground = false;
    }

    @OnClick(R.id.iv_add_profile)
    void addProfile() {
        ToastInterval.showToast(this, "Add profile");
    }

    @Override
    public void onNavigationDrawerItemSelected(final int menuItemId) {
        if (mFirstRun) {
            mFirstRun = false;
            setContentFragment(menuItemId);
            return;
        }

        if (mInForeground) {
            setContentFragment(menuItemId);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mDrawer.getCurrentSelectItem() != null &&
                    mDrawer.getCurrentSelectItem().getId() == R.id.nav_register_worker) {
                super.onBackPressed();
            } else {
                mDrawer.menuItemClick(findViewById(R.id.nav_register_worker));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mFirstResume) {
            mDrawer = (NavigationDrawerFragment)
                    getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mDrawer.setUp(R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }

        if (mFirstRun){
            loadUserProfile();
        }

        mInForeground = true;

        if (!mRequestedPermissionsOnce) {
            requestSmsPermission();
            mRequestedPermissionsOnce = true;
        }
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, Manifest.permission.RECEIVE_SMS)) {
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

    public void loadUserProfile() {
        Call<UserResponse> call = RestClient.getService().getWorkerData(XabaApplication.getInstance().getLanguageCode(), Constants.AGENT_APP_VALUE,
                XabaApplication.getInstance().getToken().getValue());

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getUser();
                    if (response.body().getUser() != null) {

                        if (user.getTokenFromWS() == null) {
                            user.setToken(XabaApplication.getInstance().getToken());
                        }

                        xabaDbHelper.insertLoggedUser(HomeActivity.this, response.body().getUser());
                    }
                } else {
                    ErrorCodeAndMessage errorLogin = ErrorUtils.parseErrorCodeMessage(response);

                    if (errorLogin.getErrors().getMessage().equals(Constants.ERROR_UNAUTHORIZED)) {
                        XabaApplication.getInstance().logout();
                        //from this point we logout user
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }

    private void setContentFragment(int menuItemId) {
        switch (menuItemId) {
            case R.id.nav_register_worker:
                openFragment(RegisterWorkerByAgentFragment.newInstance(), false);
                break;
            case R.id.nav_dashboard:
                openFragment(DashboardFragment.newInstance(), false);
                break;
            case R.id.nav_reports:
                openFragment(ReportsFragment.newInstance(), false);
                break;
            case R.id.nav_my_profile:
                openFragment(MyProfileFragment.newInstance(), false);
                break;
            case R.id.nav_notifications:
                openFragment(NotificationsFragment.newInstance(), false);
                break;
            case R.id.nav_faq:
                openFragment(FaqFragment.newInstance(), false);
                break;
            case R.id.nav_contact:
                openFragment(ContactFragment.newInstance(), false);
                break;
            case R.id.nav_logout:
                XabaApplication.getInstance().logout("");
                break;

        }
    }

    public void openAddWorker() {
        mDrawer.selectAddWorker();
    }
}

