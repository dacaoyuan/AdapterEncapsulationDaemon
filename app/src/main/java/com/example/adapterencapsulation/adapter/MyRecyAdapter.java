package com.example.adapterencapsulation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapterencapsulation.R;

import java.util.List;

/**
 * Created by zhyantai on 2017/7/5.
 */
public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.ViewHolder> {
    private static final String TAG = "MyRecyAdapter";
    private List<String> stringList;
    private LayoutInflater inflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public MyRecyAdapter(List<String> stringList, Context mContext) {
        this.stringList = stringList;
        inflater = LayoutInflater.from(mContext);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }


    @Override
    public MyRecyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recy, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(stringList.get(position));
        if (mOnItemClickListener != null) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //int pos = holder.getLayoutPosition();
                    //int pos2 = holder.getAdapterPosition();
                    //Log.i(TAG, "onClick: pos=" + pos + " pos2=" + pos2);
                    mOnItemClickListener.onItemClick(v, position);
                }
            });


            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return stringList == null ? 0 : stringList.size();
    }


}