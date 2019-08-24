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
    @BindView(R.id.button9)
    Button mButton9;
    @BindView(R.id.button10)
    Button button10;
    @BindView(R.id.button11)
    Button button11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button7:
                startActivity(new Intent(RecyclerActivity.this, RecycleViewActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(RecyclerActivity.this, RecycleEditTextActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(RecyclerActivity.this, RecycleEditText2Activity.class));
                break;
            case R.id.button10:
                startActivity(new Intent(RecyclerActivity.this, RecycleMultipleEditActivity.class));
                break;
            case R.id.button11:

                break;
            case R.id.button12:

                startActivity(new Intent(RecyclerActivity.this, RecyclerMultipleActivity.class));


                break;
        }
    }
}
