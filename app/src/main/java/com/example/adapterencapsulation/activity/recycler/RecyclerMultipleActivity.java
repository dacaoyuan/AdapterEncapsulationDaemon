package com.example.adapterencapsulation.activity.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.MultipleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerMultipleActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_multiple);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {


        MultipleAdapter multipleAdapter = new MultipleAdapter(getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(multipleAdapter);


    }

    public List<String> getData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("i" + i);
        }

        return strings;
    }
}
