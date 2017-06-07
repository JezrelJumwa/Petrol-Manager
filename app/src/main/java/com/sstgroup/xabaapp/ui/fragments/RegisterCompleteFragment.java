package com.sstgroup.xabaapp.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.activities.HomeActivity;
import com.sstgroup.xabaapp.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterCompleteFragment extends BaseFragment {

    String userId = "";

    @BindView(R.id.your_referral_id_is)
    TextView mTextViewYourReferralIdIs;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_complete;
    }

    @Override
    protected void initFields() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getString(Constants.WORKER_ID);
        }
    }

    @Override
    protected void initViews(View rootView) {
        mTextViewYourReferralIdIs.setText(userId);
    }

    @OnClick({R.id.continuE, R.id.register_another_worker})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.continuE:
                Intent intent = new Intent(activity, HomeActivity.class);
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
}
