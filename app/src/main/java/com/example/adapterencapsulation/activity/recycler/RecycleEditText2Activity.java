package com.example.adapterencapsulation.activity.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.EditText2Adapter;
import com.example.adapterencapsulation.bean.DemoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试发现，当列表的item包含一个编辑框是，采用这种方法，没问题。包含多个编辑框时，还是会出现错乱的
 */

public class RecycleEditText2Activity extends AppCompatActivity implements EditText2Adapter.OnItemEditTextChangedListener {
    private static final String TAG = "RecyclerViewEditTextAct";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<DemoBean> mDemoBeanList;

    private EditText2Adapter mDemoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_edit_text2);
        ButterKnife.bind(this);

        mDemoListAdapter = new EditText2Adapter();
        mDemoListAdapter.setListener(this);
        mRecyclerView.setAdapter(mDemoListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        showData();
    }

    private void showData() {
        mDemoBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDemoBeanList.add(new DemoBean(i, "name" + i));
        }
        mDemoListAdapter.setNewData(mDemoBeanList);
    }

    @Override
    public void onEditTextAfterTextChanged(Editable editable, int position) {

        DemoBean demoBean = mDemoBeanList.get(position);
        demoBean.setAge(editable.toString());
        Log.i(TAG, "onEditTextAfterTextChanged: position=" + position + " Age=" + editable.toString());
    }
}
