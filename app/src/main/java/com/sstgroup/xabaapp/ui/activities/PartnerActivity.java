package com.sstgroup.xabaapp.ui.activities;


import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.utils.Constants;
import com.sstgroup.xabaapp.utils.Utils;

import butterknife.OnClick;

public class PartnerActivity extends BaseActivity {

    String screen;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_partners;
    }

    @Override
    protected void init() {

        screen = getIntent().getExtras().getString(SplashActivity.GO_TO);
        startTimerForSplash();

//        FrameLayout frameLayoutOne = (FrameLayout) findViewById(R.id.frame_layout_one);
//        FrameLayout frameLayoutTwo = (FrameLayout) findViewById(R.id.frame_layout_two);
//        FrameLayout frameLayoutThree = (FrameLayout) findViewById(R.id.frame_layout_three);
//        FrameLayout frameLayoutFour = (FrameLayout) findViewById(R.id.frame_layout_four);
//
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadeinout);
//
//        frameLayoutOne.startAnimation(animation);
//        frameLayoutTwo.startAnimation(animation);
//        frameLayoutThree.startAnimation(animation);
//        frameLayoutFour.startAnimation(animation);
    }

    private void startTimerForSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (screen.equals(SplashActivity.HOME_PAGE)) {
                    goToHome();
                } else {
                    goToMainScreen();
                }
            }
        }, 3000);
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.txt_visit_url})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.txt_visit_url:
                Utils.openUrl(Constants.VISIT_XABA_URL, this);
                break;
        }
    }
}
