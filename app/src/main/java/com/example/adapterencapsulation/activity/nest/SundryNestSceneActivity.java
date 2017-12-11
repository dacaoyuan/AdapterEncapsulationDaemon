package com.example.adapterencapsulation.activity.nest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.adapterencapsulation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 各种控件嵌套场景
 */

public class SundryNestSceneActivity extends AppCompatActivity {


    @BindView(R.id.btn_scene1)
    Button btnScene1;
    @BindView(R.id.btn_scene2)
    Button btnScene2;
    @BindView(R.id.btn_scene3)
    Button btnScene3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sundry_nest_scene);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_scene1, R.id.btn_scene2, R.id.btn_scene3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scene1:

                break;
            case R.id.btn_scene2:

                break;
            case R.id.btn_scene3:

                break;
        }
    }
}
