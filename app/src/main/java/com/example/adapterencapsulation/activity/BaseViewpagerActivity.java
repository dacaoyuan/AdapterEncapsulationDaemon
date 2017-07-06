package com.example.adapterencapsulation.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewPager 的基本用法
 */
public class BaseViewpagerActivity extends AppCompatActivity {

    private final static int[] imageId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @BindView(R.id.vp)
    ViewPager viewPager;

    private MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);

        pagerAdapter = new MyPagerAdapter(getDate());
        viewPager.setAdapter(pagerAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private List<ImageView> getDate() {
        List<ImageView> imageViews = new ArrayList<>();
        ImageView imageView;
        for (int i = 0; i < imageId.length; i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageId[i]);
            imageViews.add(imageView);
        }
        return imageViews;
    }
}
