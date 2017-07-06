package com.example.adapterencapsulation.pagerapdapter;

/**
 * Created by zhyantai on 2017/6/30.
 */

public interface ViewPagerHolderCreator<VH extends ViewPagerHolder> {
    /**
     * 创建ViewHolder
     *
     * @return
     */
    public VH createViewHolder();
}
