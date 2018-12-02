package com.zhulf.myandroidpractise;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class TimeLineItemDecoration extends RecyclerView.ItemDecoration {
    private final Paint linePaint;
    private final Paint circlePaint;
    private int paintSize = 6;
    private String paintColor = "#B8B8B8";
    private int radius = 15;

    public TimeLineItemDecoration() {
        linePaint = new Paint();
        linePaint.setStrokeWidth(paintSize);
        linePaint.setColor(Color.parseColor(paintColor));

        circlePaint = new Paint();
//        circlePaint.setStrokeWidth(6);
        circlePaint.setColor(Color.parseColor("#22aacc"));



    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        Rect rect = new Rect(200,0,0,0);
        outRect.set(rect);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int left = child.getLeft();
            int top = child.getTop();
            int bottom = child.getBottom();
            int right = child.getRight();



            int lineX = left / 2;
            int childHeight = child.getHeight();

            if(childCount == 1) {
                c.drawLine(lineX, top + childHeight / 2, lineX, top, linePaint);
            } else {
                if(i == 0) {
                    //第一个图形只有向下的直线和圆形
                    c.drawLine(lineX, top + childHeight / 2, lineX, bottom, linePaint);
                } else if(i == childCount - 1){
                    //最后一个图形只有向上的直线和圆形
                    c.drawLine(lineX, top, lineX, top + childHeight / 2, linePaint);
                } else {
                    //其余的图像既有上下直线，又有圆形
                    c.drawLine(lineX, top, lineX, top + childHeight / 2, linePaint);
                    c.drawLine(lineX, top + childHeight / 2, lineX, bottom, linePaint);
                }
            }
            c.drawCircle(lineX,top + childHeight / 2,radius,circlePaint);

        }
    }


}
