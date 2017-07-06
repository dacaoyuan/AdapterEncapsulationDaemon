package com.example.adapterencapsulation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adapterencapsulation.R;

import java.util.List;

/**
 * Created by zhyantai on 2017/6/30.
 */

public class MyListAdapter extends BaseAdapter {


    private List<String> stringList;
    private Context mContext;

    public MyListAdapter(List<String> stringList, Context mContext) {
        this.stringList = stringList;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_lv, null);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText((String) getItem(position));
        return convertView;
    }


    class ViewHolder {
        public TextView textView;
    }

}
