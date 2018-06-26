package com.example.adapterencapsulation.view;


import android.content.Context;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


/**
 * Created by yuanpk on 2018/6/26.
 * <p>
 * 经过测试发现（用的是25.3.1的依赖包，低于这个版本是否为如下的情况，未测试，但是高于这个版本肯定是没问题的）
 * 在ViewPager nest ViewPager 情况下，默认官方源码是已经做了，冲突处理的。子ViewPager是可以正常滑动的，
 * 只有 贴边滑动 子ViewPager时，父ViewPager才会拦截 子ViewPager 的滑动事件！到此，其实官方对这种情况下的，滑动冲突处理，已经很完善了。
 * <p>
 * <p>
 * 但是，如果你不想 贴边滑动 子ViewPager时，让父ViewPager拦截 子ViewPager 的滑动事件，
 * 而是想，只有 子ViewPager滑动到最后一页时， 父ViewPager才会拦截子ViewPager 的滑动事件
 * 如你可以进行如下处理，看下面的代码：
 */

public class ChildViewPager2 extends ViewPager {

    private static final String TAG = "ChildViewPager2";

    public ChildViewPager2(Context context) {
        super(context);
    }

    public ChildViewPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //private int minSlidingDistance = 160;//最小滑动距离
    private int lastX;
    private int lastY;

    private VelocityTrackerCompat mVelocityTrackerCompat;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int curPosition;

        //获取到手指处的横坐标和纵坐标
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(false);
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offX = x - lastX;
                int offY = y - lastY;

                curPosition = this.getCurrentItem();
                int count = this.getAdapter().getCount();
                //Log.i(TAG, "curPosition:=" + curPosition);
                // 当当前页面在最后一页和第0页的时候，由父亲拦截触摸事件
                if (curPosition == count - 1) { // || curPosition == 0
                    Log.i(TAG, "dispatchTouchEvent: 由父亲拦截触摸事件");
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {//其他情况，由孩子拦截触摸事件
                    if (Math.abs(offY) > Math.abs(offX)) {
                        Log.i(TAG, "dispatchTouchEvent: 竖直滑动");
                        getParent().requestDisallowInterceptTouchEvent(false);//父控件拦截
                    } else {
                        Log.i(TAG, "dispatchTouchEvent: 左右滑动");
                        getParent().requestDisallowInterceptTouchEvent(true);//父控件不拦截
                    }

                  /*  if (Math.abs(offY) > minSlidingDistance) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }*/
                }

                break;


        }
        return super.dispatchTouchEvent(ev);
    }


}
