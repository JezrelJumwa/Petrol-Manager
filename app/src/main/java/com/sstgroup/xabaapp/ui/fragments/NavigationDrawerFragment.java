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

import butterknife.OnClick;


public class NavigationDrawerFragment extends BaseFragment {
    private static final String STATE_SELECTED_ID = "selected_navigation_drawer_menu_id";

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

    private boolean selectItem(AppCompatCheckedTextView v) {
        if (v != mCurrentSelectedItem) {
            int viewId = v.getId();
            if (mCurrentSelectedItem != null && viewId != R.id.nav_logout) {
                mCurrentSelectedItem.setChecked(false);
                setTintFromActiveToInactive(mCurrentSelectedItem.getId(), mCurrentSelectedItem);
            }

            if (v != null && viewId != R.id.nav_logout) {
                v.setChecked(true);
                setTintFromInactioveToActive(viewId, v);
            }

            if (viewId != R.id.nav_logout) {
                mCurrentSelectedItem = v;
            }
            return true;
        }
        return false;
    }


    private void setTintFromActiveToInactive(int id, AppCompatCheckedTextView v) {
//        if (R.id.nav_home == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_home_inactive, 0, 0, 0);
//        } else if (R.id.nav_profile == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_profile_inactive, 0, 0, 0);
//        } else if (R.id.nav_feedback == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_feedback_inactive, 0, 0, 0);
//        } else if (R.id.nav_rate_app == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_rate_app_inactice, 0, 0, 0);
//        } else if (R.id.nav_join_artists == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_join_artists_inactive, 0, 0, 0);
//        } else if (R.id.nav_win_art == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_share_inactive, 0, 0, 0);
//        } else if (R.id.nav_my_purchases == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_my_purchases, 0, 0, 0);
//        } else if (R.id.nav_instagram == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.instagram_icon, 0, 0, 0);
//        }
    }

    private void setTintFromInactioveToActive(int id, AppCompatCheckedTextView v) {
//        if (R.id.nav_home == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_home_active, 0, 0, 0);
//        } else if (R.id.nav_profile == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_profile_active, 0, 0, 0);
//        } else if (R.id.nav_feedback == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_feedback_active, 0, 0, 0);
//        } else if (R.id.nav_rate_app == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_rate_app_actice, 0, 0, 0);
//        } else if (R.id.nav_join_artists == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_join_artists_active, 0, 0, 0);
//        } else if (R.id.nav_win_art == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_share_active, 0, 0, 0);
//        } else if (R.id.nav_my_purchases == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_my_purchases, 0, 0, 0);
//        } else if (R.id.nav_instagram == id) {
//            v.setCompoundDrawablesWithIntrinsicBounds(R.drawable.instagram_icon, 0, 0, 0);
//        }
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
