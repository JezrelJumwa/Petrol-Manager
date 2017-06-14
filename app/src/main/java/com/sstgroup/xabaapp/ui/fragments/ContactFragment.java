package com.sstgroup.xabaapp.ui.fragments;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ui.widgets.ToastInterval;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactFragment extends BaseFragment {

    @BindView(R.id.email)
    EditText mEditTextEmail;
    @BindView(R.id.message)
    EditText mEditTextMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initFields() {

    }

    @Override
    protected void initViews(View rootView) {

    }

    public static ContactFragment newInstance() {

        Bundle args = new Bundle();

        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.send})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                ToastInterval.showToast(activity, "SENT");
                break;
        }
    }
}
