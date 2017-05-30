package com.sstgroup.xabaapp.ui.fragments;


import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterCompleteFragment extends BaseFragment {

    @BindView(R.id.your_referral_id_is)
    TextView mTextViewYourReferralIdIs;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_complete;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {
        mTextViewYourReferralIdIs.setText(getString(R.string.your_referral_id_is) + " 54680321");
    }

    @OnClick({R.id.continuE, R.id.register_another_worker})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.continuE:
                break;
            case R.id.register_another_worker:
                RegisterWorkerByAgentFragment registerWorkerByAgentFragment = new RegisterWorkerByAgentFragment();
                activity.openFragment(registerWorkerByAgentFragment, false);
                break;
        }
    }
}
