package com.example.adapterencapsulation.activity.recycler;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.adapter.MyRecyAdapter;
import com.example.adapterencapsulation.view.SnapLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecycleView 的基本用法+PagerSnapHelper的基本用法
 */
public class RecycleViewActivity extends AppCompatActivity {
    private static final String TAG = "RecycleViewActivity";
    @BindView(R.id.recy)
    RecyclerView recyclerView;

    private MyRecyAdapter recyAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);

        mLayoutManager = new SnapLayoutManager(this, OrientationHelper.VERTICAL);
        //mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyAdapter = new MyRecyAdapter(getDate(), this);
        recyclerView.setAdapter(recyAdapter);

        recyAdapter.setOnItemClickListener(new MyRecyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, "onItemLongClick", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i(TAG, "onScrollStateChanged: newState=" + newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Log.i(TAG, "onScrollStateChanged: 滚动状态拖");
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Log.i(TAG, "onScrollStateChanged: 滚动状态闲置");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Log.i(TAG, "onScrollStateChanged: 滚动状态解决");
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i(TAG, "onScrolled: dx=" + dx + " dy=" + dy);

                if (dy > 0) {
                    Log.i(TAG, "onScrolled sliding: 上滑");
                } else {
                    Log.i(TAG, "onScrolled sliding: 下滑");
                }

                //方案一：判断是否都顶部或底部（测试可行）
                if (!recyclerView.canScrollVertically(-1)) {
                    Log.i(TAG, "onScrolled sliding33: 到顶部啦");
                    Snackbar.make(recyclerView, "到顶部啦", Snackbar.LENGTH_LONG).show();
                } else if (!recyclerView.canScrollVertically(1)) {
                    Log.i(TAG, "onScrolled sliding33: 到底部了");
                    Snackbar.make(recyclerView, "到底部了", Snackbar.LENGTH_LONG).show();
                }


                //方案二：判断是否都顶部或底部（测试可行）
              /*  visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem == 0) {
                    View firstVisibleItemView = mLayoutManager.getChildAt(0);
                    if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                        Log.d(TAG, "onScroll ##### 滚动到顶部 ######");//可以没问题
                    }
                }

                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = mLayoutManager.getChildAt(mLayoutManager.getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == mLayoutManager.getHeight()) {
                        Log.d(TAG, "onScroll ##### 滚动到底部 ######");//可以没问题
                    }
                }*/

                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem == 3) {
                    //RecyclerView.ViewHolder viewHolderForLayoutPosition = recyclerView.findViewHolderForLayoutPosition(3);
                    RecyclerView.ViewHolder viewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(3);
               /* int top = viewHolderForAdapterPosition.itemView.getTop();
                if (top <= 0) {
                    Log.i(TAG, "onScrolled:  第4个item到顶部啦 top=" + top);
                }*/
                    int bottom = viewHolderForAdapterPosition.itemView.getBottom();
                    if (bottom >= 0) {
                        Log.i(TAG, "onScrolled:  第4个item到顶部啦 bottom=" + bottom);
                    }
                }

            }
        });

    }

    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;

    public List<String> getDate() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("列表= " + i);
        }
        return strings;
    }
}
