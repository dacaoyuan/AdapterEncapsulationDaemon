package com.example.adapterencapsulation.activity.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.EditTextAdapter;
import com.example.adapterencapsulation.bean.EditBean;
import com.example.adapterencapsulation.decoration.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView 包含 EditText 的情况处理
 */
public class RecycleEditTextActivity extends AppCompatActivity {
    private static final String TAG = "RecycleEditTextActivity";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private EditTextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_edit_text);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EditTextAdapter(getDate(), this);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new EditTextAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG, "onItemClick: position=" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    public View getFootView(View.OnClickListener listener) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_foot_view, (ViewGroup) recyclerView, false);
        view.setOnClickListener(listener);
        return view;
    }

    public List<EditBean> getDate() {
        List<EditBean> strings = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            EditBean editBean = new EditBean();
            editBean.textContent = "列表= " + i;
            strings.add(editBean);
        }
        return strings;
    }

}
