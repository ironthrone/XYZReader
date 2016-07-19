package com.example.xyzreader.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/7/15.
 */
public class HideBehavior extends CoordinatorLayout.Behavior {
    private  Context mContext;
    private final DisplayMetrics displayMetrics;
    //初始的y值，在第一次嵌套滑动之前
    private float initY;


    public HideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        if(initY == 0){

        initY = child.getY();
        }
        return true;
    }


    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        float newY = child.getY() + dyConsumed/2;
        if(dyConsumed >0){

            ViewCompat.animate(child)
                    .setDuration(300)
                    .translationY(displayMetrics.heightPixels)
            .start();
        }else {
            ViewCompat.animate(child)
                    .setDuration(300)
                    .translationY(0)
                    .start();
        }
    }

}
