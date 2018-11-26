package com.zhulf.myandroidpractise;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FlowLayout extends ViewGroup {
    private List<Integer> lineHeightList = new ArrayList<Integer>();
    private List<List<View>> allViewList = new ArrayList<List<View>>();
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        /**
         * 每一行的宽度
         */
        int lineWidth = 0;

        /**
         * 每一行的高度
         */
        int lineHeight = 0;

        int childCount = getChildCount();

        for(int i = 0;i < childCount;i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            int childHeight = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

            if(lineWidth + childWidth > widthSize) {
                width = Math.max(lineWidth,childWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(childHeight,lineHeight);
            }

            if(i == childCount - 1) {
                width = Math.max(childWidth,lineWidth);
                height += lineHeight;
            }
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        allViewList.clear();
        lineHeightList.clear();

        int childCount = getChildCount();
        List<View> lineViewList = new ArrayList<View>();

        /**
         * 每一行的宽度
         */
        int lineWidth = 0;

        /**
         * 每一行的高度
         */
        int lineHeight = 0;

        for(int i = 0; i < childCount;i++) {
            View child = getChildAt(i);
            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if(lineWidth + childWidth + mlp.leftMargin + mlp.rightMargin > getWidth()) {
                lineHeightList.add(lineHeight);
                allViewList.add(lineViewList);
                lineWidth = 0;
                lineViewList = new ArrayList<View>();
            }

            lineWidth += childWidth + mlp.leftMargin + mlp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + mlp.topMargin + mlp.bottomMargin);
            lineViewList.add(child);
        }
        lineHeightList.add(lineHeight);
        allViewList.add(lineViewList);

        int left = 0;
        int top = 0;

        for(int i = 0;i<allViewList.size();i++) {
            lineViewList = allViewList.get(i);
            lineHeight = lineHeightList.get(i);
            Log.d("zhulf","第" + i + "行子元素个数 ：" + lineViewList.size());
            Log.d("zhulf", "第" + i + "行行高， ：" + lineHeight);

            for(int j = 0; j < lineViewList.size();j++) {
                View child = lineViewList.get(j);
                if(child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
                int childLeft = left + mlp.leftMargin;
                int childTop = top + mlp.topMargin;
                int childRight = childLeft + child.getMeasuredWidth();
                int childBottom = childTop + child.getMeasuredHeight();

                Log.d("zhulf","===childLeft==" + childLeft + ", childTop==" + childTop +
                        " childRight==" + childRight + " childBottom==" + childBottom);
                child.layout(childLeft,childTop,childRight,childBottom);
                left += child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
