package com.example.adapterencapsulation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by yuanpk on 2018/1/16.
 */

public class CustomScrollView extends ScrollView {

    private static final String TAG = "CustomScrollView";

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    //返回 true 拦截  false：不拦截   super.onInterceptTouchEvent(ev)
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //Log.i(TAG, "onScrollChanged:  t=" + t + " oldt=" + oldt);
        if (mListener != null) {
            mListener.onScrollChangedListener(t);
        }


    }

    public OnScrollListener mListener;

    public interface OnScrollListener {
        void onScrollChangedListener(int t);

    }

    public void addOnScrollListener(OnScrollListener mListener) {
        this.mListener = mListener;

    }

}
