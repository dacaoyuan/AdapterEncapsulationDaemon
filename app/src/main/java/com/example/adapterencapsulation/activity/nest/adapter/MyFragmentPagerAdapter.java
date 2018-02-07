package com.example.adapterencapsulation.activity.nest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.adapterencapsulation.activity.nest.fragment.FragmentOne;
import com.example.adapterencapsulation.activity.nest.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanpk on 2018/1/16.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentLis = new ArrayList<>();
    private List<String> titles = new ArrayList<>();


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentLis.add(FragmentOne.getFragmentOne());
        fragmentLis.add(FragmentTwo.getFragmentTwo());

        initTitle();
    }

    private void initTitle() {
        titles.add("标题一");
        titles.add("标题二");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLis.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLis.size();
    }
}
