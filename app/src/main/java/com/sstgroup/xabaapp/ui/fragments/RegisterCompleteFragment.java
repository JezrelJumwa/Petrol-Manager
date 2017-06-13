package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.activities.LoginActivity;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterCompleteFragment extends BaseFragment {

    private Long userId;

    @BindView(R.id.your_referral_id_is)
    TextView mTextViewYourReferralIdIs;

    public static RegisterCompleteFragment newInstance(long userId) {

        Bundle args = new Bundle();
        args.putLong(Constants.WORKER_ID, userId);
        RegisterCompleteFragment fragment = new RegisterCompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_complete;
    }

    @Override
    protected void initFields() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getLong(Constants.WORKER_ID, -1);
        }
    }

    @Override
    protected void initViews(View rootView) {
        mTextViewYourReferralIdIs.setText(String.valueOf(userId));
    }

    @OnClick({R.id.continuE, R.id.register_another_worker})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.continuE:
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.register_another_worker:
         /*       Bundle bundle = new Bundle();
                bundle.putString(Constants.WORKER_ID, userId);

                RegisterWorkerByAgentFragment registerWorkerByAgentFragment = new RegisterWorkerByAgentFragment();
                registerWorkerByAgentFragment.setArguments(bundle);
                activity.openFragment(registerWorkerByAgentFragment, false);*/
                break;
        }
    }

    @OnClick(R.id.txt_visit_url)
    public void openUrl() {
        Utils.openUrl(Constants.VISIT_XABA_URL, activity);
    }
}
