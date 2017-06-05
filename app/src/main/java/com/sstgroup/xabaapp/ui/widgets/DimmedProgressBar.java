package com.sstgroup.xabaapp.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sstgroup.xabaapp.R;


/**
 * Created by alexander on 2/16/17.
 */

public class DimmedProgressBar extends RelativeLayout {

    public DimmedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.dimmed_progress_bar, this);
        setWillNotDraw(false);
    }
}
