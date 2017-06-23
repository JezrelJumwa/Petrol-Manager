package com.sstgroup.xabaapp.ui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.sstgroup.xabaapp.R;

/**
 * Created by julianlubenov on 6/9/17.
 */

public class CirclesProgress extends View {

    private int mWidth;
    private int mHeight;

    private int mDiameter;

    private int mPadding = 20;

    private int mStrokeWidth = 30;


    private float mFirstMin;
    private float mFirstMax;
    private float mSecondMin;
    private float mSecondMax;

    private float mValueFirst;
    private float mValueSecond;

    private void additionalInit(@Nullable AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CirclesProgress);

        mFirstMin = typedArray.getFloat(R.styleable.CirclesProgress_first_value_minimal, 0);
        mFirstMax = typedArray.getFloat(R.styleable.CirclesProgress_first_value_maximum, 100);
        mSecondMin = typedArray.getFloat(R.styleable.CirclesProgress_second_value_minimal, 0);
        mSecondMax = typedArray.getFloat(R.styleable.CirclesProgress_second_value_maximum, 100);

        mValueFirst = typedArray.getFloat(R.styleable.CirclesProgress_first_value, 0);
        mValueSecond = typedArray.getFloat(R.styleable.CirclesProgress_second_value, 0);

        typedArray.recycle();
        Resources resources = getResources();
        if (resources != null) {
            DisplayMetrics metrics = getResources().getDisplayMetrics();

            mPadding = (int) (mPadding * (((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)));
            mStrokeWidth = (int) (mStrokeWidth * (((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)));
        }
    }

    public CirclesProgress(Context context) {
        super(context);
    }

    public CirclesProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        additionalInit(attrs);
    }

    public CirclesProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        additionalInit(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightSize > 0 && widthSize > 0) {
            mWidth = Math.min(widthSize, heightSize);
            mHeight = Math.min(widthSize, heightSize);
        } else {
            mWidth = Math.max(widthSize, heightSize);
            mHeight = Math.max(widthSize, heightSize);
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    public void setmFirstMin(float mFirstMin) {
        this.mFirstMin = mFirstMin;
    }

    public void setmFirstMax(float mFirstMax) {
        this.mFirstMax = mFirstMax;
    }

    public void setmSecondMin(float mSecondMin) {
        this.mSecondMin = mSecondMin;
    }

    public void setmSecondMax(float mSecondMax) {
        this.mSecondMax = mSecondMax;
    }

    public void setmValueFirst(float mValueFirst) {
        this.mValueFirst = mValueFirst;
    }

    public void setmValueSecond(float mValueSecond) {
        this.mValueSecond = mValueSecond;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.dashboard_light_gray));


        float x = mPadding;
        float y = mPadding;
        float width = Math.min(getWidth(), canvas.getWidth()); //Seems that canvas.getWidth differs from the measured mWidth
        float height = Math.min(getWidth(), canvas.getHeight()); //Seems that canvas.getHeight differs from the measured mHeight

        //Draw first Arc
        canvas.drawArc(new RectF(x, y, (float)width - mPadding, (float)width - mPadding), 190.0f, 160.0f, false, paint);

        //Draw second Arc
        canvas.drawArc(new RectF(x, y, (float)width - mPadding, (float)width - mPadding), 10.0f, 160.0f, false, paint);

        canvas.drawPoint(width / 2, height / 2, paint);


        //Draw first value Arc
        paint.setColor(ContextCompat.getColor(getContext(), R.color.text_green));
        float sweepAngle = (mValueFirst / (mFirstMax - mFirstMin)) * 160;
        if (sweepAngle > 0)
            canvas.drawArc(new RectF(x, y, (float)width - mPadding, (float)width - mPadding), 190, sweepAngle, false, paint);

        //Draw second value Arc
        paint.setColor(ContextCompat.getColor(getContext(), R.color.red));
        sweepAngle = (mValueSecond / (mSecondMax - mSecondMin)) * 160;
        if (sweepAngle > 0)
            canvas.drawArc(new RectF(x, y, (float)width - mPadding, (float)width - mPadding), 170, -sweepAngle, false, paint);
    }
}
