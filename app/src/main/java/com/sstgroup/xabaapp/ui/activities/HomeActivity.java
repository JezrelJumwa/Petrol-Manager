package com.sstgroup.xabaapp.ui.activities;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.ui.fragments.DashboardFragment;
import com.sstgroup.xabaapp.ui.fragments.MyProfileFragment;
import com.sstgroup.xabaapp.ui.fragments.NavigationDrawerFragment;
import com.sstgroup.xabaapp.ui.fragments.NotificationsFragment;
import com.sstgroup.xabaapp.ui.fragments.RegisterWorkerByAgentFragment;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private NavigationDrawerFragment mDrawer;
    private boolean mFirstResume = true;
    private boolean mFirstRun = true;
    protected boolean mInForeground = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
//        ViewCompat.setElevation(mToolbar, 32);
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
        mInForeground = true;
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
                break;
            case R.id.nav_my_profile:
                openFragment(MyProfileFragment.newInstance(), false);
                break;
            case R.id.nav_notifications:
                openFragment(NotificationsFragment.newInstance(), false);
                break;
            case R.id.nav_faq:
                break;
            case R.id.nav_contact:
                break;
            case R.id.nav_logout:
                XabaApplication.getInstance().logout();
                break;

        }
    }
}

