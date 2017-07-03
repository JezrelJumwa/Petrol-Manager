package com.sstgroup.xabaapp;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by julianlubenov on 7/3/17.
 */

public final class Helpers {
    public static <T> Matcher<T> first(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isFirst = true;

            @Override
            public boolean matches(final Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }

                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return first matching item");
            }
        };
    }

    public static BoundedMatcher<View, View> viewHolderAtPosition(int position) {
        return  new ViewHolderAtPositionMatcher(View.class, position);
    }
}
