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
import java.util.HashMap;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by zhyantai on 2017/7/5.
 */
public class EditTextAdapter extends RecyclerView.Adapter<EditTextAdapter.ViewHolder> {
    private static final String TAG = "RecycleEditTextActivity";
    private List<EditBean> stringList;
    private LayoutInflater inflater;

    private List<Integer> mPos = new ArrayList<>();


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public EditTextAdapter(List<EditBean> stringList, Context mContext) {
        this.stringList = stringList;
        inflater = LayoutInflater.from(mContext);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View view;
        EditText editText;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView) itemView.findViewById(R.id.textView);
            editText = (EditText) itemView.findViewById(R.id.edit_text);
        }
    }


    @Override
    public EditTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: viewType=" + viewType);


        View view = inflater.inflate(R.layout.item_recy_edit_text, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: position=" + position);
        if (mPos.contains(position)) {
            holder.textView.setText(position + "sss");
        } else {
            holder.textView.setText(stringList.get(position).textContent);
        }

        //holder.editText.setText("");

        if (mOnItemClickListener != null) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPos.add(position);

                    mOnItemClickListener.onItemClick(v, position);
                }
            });


            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(v, position);
                    return false;
                }
            });
        }


        processEditText(holder.editText, position);


    }

    // HashMap<Integer, String> contents = new HashMap<>();  //方法一
    private void processEditText(final EditText editText, final int position) {
        Log.i(TAG, "processEditText: " + stringList.size() + "  " + position);

        EditBean editBean = stringList.get(position);//方法二

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
               /* if (contents != null) {
                    int pos = (int) editText.getTag();
                    contents.put(pos, s.toString());
                }*/

                //方法二
                int pos = (int) editText.getTag();
                stringList.get(pos).editContent = s + "";

            }
        });

        //方法一
       /* if (!TextUtils.isEmpty(contents.get(position))) {//不为空的时候 赋值给对应的edittext
            editText.setText(contents.get(position));
        } else {//置空
            editText.getEditableText().clear();
        }*/

        //方法二
        if (!TextUtils.isEmpty(editBean.editContent)) {
            editText.setText(editBean.editContent);
        } else {
            editText.setText("");
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