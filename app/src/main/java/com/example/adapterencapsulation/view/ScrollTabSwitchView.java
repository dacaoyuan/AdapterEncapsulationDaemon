package com.example.adapterencapsulation.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.support.design.widget.TabLayout;


import com.example.adapterencapsulation.R;

import java.util.List;

/**
 * Created by yuanpk on 2018/8/24  15:08
 * <p>
 * Description:TODO
 */
public class ScrollTabSwitchView extends LinearLayout {
    private static final String TAG = "ScrollTabSwitchView";

    private int mTab1Height;
    private int mTab2Height;
    private int mTab3Height;

    private boolean isTab1Lock = false;//默认不锁
    private boolean isTab2Lock = false;//默认不锁
    private boolean isTab3Lock = false;//默认不锁

    private boolean isTabSelected;
    private boolean isScrollChanged;


    public ScrollTabSwitchView(Context context) {
        this(context, null);
    }

    public ScrollTabSwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    private TabLayout tableLayout;
    private CustomScrollView customScrollView;


    LinearLayout mLinearTab1;
    LinearLayout mLinearTab2;
    LinearLayout mLinearTab3;

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mTab1Height = mLinearTab1.getHeight();
        mTab2Height = mLinearTab2.getHeight();
        mTab3Height = mLinearTab3.getHeight();
        Log.i(TAG, "onWindowFocusChanged: mTab1Height=" + mTab1Height + " mTab2Height=" + mTab2Height);


    }

    public void initTabSwitchView(Context context, View rootView, List<String> tabName) {
        if (rootView == null || tabName == null) {
            throw new IllegalArgumentException("rootView is null or tabName is null");
        }

        mLinearTab1 = (LinearLayout) rootView.findViewById(R.id.linear_tab1);
        mLinearTab2 = (LinearLayout) rootView.findViewById(R.id.linear_tab2);
        mLinearTab3 = (LinearLayout) rootView.findViewById(R.id.linear_tab3);
       /* mLinearTab1.post(new Runnable() {
            @Override
            public void run() {

            }
        });*/


        tableLayout = new TabLayout(context);
        tableLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < tabName.size(); i++) {
            if (i == 0) {
                tableLayout.addTab(tableLayout.newTab().setText(tabName.get(i)), true);
            } else {
                tableLayout.addTab(tableLayout.newTab().setText(tabName.get(i)));
            }

        }


        customScrollView = new CustomScrollView(context);
        customScrollView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);

        linearLayout.addView(rootView);


        customScrollView.addView(linearLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        addView(tableLayout, 0);
        addView(customScrollView, 1);


        initListener();


    }


    private void initListener() {
        customScrollView.addOnScrollListener(new CustomScrollView.OnScrollListener() {
            @Override
            public void onScrollChangedListener(int t) {
                // Log.i(TAG, "onScrollChangedListener: t=" + t);
                if (!isTabSelected) {
                    isScrollChanged = true;


                    if (t > mTab1Height + mTab2Height) {//tab3
                        isTab2Lock = false;//tab2解锁
                        isTab1Lock = false;//tab1解锁
                        if (!isTab3Lock) {
                            tableLayout.getTabAt(2).select();
                            Log.i(TAG, "onScrollChangedListener: tab3加锁");
                            isTab3Lock = true;//tab3加锁
                        }


                    } else if (t > mTab1Height) {//tab2
                        isTab1Lock = false;//tab1解锁
                        isTab3Lock = false;//tab3解锁
                        if (!isTab2Lock) {
                            tableLayout.getTabAt(1).select();
                            Log.i(TAG, "onScrollChangedListener: tab2加锁");
                            isTab2Lock = true;//tab2加锁
                        }

                    } else if (t > 0) {//tab1
                        isTab2Lock = false;//tab2解锁
                        isTab3Lock = false;//tab3解锁
                        if (!isTab1Lock) {
                            tableLayout.getTabAt(0).select();
                            Log.i(TAG, "onScrollChangedListener: tab1加锁");
                            isTab1Lock = true;//tab1加锁
                        }


                    }


                    isScrollChanged = false;

                }


            }
        });


        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                isTabSelected = true;
                Log.i(TAG, "onTabSelected: position=" + tab.getPosition());
                if (!isScrollChanged) {
                    int position = tab.getPosition();
                    switch (position) {
                        case 0:
                            customScrollView.scrollTo(0, 0);
                            break;
                        case 1:
                            customScrollView.scrollTo(0, mTab1Height);
                            break;
                        case 2:
                            customScrollView.scrollTo(0, mTab1Height + mTab2Height);
                            break;
                    }
                }


                isTabSelected = false;


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

   /*  public void initLayoutHeight(int mTab1Height, int mTab2Height, int mTab3Height) {
        this.mTab1Height = mTab1Height;
        this.mTab2Height = mTab2Height;
        this.mTab3Height = mTab3Height;
    }

    private List<Integer> heightList;
   public void initLayoutHeight(List<Integer> heightList) {
        if (heightList == null) {
            throw new IllegalArgumentException("heightList is null");
        }
        this.heightList = heightList;
    }*/

}
