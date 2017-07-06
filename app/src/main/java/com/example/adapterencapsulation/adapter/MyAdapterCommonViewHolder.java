package com.example.adapterencapsulation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.bean.StateBean;
import com.example.adapterencapsulation.utils.CommonAdapter;
import com.example.adapterencapsulation.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhyantai on 2017/6/30.
 */

public class MyAdapterCommonViewHolder extends CommonAdapter<StateBean> {


    private List<Integer> mPos = new ArrayList<>();

    public MyAdapterCommonViewHolder(List<StateBean> mDatas, Context mContext) {
        super(mContext, mDatas, R.layout.item_lv);
    }


    @Override
    public void convert(final ViewHolder holder, final StateBean stateBean) {

        holder.setText(R.id.textView, stateBean.getTest());

        final CheckBox checkBox = holder.getView(R.id.checkbox);
        checkBox.setChecked(stateBean.position);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateBean.setPosition(checkBox.isChecked());
            }
        });

        if (stateBean.isDisplay) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.INVISIBLE);
        }



        /*checkBox.setChecked(false);
        if (mPos.contains(holder.getPosition())) {
            checkBox.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stateBean.setPosition(checkBox.isChecked());
                if (checkBox.isChecked()) {
                    mPos.add(holder.getPosition());
                } else {
                    mPos.remove((Integer) holder.getPosition());
                }

            }
        });*/

    }


}
