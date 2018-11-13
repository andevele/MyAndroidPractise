package com.zhulf.myandroidpractise;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class CustomContainer1 extends ViewGroup {
    public CustomContainer1(Context context) {
        this(context, null);
    }

    public CustomContainer1(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomContainer1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int width = 0;
        int height = 0;

        int measuredChildWidth = 0;
        int measuredChildHeight = 0;

        int topChildWidth = 0;
        int bottomChildWidth = 0;
        int topChildHeight = 0;
        int bottomChildheight = 0;

        int childCount = getChildCount();

        MarginLayoutParams mParams = null;

        for(int i = 0;i < childCount;i++) {
            View childView = getChildAt(i);
            measuredChildWidth = getMeasuredWidth();
            measuredChildHeight = getMeasuredHeight();
            mParams = (MarginLayoutParams) childView.getLayoutParams();
            if(i == 0 || i == 1) {
                topChildWidth = measuredChildWidth + mParams.getMarginStart() + mParams.getMarginEnd();

            } else if (i == 2 || i == 3) {
                bottomChildWidth = measuredChildWidth + mParams.getMarginStart() + mParams.getMarginEnd();
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
