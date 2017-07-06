package com.example.adapterencapsulation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    private List<String> stringList;
    private LayoutInflater inflater;
    private Context mContent;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public MyRecyAdapter(List<String> stringList, Context mContext) {
        this.stringList = stringList;
        inflater = LayoutInflater.from(mContext);
        this.mContent = mContext;
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
        //View view = View.inflate(mContent, R.layout.item_recy, null);
        final ViewHolder holder = new ViewHolder(view);

        if (mOnItemClickLitener != null) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    int pos2 = holder.getAdapterPosition();
                    System.out.println("MyRecyAdapter.onClick pos=" + " pos2=" + pos2);

                    mOnItemClickLitener.onItemClick(v, holder.getAdapterPosition());
                }
            });


            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickLitener.onItemLongClick(v,holder.getAdapterPosition());
                    return false;
                }
            });
        }



      /*  holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContent, stringList.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });*/


        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyAdapter.ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


}