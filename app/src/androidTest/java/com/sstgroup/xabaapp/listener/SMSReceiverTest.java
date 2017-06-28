package com.sstgroup.xabaapp.listener;

import android.content.Intent;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.XabaApplication;
import com.sstgroup.xabaapp.ui.activities.MainActivity;
import com.sstgroup.xabaapp.utils.Preferences;

import junit.framework.TestCase;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 6/28/17.
 */
@RunWith(AndroidJUnit4.class)
public class SMSReceiverTest {

    SMSReceiver smsReceiver;


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        smsReceiver = new SMSReceiver();
        smsReceiver.setLocalizedVerificationCode("verification code");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void onReceive_receivesAndParsesSMSCorrectly() throws Exception {
        Intent intent = new Intent();
        //Testing pdus coresponding to the message "Dear Sir, your verification code for account in XABA is BC995EA8. Dial *884# to login and manage your account." send from 1234567 number
        byte [][] pdus = {{ 0, 32, 7, -127, 33, 67, 101, -9, 0, 0, 113, 96, -126, 65, 117, 65, 33, 112, 34, 98, 57, 44, 7, 77, -45, 114, 22, 40, -1, -82,
                -53, 65, -10, -78, 60, 109, 78, -113, -61, -12, -12, -37, 13, 26, -65, -55, 101, -112, -7, 45, 7, -123, -57, -29, 119, -35, 77, 7, -91,
                -35, 32, 108, 80, 24, 4, -91, -25, 32, -31, 48, -105, -85, 21, -125, 56, 23, -120, -104, 14, -77, 65, 42, 28, -114, 54, 2, -47,
        -33, 32, -10, -5, -100, 118, -125, -62, 110, 50, -88, 29, 118, -121, -49, 101, 80, -2, 93, -105, -125, -62, -29, -15, -69, -18, -90, -69, 68 }};

        if (Preferences.getLoggedUserId(XabaApplication.getInstance().getApplicationContext()) == null ||
                Preferences.getLoggedUserId(XabaApplication.getInstance().getApplicationContext()) < 0) {
            Preferences.setLoggedUserId(XabaApplication.getInstance().getApplicationContext(), 1L);
        }

        intent.putExtra("pdus", (Object[])pdus);
        intent.putExtra("format", "3gpp");

        smsReceiver.onReceive(XabaApplication.getInstance().getApplicationContext(), intent);

        onView(isRoot()).perform(waitFor(300));

        onView(withId(R.id.resend_pin_sms))
                .check(matches(isDisplayed()));
    }

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }

}