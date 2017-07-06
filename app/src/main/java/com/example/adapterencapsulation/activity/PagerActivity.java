package com.example.adapterencapsulation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.adapterencapsulation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PagerActivity extends AppCompatActivity {

    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button4, R.id.button5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button4:
                startActivity(new Intent(PagerActivity.this, BaseViewpagerActivity.class));

                break;
            case R.id.button5:
                startActivity(new Intent(PagerActivity.this, PackageViewPagerActivity.class));
                break;
        }
    }
}
