package com.example.adapterencapsulation.activity.nest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.activity.nest.adapter.MyViewPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuanpk on 2018/1/16.
 */

public class FragmentThree extends Fragment {
    private static final String TAG = "FragmentThree";
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    Unbinder unbinder;

    private static final int[] imageViews = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };


    public static FragmentThree getFragmentThree() {
        FragmentThree fragmentThree = new FragmentThree();
        return fragmentThree;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmentthree, null);
        unbinder = ButterKnife.bind(this, view);

        initView();

        return view;
    }

    MyViewPagerAdapter viewPagerAdapter;

    private void initView() {

        viewPagerAdapter = new MyViewPagerAdapter(getActivity(), getData());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
              /*  Log.i(TAG, "onPageScrolled: position=" + position);
                if (position == getData().size() - 2) {
                    Log.i(TAG, "onPageScrolled: 请求父控件拦截滑动事件。");
                    mViewPager.getParent().requestDisallowInterceptTouchEvent(false);//请求父控件拦截滑动事件。
                }else {

                }*/

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    /**
     * 初始化界面
     */
    private ArrayList<ImageView> getData() {
        ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imag = new ImageView(getActivity());
            imag.setBackgroundResource(imageViews[i]);
            imageViewArrayList.add(imag);
        }
        return imageViewArrayList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
