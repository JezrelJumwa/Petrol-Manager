package com.sstgroup.xabaapp.ui.fragments;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.core.deps.guava.base.Strings;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.WaitAction;
import com.sstgroup.xabaapp.ui.activities.RegisterActivity;
import com.sstgroup.xabaapp.ui.activities.SplashActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

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
        onView(withId(R.id.rv_chooser)).perform(actionOnItemAtPosition(0, click()));

    }

}