package com.example.admin.viewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * 创建者：黄凯军
 * 创建时间：2016/2/22 17:48
 * 类描述：VelocityTracker
 */
public class VelocityTrackerView extends View {

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //追踪当前时间速度
        if (velocityTracker == null)
            velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        //计算速度    时间间隔为1000毫秒
        velocityTracker.computeCurrentVelocity(1000);

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //获取速度
                float xVelocity = velocityTracker.getXVelocity();
                float yVelocity = velocityTracker.getYVelocity();
                Log.i(TAG, "xVelocity: " + xVelocity + "-----yVelocity: " + yVelocity);
                break;
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        //不需要使用的时候回收
        velocityTracker.clear();
        velocityTracker.recycle();
        super.onDetachedFromWindow();
    }
    private static final String TAG = "MyView";
    private VelocityTracker velocityTracker;

    public VelocityTrackerView(Context context) {
        super(context);
    }

    public VelocityTrackerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VelocityTrackerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
