package com.sstgroup.xabaapp.ui.activities;

import android.content.Intent;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sstgroup.xabaapp.FeedbackMatcher;
import com.sstgroup.xabaapp.R;
import com.sstgroup.xabaapp.WaitAction;
import com.sstgroup.xabaapp.XabaApplication;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.sstgroup.xabaapp.Helpers.first;
import static com.sstgroup.xabaapp.Helpers.viewHolderAtPosition;
import static com.sstgroup.xabaapp.WaitAction.waitFor;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by julianlubenov on 7/4/17.
 */
public class EditProfileActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class, true, false);

    @Rule
    public ActivityTestRule<EditProfileActivity> mEditProfileRule = new ActivityTestRule<EditProfileActivity>(EditProfileActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        XabaApplication.getInstance().logout();

        mActivityRule.launchActivity(new Intent());
    }

    @After
    public void tearDown() throws Exception {
        XabaApplication.getInstance().logout();
    }

    @Test
    public void testUpdateProfileScreenDisplaysListOfPrograms() {
        //wait for the spalsh screen to disappear

        onView(withId(R.id.register)).withFailureHandler(new FailureHandler() {
            @Override
            public void handle(Throwable error, Matcher<View> viewMatcher) {
                //retry
                onView(isRoot()).perform(waitFor(1000));
            }
        });
        enterUpdateProfile();
        checkProgramsSelection();
    }

    private void enterUpdateProfile() {
        onView(withId(R.id.national_id)).perform(replaceText("22330828"));
        onView(withId(R.id.pin_code)).perform(replaceText("1234"));
        onView(withId(R.id.log_in)).perform(click());

        onView(isRoot()).perform(waitFor(400)); //Wait for login to complete

        Intent updateProfileIntent = new Intent();
        mEditProfileRule.launchActivity(updateProfileIntent);
    }

    public static void checkProgramsSelection() {

        onView(withId(R.id.rv_edit_profile)).perform(RecyclerViewActions.actionOnItemAtPosition(5,scrollTo()));
        onView(withId(R.id.row_profile_frame)).perform(click());
        //Check that the chooser dialog is displayed
        onView(withId(R.id.rv_chooser)).check(matches(isDisplayed()));
        FeedbackMatcher feedback = new FeedbackMatcher(TextView.class);

        onView(allOf(isDescendantOfA(withId(R.id.rv_chooser)), isDescendantOfA(viewHolderAtPosition(3)), first(feedback))).perform(click());
        onView(withId(R.id.btn_close)).perform(click());

        onView(withId(R.id.txt_profession)).check(matches(withText(feedback.getFeedbackText())));

        Log.d("Pass" , "feeback: " + feedback.getFeedbackText());

    }

    @Test
    public void testGenericProfessionsDifferForDifferentCategories() {
        enterUpdateProfile();

        onView(withId(R.id.rv_edit_profile)).perform(RecyclerViewActions.actionOnItemAtPosition(5,scrollTo()));

        onView(withId(R.id.remove_profession_one)).perform(click());
        onView(withId(R.id.add_another_profession)).perform(click());

        onView(withId(R.id.rv_edit_profile)).perform(RecyclerViewActions.actionOnItemAtPosition(5,scrollTo()));

        onView(withId(R.id.grp_industry)).perform(click());
        onView(allOf(isDescendantOfA(withId(R.id.rv_chooser)), withText(containsString("Agriculture")))).perform(click());
        onView(withId(R.id.txt_industry_selection)).check(matches(withText("Agriculture")));


        onView(withId(R.id.grp_category)).perform(click());
        onView(allOf(isDescendantOfA(withId(R.id.rv_chooser)), withText(containsString("General")))).perform(click());
        onView(withId(R.id.txt_category_selection)).check(matches(withText("General")));

        onView(withId(R.id.grp_profession)).perform(click());
        final ArrayList<String> professions1 = new ArrayList<>();
        final ArrayList<String> professions2 = new ArrayList<>();
        onView(withId(R.id.rv_chooser)).check(matches(new BoundedMatcher<View, View>(View.class) {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) item;

                    int count = recyclerView.getChildCount();
                    for (int i = 0; i < count; ++i) {
                        View child = recyclerView.getChildAt(i);
                        TextView txtView = (TextView) child.findViewById(R.id.txt_item_name);
                        professions1.add(txtView.getText().toString());
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        }));
        onView(withId(R.id.rv_chooser)).perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.grp_industry)).perform(click());
        onView(allOf(isDescendantOfA(withId(R.id.rv_chooser)), withText(containsString("Domestic")))).perform(click());
        onView(withId(R.id.txt_industry_selection)).check(matches(withText("Domestic")));


        onView(withId(R.id.grp_category)).perform(click());
        onView(allOf(isDescendantOfA(withId(R.id.rv_chooser)), withText(containsString("General")))).perform(click());
        onView(withId(R.id.txt_category_selection)).check(matches(withText("General")));

        onView(withId(R.id.grp_profession)).perform(click());

        onView(withId(R.id.rv_chooser)).check(matches(new BoundedMatcher<View, View>(View.class) {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) item;

                    int count = recyclerView.getChildCount();
                    for (int i = 0; i < count; ++i) {
                        View child = recyclerView.getChildAt(i);
                        TextView txtView = (TextView) child.findViewById(R.id.txt_item_name);
                        professions2.add(txtView.getText().toString());
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        }));

        Log.d("prof" , "professions1: " + professions1);
        Log.d("prof" , "professions2: " + professions2);

        if (professions1.size() == professions2.size()) {
            boolean allSame = true;
            for (String prof : professions1) {
                if (!professions2.contains(prof)) {
                    allSame = false;
                    break;
                }
            }
            if (allSame) {
                Assert.fail("Professions should not be same for different Industries in the Genral Category.");
            }
        }

    }

}