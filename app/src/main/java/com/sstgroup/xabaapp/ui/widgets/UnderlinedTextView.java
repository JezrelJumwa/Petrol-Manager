package com.sstgroup.xabaapp.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by alexander on 2/6/17.
 */

public class UnderlinedTextView extends AppCompatTextView {

    public UnderlinedTextView(Context context) {
        super(context);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnderlinedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        setPaintFlags(getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

}
