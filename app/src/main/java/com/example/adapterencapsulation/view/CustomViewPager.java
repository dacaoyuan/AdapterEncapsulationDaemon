package com.example.adapterencapsulation.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.adapterencapsulation.activity.nest.SVNestViewPager2Activity;

/**
 * Created by yuanpk on 2018/1/16.
 * <p>
 * 重写ViewPager测量方法
 * <p>
 * 如果viewPager中填充的是图片或文本等等。
 * 它会测试测量图片或文本的高度，这样，最终viewpager的高度，取决于图片或文本的高度
 */

public class CustomViewPager extends ViewPager {


    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        Log.i(SVNestViewPager2Activity.TAG, "onMeasure: ");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }


   /* @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(SVNestViewPager2Activity.TAG, "onSizeChanged");
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i(SVNestViewPager2Activity.TAG, "onLayout");

    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.i(SVNestViewPager2Activity.TAG, "onWindowFocusChanged=" + hasWindowFocus);

    }*/


}
