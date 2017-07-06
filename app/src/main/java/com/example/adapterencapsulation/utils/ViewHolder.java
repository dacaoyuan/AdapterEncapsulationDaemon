package com.example.adapterencapsulation.utils;

import android.content.Context;
import android.renderscript.Byte3;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhyantai on 2017/7/4.
 */

public class ViewHolder {

    private SparseArray<View> mViews;


    private int mPosition;
    private View mConvertView;


    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        // FIXME: 2017/7/5 用下面这两种方法，都报错
        //mConvertView = LayoutInflater.from(context).inflate(layoutId, parent);
        //mConvertView = View.inflate(context, layoutId, parent);
        mConvertView.setTag(this);

    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 通过viewId 获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    public View getConvertView() {
        return mConvertView;
    }


    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;

    }


    public int getPosition() {
        return mPosition;
    }

}
