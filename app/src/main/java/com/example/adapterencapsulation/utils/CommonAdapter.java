package com.example.adapterencapsulation.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.bean.StateBean;

import java.util.List;

/**
 * Created by zhyantai on 2017/7/5.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> datas;
    protected int layoutId;


    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        convert(holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);


}
