package com.example.adapterencapsulation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 滚动布局嵌套listView
 * 1:listView 只显示一行的处理办法
 * 方案一：setListViewHeightBasedOnChildren(listView);
 * 方案二：自定义可适应ScrollView的ListView
 */
public class ScrollViewNestListActivity extends AppCompatActivity {
    private static final String TAG = "ScrollViewNestListActiv";
    @BindView(R.id.listView)
    ListView listView;
    private MyListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_nest_list);
        ButterKnife.bind(this);

        listAdapter = new MyListAdapter(getDate(), this);
        listView.setAdapter(listAdapter);

        listView.addFooterView(getFootView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                strings.add("测试kkk");
                listAdapter.setNewsData(strings);
                //setListViewHeightBasedOnChildren(listView);
            }
        }));
        //setListViewHeightBasedOnChildren(listView);
    }

    public View getFootView(View.OnClickListener listener) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_foot_view, (ViewGroup) listView, false);
        view.setOnClickListener(listener);
        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }


    List<String> strings = new ArrayList<>();

    public List<String> getDate() {
        for (int i = 0; i < 30; i++) {
            strings.add("列表= " + i);
        }
        return strings;
    }
}
