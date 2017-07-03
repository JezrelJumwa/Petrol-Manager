package com.sstgroup.xabaapp;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;

import org.hamcrest.Description;

/**
 * Created by julianlubenov on 7/3/17.
 */

public class ViewHolderAtPositionMatcher extends BoundedMatcher<View, View> {

    private int position = 0;

    public ViewHolderAtPositionMatcher(Class<? extends View> expectedType, int position) {
        super(expectedType);
        this.position = position;
    }

    @Override
    protected boolean matchesSafely(View item) {
        ViewParent parent = item.getParent();

        if (parent != null) {
            if (parent instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) parent;
                RecyclerView.ViewHolder vh = null;
                try {
                    vh = recyclerView.getChildViewHolder(item);
                } catch (Exception e) {

                }
                if (vh != null && recyclerView.findViewHolderForAdapterPosition(position).equals(vh)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("ViewHolderAtPostion position " + position);
    }
}
