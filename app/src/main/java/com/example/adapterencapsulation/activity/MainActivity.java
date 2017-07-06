package com.example.adapterencapsulation.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.adapterencapsulation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        DisplayMetrics metrics = new DisplayMetrics();
        /**
         * getRealMetrics - 屏幕的原始尺寸，即包含状态栏。
         * version >= 4.2.2
         */
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        System.out.println("MainActivity.onCreate width=" + width + " height=" + height);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, PagerActivity.class));
                break;
        }
    }
}
