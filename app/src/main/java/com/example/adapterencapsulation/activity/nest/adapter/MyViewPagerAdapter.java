package com.example.adapterencapsulation.activity.nest.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by yuanpk on 2018/1/16.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private Context context;
    ArrayList<ImageView> imageViewArrayList;

    public MyViewPagerAdapter(Context context, ArrayList<ImageView> imageViewArrayList) {
        this.context = context;
        this.imageViewArrayList = imageViewArrayList;
    }


    @Override
    public int getCount() {
        return imageViewArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViewArrayList.get(position);
        container.addView(imageView);
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
