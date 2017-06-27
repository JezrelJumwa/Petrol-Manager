package com.sstgroup.xabaapp.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.models.User;
import com.sstgroup.xabaapp.models.errors.ErrorCodeAndMessage;
import com.sstgroup.xabaapp.service.RestClient;
import com.sstgroup.xabaapp.ui.activities.EditPinActivity;
import com.sstgroup.xabaapp.ui.activities.EditProfileActivity;
import com.sstgroup.xabaapp.ui.adapters.MyProfileAdapter;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.ErrorUtils;
import com.sstgroup.xabaapp.utils.NavigationUtils;
import com.sstgroup.xabaapp.utils.Utils;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileFragment extends BaseFragment implements MyProfileAdapter.Callbacks {

    public static final String TAG = MyProfileFragment.class.getSimpleName();

    @BindView(R.id.rv_profile)
    RecyclerView mRvMyProfile;
    private MyProfileAdapter myProfileAdapter;

    public static MyProfileFragment newInstance() {

        Bundle args = new Bundle();

        MyProfileFragment fragment = new MyProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_profile;
    }

    @Override
    protected void initFields() {
        mRvMyProfile.setLayoutManager(new LinearLayoutManager(activity));
        User loggedUser = xabaDbHelper.getLoggedUser(activity);
        loggedUser.refresh();
        myProfileAdapter = new MyProfileAdapter(loggedUser, this);
        mRvMyProfile.setAdapter(myProfileAdapter);
    }

    @Override
    protected void initViews(View rootView) {

    }

    @Override
    public void onResume() {
        super.onResume();
        User user = xabaDbHelper.getLoggedUser(activity);
        myProfileAdapter.updateUser(user);
    }

    private void showDeleteAccountDialog() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_deactivate_account);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_white_rect);

        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnDelete = (Button) dialog.findViewById(R.id.btn_delete);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.showLoader();
                RestClient.getService().deactivateAccount(XabaApplication.getInstance().getLanguageCode(),
                        Constants.AGENT_APP_VALUE, XabaApplication.getInstance().getToken().getValue()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            XabaApplication.getInstance().logout(getString(R.string.account_deactivated_success));
                        } else {
                            ErrorCodeAndMessage errorLogin = ErrorUtils.parseErrorCodeMessage(response);

                            if (errorLogin.getErrors().getMessage().equals(Constants.ERROR_UNAUTHORIZED)) {
                                XabaApplication.getInstance().logout();
                                //from this point we logout user
                                return;
                            }

                            ToastInterval.showToast(activity, getString(R.string.something_is_wrong));
                        }
                        activity.hideLoader();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Utils.onFailureUtils(activity, t);
                        activity.hideLoader();
                    }
                });
            }
        });

        dialog.show();
    }


    @Override
    public void onItemClick(int id) {
        switch (id) {
            case R.id.txt_change_pin:
                NavigationUtils.startActivity(activity, EditPinActivity.class);
                break;
            case R.id.btn_edit_profile:
                NavigationUtils.startActivity(activity, EditProfileActivity.class);
                break;
            case R.id.txt_delete_account:
                showDeleteAccountDialog();
                break;
        }
    }
}
