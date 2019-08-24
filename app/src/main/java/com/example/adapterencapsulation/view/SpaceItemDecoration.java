package com.example.adapterencapsulation.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Author: YuanPeikai
 * @CreateDate: 2019/8/23 17:20
 * @Description:
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int count;

    public SpaceItemDecoration(int space, int count) {
        this.space = space;
        this.count = count;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        // outRect.left = space / 2;
        // outRect.top = space / 2;
        //outRect.bottom = space / 2;
        //outRect.right = space / 2;

        //int childLayoutPosition = parent.getChildLayoutPosition(view);
        //System.out.println("SpaceItemDecoration.getItemOffsets childLayoutPosition="+childLayoutPosition);


        outRect.left = 0;
        outRect.top = 0;
        outRect.bottom = 0;
        outRect.right = 0;



        switch (count) {
            case 1:
                //outRect.left = space / 2;
                //outRect.right = space / 2;
                break;
            case 2:
                if (parent.getChildLayoutPosition(view) % count == 0) {
                    outRect.left = 0;
                } else {
                    outRect.left = space / 2;

                }


                break;
            case 3:
                if (parent.getChildLayoutPosition(view) % count == 0) {
                    outRect.left = 0;
                } else {
                    outRect.left = space / 2;
                }
                break;

            default:
                if (parent.getChildLayoutPosition(view) % 2 == 0) {
                    outRect.left = 0;
                } else {
                    outRect.left = space / 2;

                }

                break;
        }


//        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
//        if (parent.getChildLayoutPosition(view) % count == 0) {
//            outRect.left = 0;
//        }
    }
}

