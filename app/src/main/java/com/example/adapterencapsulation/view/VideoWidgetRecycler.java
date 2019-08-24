package com.example.adapterencapsulation.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.bean.CourseListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YuanPeikai
 * @CreateDate: 2019/8/23 16:55
 * @Description:
 */
public class VideoWidgetRecycler extends RecyclerView {
    private static final String TAG = "VideoWidgetRecycler";
    private Context mContext;

    public VideoWidgetRecycler(@NonNull Context context) {
        super(context, null);
    }

    public VideoWidgetRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public VideoWidgetRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private void initView() {


    }


    public void setData(List<CourseListBean> courseDataList) {
        if (courseDataList == null || courseDataList.size() == 0) {
            return;
        }

        GridLayoutManager gridLayoutManager = null;
        int size = courseDataList.size();

        switch (size) {
            case 1:
            case 2:
            case 3:
                gridLayoutManager = new GridLayoutManager(mContext, size);
                break;
            default:
                gridLayoutManager = new GridLayoutManager(mContext, 2);
                break;
        }
        setLayoutManager(gridLayoutManager);
        //int dp_6 = mContext.getResources().getDimensionPixelSize(R.dimen.dp_10);
        // SpaceItemDecoration dividerDecoration = new SpaceItemDecoration(15, size);
        // addItemDecoration(dividerDecoration);

        VideoAdapter videoAdapter = new VideoAdapter(getData(size));
        setAdapter(videoAdapter);
        videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i(TAG, "onItemClick: position=" + position);
            }
        });


    }


    private class VideoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public VideoAdapter(@Nullable List<String> data) {
            super(R.layout.item_multiple_child, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

            ImageView imageView = helper.getView(R.id.imageview);
            switch (getItemCount()) {


            }


        }
    }

    public List<String> getData(int size) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            strings.add("i" + i);
        }

        return strings;
    }

}
