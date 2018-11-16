package com.example.adapterencapsulation.view;

/**
 * Created by yuanpk on 2018/11/16  16:07
 * <p>
 * Description:TODO
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.adapterencapsulation.R;

import java.lang.ref.WeakReference;


/**
 * 重写让ScrollView有滚动监听(23以前是没有滚动监听的)
 * 拦截touch事件，让其支持下拉放大图片
 * Created by Raye on 2016/6/11.
 */
public class ZoomScrollView extends ScrollView {

    private View zoomView;
    /**
     * 记录上次事件的Y轴
     */
    private float mLastMotionY;
    /**
     * 记录一个滚动了多少距离，通过这个来设置缩放
     */
    private int allScroll = -1;
    /**
     * 控件原本的高度
     */
    private int height = 0;
    /**
     * 被放大的控件id
     */
    private int zoomId;
    /**
     * 最大放大多少像素
     */
    private int maxZoom;
    /**
     * 滚动监听
     */
    private ScrollViewListener scrollViewListener = null;
    private UIHandler handler;
    /*private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            allScroll -= 55;//这个值越大，回弹的速度越快
            if (allScroll < 0) {
                allScroll = 0;
            }
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) zoomView.getLayoutParams();
            lp.height = (int) (height + allScroll / 2);
            zoomView.setLayoutParams(lp);
            if (allScroll != 0) {
                handler.sendEmptyMessageDelayed(1, 10);
            } else {
                allScroll = -1;
            }
        }
    };*/


    private static class UIHandler extends Handler {

        private final WeakReference<ZoomScrollView> mWeakReference;

        public UIHandler(ZoomScrollView context) {
            mWeakReference = new WeakReference<ZoomScrollView>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            ZoomScrollView context = mWeakReference.get();

            context.allScroll -= 55;//这个值越大，回弹的速度越快
            if (context.allScroll < 0) {
                context. allScroll = 0;
            }
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) context.zoomView.getLayoutParams();
            lp.height = (int) (context.height + context.allScroll / 2);
            context.zoomView.setLayoutParams(lp);
            if (context.allScroll != 0) {
                context.handler.sendEmptyMessageDelayed(1, 10);
            } else {
                context. allScroll = -1;
            }


        }
    }


    public ZoomScrollView(Context context) {
        super(context);
        init(context, null);
    }

    public ZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ZoomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ZoomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        zoomView = findViewById(zoomId);
    }

    private void init(Context context, AttributeSet attrs) {
         handler=new UIHandler(this);

        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.ObservableScrollView);
        zoomId = t.getResourceId(R.styleable.ObservableScrollView_zoomId, -1);
        maxZoom = t.getDimensionPixelOffset(R.styleable.ObservableScrollView_maxZoom, 0);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (zoomView == null || maxZoom == 0) {
            return super.dispatchTouchEvent(event);
        }

        final int action = event.getAction();

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            if (allScroll != -1) {
                handler.sendEmptyMessageDelayed(1, 10);
            }
            return super.dispatchTouchEvent(event);
        }

        switch (action) {
            case MotionEvent.ACTION_MOVE: {
                final float y = event.getY();
                final float diff, absDiff;
                diff = y - mLastMotionY;
                mLastMotionY = y;
                absDiff = Math.abs(diff);
                if (allScroll >= 0 && absDiff > 1) {
                    allScroll += diff;

                    if (allScroll < 0) {
                        allScroll = 0;
                    } else if (allScroll > maxZoom) {
                        allScroll = maxZoom;
                    }
                    Log.i("allScroll", "allScroll:" + allScroll);
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) zoomView.getLayoutParams();
                    lp.height = (int) (height + allScroll / 2);
                    zoomView.setLayoutParams(lp);
                    if (allScroll == 0) {
                        allScroll = -1;
                    }
                    return false;
                }
                if (isReadyForPullStart()) {
                    if (absDiff > 0) {
                        if (diff >= 1f && isReadyForPullStart()) {
                            mLastMotionY = y;
                            allScroll = 0;
                            height = zoomView.getHeight();
                            return true;
                        }
                    }
                }
                break;
            }


        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (allScroll != -1) {
            Log.i("ScrollView", "onTouchEvent");
            return false;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 返回是否可以开始放大
     *
     * @return
     */
    protected boolean isReadyForPullStart() {
        return getScrollY() == 0;
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface ScrollViewListener {

        void onScrollChanged(ZoomScrollView scrollView, int x, int y, int oldx, int oldy);

    }
}