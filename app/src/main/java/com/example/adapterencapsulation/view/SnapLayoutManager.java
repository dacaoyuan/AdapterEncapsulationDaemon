package com.example.adapterencapsulation.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

/**
 * @Author: YuanPeikai
 * @CreateDate: 2019/8/31 8:35
 * @Description:
 */
public class SnapLayoutManager extends LinearLayoutManager {
    private PagerSnapHelper mPagerSnapHelper;

    public SnapLayoutManager(Context context) {
        super(context, VERTICAL, false);
        init();
    }

    public SnapLayoutManager(Context context, int orientation) {
        super(context, orientation, false);
        init();
    }

    public SnapLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    private void init() {
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mPagerSnapHelper.attachToRecyclerView(view);
    }


}
