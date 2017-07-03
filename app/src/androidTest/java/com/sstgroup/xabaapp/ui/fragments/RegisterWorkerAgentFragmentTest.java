package com.sstgroup.xabaapp.ui.fragments;

import android.support.test.espresso.FailureHandler;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.FeedbackMatcher;
import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.ViewHolderAtPositionMatcher;
import com.sstgroup.xabaapp.WaitAction;
import com.sstgroup.xabaapp.ui.activities.SplashActivity;
import static com.sstgroup.xabaapp.Helpers.first;
import static com.sstgroup.xabaapp.Helpers.viewHolderAtPosition;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by julianlubenov on 6/30/17.
 */
@RunWith(AndroidJUnit4.class)
public class RegisterWorkerAgentFragmentTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule =
            new ActivityTestRule(SplashActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRegisterScreenDisplaysListOfPrograms() {
        //wait for the spalsh screen to disappear

        onView(withId(R.id.register)).withFailureHandler(new FailureHandler() {
            @Override
            public void handle(Throwable error, Matcher<View> viewMatcher) {
                //retry
                onView(isRoot()).perform(WaitAction.waitFor(1000));
            }
        });
        afterClickingRegister();
    }

    public void afterClickingRegister() {

        onView(withId(R.id.grp_country)).perform(click());
        onView(withId(R.id.rv_chooser)).perform(click());

        onView(withId(R.id.grp_language)).perform(click());
        onView(withId(R.id.rv_chooser)).perform(click());

        onView(withId(R.id.register)).perform(click()).check(matches(not(isDisplayed())));
        onView(withId(R.id.grp_program)).check(matches(isDisplayed()));
        onView(withId(R.id.grp_program)).perform(scrollTo());
        onView(withId(R.id.grp_program)).perform(click());
        //Check that the chooser dialog is displayed
        onView(withId(R.id.rv_chooser)).check(matches(isDisplayed()));
        FeedbackMatcher feedback = new FeedbackMatcher(TextView.class);

        onView(allOf(isDescendantOfA(withId(R.id.rv_chooser)), isDescendantOfA(viewHolderAtPosition(3)), first(feedback))).perform(click());
        onView(withId(R.id.btn_close)).perform(click());

        onView(withId(R.id.txt_program)).check(matches(withText(feedback.getFeedbackText())));

        Log.d("Pass" , "feeback: " + feedback.getFeedbackText());

    }

}