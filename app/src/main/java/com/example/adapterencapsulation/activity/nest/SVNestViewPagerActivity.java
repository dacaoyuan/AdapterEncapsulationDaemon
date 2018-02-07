package com.example.adapterencapsulation.activity.nest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.activity.nest.adapter.MyViewPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 竖直ScrollView嵌套，水平滑动viewPager(一)
 * <p>
 * 通过源码发现：viewPager内部已做处理，当时水平滑动时，viewPager会请求父控件，不要拦截事件。
 * <p>
 * ScrollView 嵌套 ViewPager 不能显示解决办法:
 * 方案一：给ScrollView添加如下属性：  android:fillViewport="true"
 * 顾名思义，这个属性允许 ScrollView中的组件去充满它。
 * <p>
 * 方案二：自定义ViewPager 重写ViewPager测量方法（推荐）
 */
public class SVNestViewPagerActivity extends AppCompatActivity {

    private static final int[] imageViews = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    MyViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_nest_view_pager);
        ButterKnife.bind(this);
        viewPagerAdapter = new MyViewPagerAdapter(this, getData());
        viewPager.setAdapter(viewPagerAdapter);
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

    /**
     * 初始化界面
     */
    private ArrayList<ImageView> getData() {
        ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imag = new ImageView(this);
            imag.setBackgroundResource(imageViews[i]);
            imageViewArrayList.add(imag);
        }
        return imageViewArrayList;
    }
}

