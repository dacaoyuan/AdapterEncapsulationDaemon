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
 * 场景二(同方向的)
 */
public class SameDirectionActivity extends AppCompatActivity {


    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_direction);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:

                Intent intent = new Intent();
                intent.setClass(SameDirectionActivity.this, VPNestVPActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent();
                intent2.setClass(SameDirectionActivity.this, SVnestVPnestVPActivity.class);
                startActivity(intent2);
                break;
        }
    }

 /*   @OnClick(R.id.button1)
    public void onViewClicked() {



    }*/
}
