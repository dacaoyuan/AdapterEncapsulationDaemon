package com.example.adapterencapsulation.activity.scrollview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.view.CustomScrollView;
import com.example.adapterencapsulation.view.ScrollTabSwitchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollSwitchActivity extends AppCompatActivity {
    private static final String TAG = "ScrollSwitchActivity";

    @BindView(R.id.scrollTabSwitchView)
    ScrollTabSwitchView mScrollTabSwitchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_swich);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.layout_scroll_content, null);
        List<String> nameList = new ArrayList<>();
        nameList.add("开放时间");
        nameList.add("推荐玩法");
        nameList.add("图文详情");
        mScrollTabSwitchView.initTabSwitchView(this, rootView, nameList);

    }


}
