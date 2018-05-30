package com.example.adapterencapsulation.activity.nest.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.MyRecyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanpk on 2018/1/16.
 */

public class FragmentOne extends Fragment {
    private static final String TAG = "FragmentOne";
    private RecyclerView recyclerView;

    MyRecyAdapter recyAdapter;

    public static FragmentOne getFragmentOne() {
        FragmentOne fragmentOne = new FragmentOne();
       /* Bundle bundle=new Bundle();
        bundle.putString("userId","");
        fragmentOne.setArguments(bundle);*/
        return fragmentOne;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentone, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        initView();
        return view;
    }

    TabLayout tabLayout;
    private boolean isFirstUpSlide = true;
    private boolean isFirstDownSlide = true;

    private boolean isUpSlide;
    private boolean isDownSlide;

    private GestureDetectorCompat mDetectorCompat;

    private void initView() {
        recyAdapter = new MyRecyAdapter(getData(), getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        //要通过懒加载，去获取父view的控件
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);

        /*mDetectorCompat = new GestureDetectorCompat(getActivity(), new MyGestureListener());
        mDetectorCompat.setIsLongpressEnabled(false);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetectorCompat.onTouchEvent(event);
                return false;
            }
        });*/

    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("i+" + i);
        }
        return list;

    }


    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(TAG, "onScroll: distanceX=" + distanceX + " distanceY=" + distanceY);

            if (Math.abs(distanceY) > Math.abs(distanceX)) {//判断是否竖直滑动
                //是否向下滑动
                boolean isScrollDown = e1.getRawY() < e2.getRawY() ? true : false;

                //boolean isScrollDown = distanceY < 0 ? true : false;

                if (isScrollDown) {
                    Log.i(TAG, "onScroll: 下滑");
                    isFirstUpSlide = true;
                    if (isFirstDownSlide) {
                        final int height = tabLayout.getHeight();
                        Log.i(TAG, "onScrolled: 下滑 height=" + height);


                        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, height);
                        anim.setFillAfter(true);
                        anim.setDuration(100);
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                tabLayout.clearAnimation();
                                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) tabLayout.getLayoutParams();
                                lp.topMargin = tabLayout.getTop() + height;
                                tabLayout.setLayoutParams(lp);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        tabLayout.startAnimation(anim);
                        isFirstDownSlide = false;
                    }
                } else {
                    Log.i(TAG, "onScroll: 上滑");
                    isFirstDownSlide = true;
                    if (isFirstUpSlide) {
                        final int height = tabLayout.getHeight();
                        Log.i(TAG, "onScrolled: 上滑 height=" + height);


                        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, -height);
                        anim.setFillAfter(true);
                        anim.setDuration(200);
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                tabLayout.clearAnimation();
                                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) tabLayout.getLayoutParams();
                                lp.topMargin = tabLayout.getTop() - height;
                                tabLayout.setLayoutParams(lp);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        tabLayout.startAnimation(anim);
                        isFirstUpSlide = false;
                    }

                }


            }


            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

}
