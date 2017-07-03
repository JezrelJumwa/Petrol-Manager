package com.sstgroup.xabaapp;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by julianlubenov on 7/3/17.
 */

public class FeedbackMatcher extends BoundedMatcher<View, View> {

    private String feedbackText = "";

    public FeedbackMatcher(Class<? extends View> expectedType) {
        super(expectedType);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("withFeedback");
    }

    @Override
    protected boolean matchesSafely(View item) {
        return findTextView(item);
    }

    private boolean findTextView(View item) {
        if (item instanceof TextView) {
            feedbackText = ((TextView) item).getText().toString();
            return true;
        } else if (item instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup)item).getChildCount(); ++i) {
                View child = ((ViewGroup)item).getChildAt(i);

                if (findTextView(child)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public String getFeedbackText() {
        return feedbackText;
    }
}
