package com.zhulf.myandroidpractise;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


public class CustomContainer1 extends ViewGroup {
    public CustomContainer1(Context context) {
        super(context);
    }

    public CustomContainer1(Context context, AttributeSet attrs) {
        super(context, attrs);
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

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int measuredChildWidth = 0;
        int measuredChildHeight = 0;

        int topChildWidth = 0;
        int bottomChildWidth = 0;
        int leftChildHeight = 0;
        int rightChildheight = 0;

        int childCount = getChildCount();

        MarginLayoutParams mParams = null;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measuredChildWidth = childView.getMeasuredWidth();
            measuredChildHeight = childView.getMeasuredHeight();
            mParams = (MarginLayoutParams) childView.getLayoutParams();
            if (i == 0 || i == 1) {
                topChildWidth += measuredChildWidth + mParams.getMarginStart() + mParams.getMarginEnd();
                Log.d("zhulf", "====topChildWidth==" + topChildWidth + " ====measuredChildWidth: " + measuredChildWidth);
            }

            if (i == 2 || i == 3) {
                bottomChildWidth += measuredChildWidth + mParams.getMarginStart() + mParams.getMarginEnd();
                Log.d("zhulf", "======bottomChildWidth==" + leftChildHeight + " ======measuredChildWidth: " + measuredChildWidth);
            }

            if (i == 0 || i == 2) {
                leftChildHeight += measuredChildHeight + mParams.topMargin + mParams.bottomMargin;
                //Log.d("zhulf", "======leftChildHeight==" + leftChildHeight + " ======measuredChildHeight: " + measuredChildHeight);
            }

            if (i == 1 || i == 3) {
                rightChildheight += measuredChildHeight + mParams.topMargin + mParams.bottomMargin;
            }
            width = Math.max(topChildWidth, bottomChildWidth);
            height = Math.max(leftChildHeight, rightChildheight);
        }
        int viewGroupWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : width;
        int viewGroupHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : height;
        Log.d("zhulf", "====width==" + width + " ====height: " + height);
        Log.d("zhulf", "====viewGroupWidth==" + viewGroupWidth + " ====viewGroupHeight: " + viewGroupHeight);
        setMeasuredDimension(viewGroupWidth, viewGroupHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams mParams = null;
        int measuredChildWidth = 0;
        int measuredChildHeight = 0;
        int tempLeft = 0;
        int firstWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            mParams = (MarginLayoutParams) childView.getLayoutParams();
            measuredChildWidth = childView.getMeasuredWidth();
            measuredChildHeight = childView.getMeasuredHeight();
            Log.d("zhulf", "==measuredChildWidth==" + measuredChildWidth
                    + " measuredChildHeight: " + measuredChildHeight);
            int childLeft = 0;
            int childTop = 0;
            int childRight = 0;
            int childBottom = 0;

            switch (i) {
                case 0:
                    childLeft = mParams.getMarginStart();
                    childTop = mParams.topMargin;
                    Log.d("zhulf", "==001=childLeft==" + childLeft + " childTop==" + childTop);
                    break;

                case 1:
                    childLeft = getMeasuredWidth() - mParams.rightMargin - measuredChildWidth;
                    childTop = mParams.topMargin;
                    Log.d("zhulf", "==002=childLeft==" + childLeft + " childTop==" + childTop);
                    break;

                case 2:
                    childLeft = mParams.leftMargin;
                    childTop = getMeasuredHeight() - measuredChildHeight - mParams.bottomMargin;
                    Log.d("zhulf", "==003=childLeft==" + childLeft + " childTop==" + childTop);
                    break;

                case 3:
                    childLeft = getMeasuredWidth() - mParams.rightMargin - measuredChildWidth;
                    childTop = getMeasuredHeight() - measuredChildHeight - mParams.bottomMargin;
                    Log.d("zhulf", "==004=childLeft==" + childLeft + " childTop==" + childTop);
                    break;

            }

            childRight = childLeft + measuredChildWidth;
            childBottom = childTop + measuredChildHeight;
            childView.layout(childLeft, childTop, childRight, childBottom);
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        Log.d("zhulf", "==2120==");
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        Log.d("zhulf", "==2121==");
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        Log.d("zhulf", "==2122==");
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
