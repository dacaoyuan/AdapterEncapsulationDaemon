package com.example.adapterencapsulation.activity.nest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.adapterencapsulation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 场景一(不同方向的)
 */
public class DiffDirectionActivity extends AppCompatActivity {


    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_direction);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(DiffDirectionActivity.this, SVNestViewPagerActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(DiffDirectionActivity.this, SVNestViewPager2Activity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(DiffDirectionActivity.this, SVNestViewPager3Activity.class));
                break;
        }
    }
}
