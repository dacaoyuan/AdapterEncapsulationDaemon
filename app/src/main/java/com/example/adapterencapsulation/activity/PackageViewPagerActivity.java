package com.example.adapterencapsulation.activity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.pagerapdapter.CommonViewPagerAdapter;
import com.example.adapterencapsulation.pagerapdapter.ViewPagerHolder;
import com.example.adapterencapsulation.pagerapdapter.ViewPagerHolderCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackageViewPagerActivity extends AppCompatActivity {
    private final static int[] imageId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    @BindView(R.id.vp)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_view_pager);
        ButterKnife.bind(this);

       /* CommonViewPagerAdapter pagerAdapter = new CommonViewPagerAdapter(getDate(), new ViewPagerHolderCreator<ViewImageHolder>() {
            @Override
            public ViewPagerHolder createViewHolder() {
                return new ViewImageHolder();
            }
        });*/

        CommonViewPagerAdapter pagerAdapter = new CommonViewPagerAdapter(getDate(), new ViewPagerHolderCreato());

        viewPager.setAdapter(pagerAdapter);


    }


    public class ViewPagerHolderCreato implements ViewPagerHolderCreator<ViewImageHolder>{

        @Override
        public ViewImageHolder createViewHolder() {
            return  new ViewImageHolder();
        }
    }







    public class ViewImageHolder implements ViewPagerHolder<ImageView> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            return imageView;
        }

        @Override
        public void onBind(Context context, int position, ImageView data) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    List<ImageView> imageViews = new ArrayList<>();

    private List<ImageView> getDate() {

        ImageView imageView;
        for (int i = 0; i < imageId.length; i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageId[i]);
            imageViews.add(imageView);
        }
        return imageViews;
    }
}
