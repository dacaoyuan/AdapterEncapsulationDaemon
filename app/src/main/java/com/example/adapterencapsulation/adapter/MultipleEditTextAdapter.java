package com.example.adapterencapsulation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.bean.EditBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhyantai on 2017/7/5.
 * <p>
 * 方法一和方法二测试都没问题。
 */
public class MultipleEditTextAdapter extends RecyclerView.Adapter<MultipleEditTextAdapter.ViewHolder> {
    private static final String TAG = "RecycleEditTextActivity";
    private List<EditBean> stringList;
    private LayoutInflater inflater;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public MultipleEditTextAdapter(List<EditBean> stringList, Context mContext) {
        this.stringList = stringList;
        inflater = LayoutInflater.from(mContext);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        EditText edit_name;
        EditText edit_age;
        EditText address;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            edit_name = (EditText) itemView.findViewById(R.id.edit_name);
            edit_age = (EditText) itemView.findViewById(R.id.edit_age);
            address = (EditText) itemView.findViewById(R.id.address);
        }
    }


    @Override
    public MultipleEditTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: viewType=" + viewType);


        View view = inflater.inflate(R.layout.item_recy_multiple_edit_text, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: position=" + position);


        processEditText(holder.edit_name, position, 1);//姓名
        processEditText(holder.edit_age, position, 2);//年龄
        processEditText(holder.address, position, 3);//地址

    }

    //HashMap<Integer, String> contents = new HashMap<>();  //方法一
    private void processEditText(final EditText editText, final int position, final int type) {
        Log.i(TAG, "processEditText: " + stringList.size() + "  " + position);

        editText.setTag(position);
        editText.clearFocus();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: s=" + s);


            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: s=" + s);

                //方法一
                /*if (contents != null) {
                    int pos = (int) editText.getTag();
                    contents.put(pos, s.toString());
                }*/

                //方法二
                int pos = (int) editText.getTag();


                switch (type) {
                    case 1:
                        stringList.get(pos).name = s + "";
                        break;
                    case 2:
                        stringList.get(pos).age = s + "";
                        break;
                    case 3:
                        stringList.get(pos).address = s + "";
                        break;
                }


            }
        });


        //方法一
      /*  if (!TextUtils.isEmpty(contents.get(position))) {//不为空的时候 赋值给对应的edittext
            editText.setText(contents.get(position));
        } else {//置空
            editText.getEditableText().clear();
        }*/

        EditBean editBean = stringList.get(position);//方法二

        //方法二
        switch (type) {
            case 1:
                if (!TextUtils.isEmpty(editBean.name)) {
                    editText.setText(editBean.name);
                } else {
                    editText.setText("");
                }

                break;
            case 2:
                if (!TextUtils.isEmpty(editBean.age)) {
                    editText.setText(editBean.age);
                } else {
                    editText.setText("");
                }
                break;
            case 3:
                if (!TextUtils.isEmpty(editBean.address)) {
                    editText.setText(editBean.address);
                } else {
                    editText.setText("");
                }
                break;
        }


    }

    @Override
    public int getItemCount() {
        return stringList == null ? 0 : stringList.size();
    }


    public void addHearderView(View view) {

    }

    public void addFootView() {

    }

}