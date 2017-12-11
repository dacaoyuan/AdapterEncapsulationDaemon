package com.example.adapterencapsulation.activity.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.adapterencapsulation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerActivity extends AppCompatActivity {


    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button7, R.id.button8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button7:
                startActivity(new Intent(RecyclerActivity.this, RecycleViewActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(RecyclerActivity.this, RecycleEditTextActivity.class));
                break;
        }
    }
}
