package com.example.adapterencapsulation.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zhyantai on 2017/6/30.
 */

public class MyPagerAdapter extends PagerAdapter {


    private List<ImageView> imageViewList;

    public MyPagerAdapter(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        container.addView(imageViewList.get(position));

        return imageViewList.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
