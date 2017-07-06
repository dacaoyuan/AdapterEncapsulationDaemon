package com.example.adapterencapsulation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ListView 的基本用法
 */
public class BaseListViewActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView lv;

    private MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);

        listAdapter = new MyListAdapter(getDate(), this);
        lv.setAdapter(listAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BaseListViewActivity.this, (String) listAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public List<String> getDate() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("列表= " + i);
        }
        return strings;
    }


}
