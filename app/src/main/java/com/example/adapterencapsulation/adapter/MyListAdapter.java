package com.example.adapterencapsulation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
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


    private final LayoutInflater inflater;
    private List<String> stringList;

    public MyListAdapter(List<String> stringList, Context mContext) {
        inflater = LayoutInflater.from(mContext);
        this.stringList = stringList;
    }

    public void setNewsData(List<String> data) {
        stringList = data;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return stringList == null ? 0 : stringList.size();
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
            convertView = inflater.inflate(R.layout.item_lv, parent, false);
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
