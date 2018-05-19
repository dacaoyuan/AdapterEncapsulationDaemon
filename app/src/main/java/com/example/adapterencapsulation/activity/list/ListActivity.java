package com.example.adapterencapsulation.activity.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.adapterencapsulation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(ListActivity.this, BaseListViewActivity.class));

                break;
            case R.id.button2:
                startActivity(new Intent(ListActivity.this, ListViewActivity.class));

                break;
            case R.id.button3:
                startActivity(new Intent(ListActivity.this, ScrollViewNestListActivity.class));

                break;
            case R.id.button4:
                startActivity(new Intent(ListActivity.this, ScrollViewNestWebViewActivity.class));

                break;
        }
    }
}
