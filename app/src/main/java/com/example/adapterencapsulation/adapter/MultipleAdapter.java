package com.example.adapterencapsulation.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.bean.CourseListBean;
import com.example.adapterencapsulation.view.VideoWidgetRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YuanPeikai
 * @CreateDate: 2019/8/23 16:13
 * @Description:
 */
public class MultipleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public MultipleAdapter(@Nullable List<String> data) {
        super(R.layout.item_multiple, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        VideoWidgetRecycler video_recycler = helper.getView(R.id.video_recycler);

        List<CourseListBean> courseDataList = new ArrayList<>();
        int rodomNumber = (int) (Math.random() * 5);
        for (int i = 0; i < rodomNumber; i++) {
            courseDataList.add(new CourseListBean());
        }


        video_recycler.setData(courseDataList);


    }
}
