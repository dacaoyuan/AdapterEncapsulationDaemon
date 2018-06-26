package com.example.adapterencapsulation.activity.nest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.activity.nest.adapter.MyFragmentPagerAdapter2;
import com.example.adapterencapsulation.activity.nest.fragment.FragmentFour;
import com.example.adapterencapsulation.activity.nest.fragment.FragmentOne;
import com.example.adapterencapsulation.activity.nest.fragment.FragmentThree;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SVnestVPnestVPActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    MyFragmentPagerAdapter2 pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svnest_vpnest_vp);
        ButterKnife.bind(this);

        pagerAdapter = new MyFragmentPagerAdapter2(getSupportFragmentManager(), getFragmentList());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> fragmentLis = new ArrayList<>();

        fragmentLis.add(FragmentFour.getFragmentFour());
        fragmentLis.add(FragmentOne.getFragmentOne());

        return fragmentLis;
    }

}
