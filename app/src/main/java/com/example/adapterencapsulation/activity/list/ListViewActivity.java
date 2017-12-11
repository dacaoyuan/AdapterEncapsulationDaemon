package com.example.adapterencapsulation.activity.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.MyAdapterCommonViewHolder;
import com.example.adapterencapsulation.bean.StateBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ListView 的基本用法
 */
public class ListViewActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.edit_btn)
    Button editBtn;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.cancel_btn)
    Button cancelBtn;


    private MyAdapterCommonViewHolder listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);

        listAdapter = new MyAdapterCommonViewHolder(getDate(), this);
        lv.setAdapter(listAdapter);
        /*lv.setAdapter(new CommonAdapter<StateBean>(this, getDate(), R.layout.item_lv) {
            @Override
            public void convert(ViewHolder holder, StateBean stateBean) {

            }
        });*/


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    List<StateBean> strings = new ArrayList<>();

    public List<StateBean> getDate() {

        StateBean stateBean;
        for (int i = 0; i < 30; i++) {
            stateBean = new StateBean();
            stateBean.setTest("列表= " + i);

            strings.add(stateBean);
        }
        return strings;
    }


    @OnClick({R.id.edit_btn, R.id.delete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_btn:
                if (editBtn.getText().equals("全选")) {
                    editBtn.setText("全不选");
                    //list 做全选操作
                    for (int i = 0; i < strings.size(); i++) {
                        strings.get(i).position = true;
                    }

                } else if (editBtn.getText().equals("全不选")) {
                    editBtn.setText("全选");
                    //list 做全不选操作
                    for (int i = 0; i < strings.size(); i++) {
                        strings.get(i).position = false;
                    }
                }
                listAdapter.notifyDataSetChanged();

                break;
            case R.id.delete_btn:
                List<StateBean> beanArrayList = new ArrayList<>();
                StateBean bean;
                for (int i = 0; i < strings.size(); i++) {
                    bean = strings.get(i);
                    if (bean.position) {
                        beanArrayList.add(bean);
                    }
                }
                strings.removeAll(beanArrayList);
                listAdapter.notifyDataSetChanged();


                break;
        }
    }

    @OnClick(R.id.cancel_btn)
    public void onViewClicked() {
        if (cancelBtn.getText().equals("编辑")) {
            cancelBtn.setText("取消");
            for (int i = 0; i < strings.size(); i++) {
                strings.get(i).isDisplay = true;
            }
        } else if (cancelBtn.getText().equals("取消")) {
            cancelBtn.setText("编辑");
            for (int i = 0; i < strings.size(); i++) {
                strings.get(i).isDisplay = false;
            }

        }
        listAdapter.notifyDataSetChanged();
    }
}
