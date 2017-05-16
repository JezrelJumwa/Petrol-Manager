package com.sstgroup.xabaapp.ui.activities;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.fragments.RegisterWorkerAgentFragment;

public class RegisterActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        RegisterWorkerAgentFragment registerWorkerAgentFragment = new RegisterWorkerAgentFragment();
        openFragment(registerWorkerAgentFragment, false);
    }
}
