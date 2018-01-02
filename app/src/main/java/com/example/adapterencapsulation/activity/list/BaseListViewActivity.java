package com.example.adapterencapsulation.activity.list;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
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
    private static final String TAG = "BaseListViewActivity";
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


        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i(TAG, "onScrollStateChanged: scrollState=" + scrollState);
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Snackbar.make(view, "触摸滚动", Snackbar.LENGTH_LONG).show();
                        Log.i(TAG, "onScrollStateChanged:触摸滚动 ");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Snackbar.make(view,"停止滚动",Snackbar.LENGTH_LONG)
                                .setAction("手否收藏", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(BaseListViewActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .show();
                        Log.i(TAG, "onScrollStateChanged: 滚动状态闲置");//停止滚动
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Snackbar.make(view,"滚动状态扔",Snackbar.LENGTH_LONG).show();
                        Log.i(TAG, "onScrollStateChanged: 滚动状态扔");//手指做了抛的动作（手指离开屏幕前，用力滑了一下）
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i(TAG, "onScrollStateChanged: firstVisibleItem=" + firstVisibleItem + " visibleItemCount=" + visibleItemCount + " totalItemCount=" + totalItemCount);
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
