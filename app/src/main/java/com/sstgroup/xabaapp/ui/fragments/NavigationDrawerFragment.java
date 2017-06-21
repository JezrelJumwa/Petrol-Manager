package com.sstgroup.xabaapp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.OnClick;


public class NavigationDrawerFragment extends BaseFragment {
    private static final String STATE_SELECTED_ID = "selected_navigation_drawer_menu_id";

    @BindView(R.id.nav_register_worker)
    AppCompatCheckedTextView addWorker;

    protected DrawerLayout mDrawerLayout;
    private NavigationDrawerCallbacks mCallbacks;
    private ActionBarDrawerToggle mDrawerToggle;
    private AppCompatCheckedTextView mCurrentSelectedItem;
    private View mTmpInitialCheckedView;
    private View mFragmentContainerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {
        int currentSelectedId = R.id.nav_register_worker;
        Bundle savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            currentSelectedId = savedInstanceState.getInt(STATE_SELECTED_ID);
        }
        mTmpInitialCheckedView = rootView.findViewById(currentSelectedId);
    }

    @OnClick({R.id.nav_register_worker, R.id.nav_dashboard, R.id.nav_reports,
            R.id.nav_my_profile, R.id.nav_notifications, R.id.nav_faq,
            R.id.nav_contact, R.id.nav_logout})
    public void menuItemClick(View v) {
        selectItemAndClose((AppCompatCheckedTextView) v);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);

        if (mTmpInitialCheckedView != null && mTmpInitialCheckedView instanceof AppCompatCheckedTextView) {
            selectItemAndClose((AppCompatCheckedTextView) mTmpInitialCheckedView);
        }
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;


        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        getActivity().getCurrentFocus().getWindowToken(),
                        0
                );
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public View getCurrentSelectItem() {
        return mCurrentSelectedItem;
    }

    public void selectAddWorker() {
        selectItemAndClose(addWorker);
    }

    private boolean selectItem(AppCompatCheckedTextView v) {
        if (v != mCurrentSelectedItem) {
            int viewId = v.getId();
            if (mCurrentSelectedItem != null && viewId != R.id.nav_logout) {
                mCurrentSelectedItem.setChecked(false);
                setTintFromActiveToInactive(mCurrentSelectedItem.getId(), mCurrentSelectedItem);
            }

            if (v != null && viewId != R.id.nav_logout) {
                v.setChecked(true);
                setTintFromInactiveToActive(viewId, v);
            }

            if (viewId != R.id.nav_logout) {
                mCurrentSelectedItem = v;
            }
            return true;
        }
        return false;
    }

    private void setTintFromActiveToInactive(int id, AppCompatCheckedTextView v) {
        switch (id) {
            case R.id.nav_register_worker:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_register_worker, 0, 0, 0);
                break;
            case R.id.nav_dashboard:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_dashboard, 0, 0, 0);
                break;
            case R.id.nav_reports:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_reports, 0, 0, 0);
                break;
            case R.id.nav_my_profile:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_my_profile, 0, 0, 0);
                break;
            case R.id.nav_notifications:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_notifications, 0, 0, 0);
                break;
            case R.id.nav_faq:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_faq, 0, 0, 0);
                break;
            case R.id.nav_contact:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_contact, 0, 0, 0);
                break;
        }
    }

    private void setTintFromInactiveToActive(int id, AppCompatCheckedTextView v) {
        switch (id) {
            case R.id.nav_register_worker:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_register_worker_selected, 0, 0, 0);
                break;
            case R.id.nav_dashboard:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_dashboard_selected, 0, 0, 0);
                break;
            case R.id.nav_reports:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_reports_selected, 0, 0, 0);
                break;
            case R.id.nav_my_profile:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_my_profile_selected, 0, 0, 0);
                break;
            case R.id.nav_notifications:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_notifications_selected, 0, 0, 0);
                break;
            case R.id.nav_faq:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_faq_selected, 0, 0, 0);
                break;
            case R.id.nav_contact:
                v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_contact_selected, 0, 0, 0);
                break;
        }
    }


    private void selectItemAndClose(AppCompatCheckedTextView v) {
        if (selectItem(v)) {
            if (mCallbacks != null) {
                mCallbacks.onNavigationDrawerItemSelected(v.getId());
            }
        }

        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallbacks = (NavigationDrawerCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_ID, mCurrentSelectedItem != null ? mCurrentSelectedItem.getId() : 0);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(int menuItemId);
    }
}
