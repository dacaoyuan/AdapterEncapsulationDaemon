package com.example.adapterencapsulation.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.bean.DemoBean;

import java.util.List;

/**
 * @author pengyuming
 * @version 1.0
 * @Package com.pengyuming.myapplication.demo
 *
 * 测试发现，当列表的item包含一个编辑框是，采用这种方法，没问题。包含多个编辑框时，还是会出现错乱的
 *
 * @Description: TODO (用一句话描述该文件做什么) Date: 2017-02-23  09:51
 */
public class EditText2Adapter extends BaseQuickAdapter<DemoBean, BaseViewHolder> {


    public EditText2Adapter() {
        super(R.layout.list_demo_item);
    }

    private OnItemEditTextChangedListener mListener;

    public void setListener(OnItemEditTextChangedListener listener) {
        mListener = listener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DemoBean item) {
        helper.setText(R.id.tv_name, item.getName());

        helper.setText(R.id.et_age, item.getAge());


        EditText etAge = helper.getView(R.id.et_age);

        etAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //mListener.onEditTextAfterTextChanged(s, helper.getLayoutPosition());//推荐使用这个方法吧


                //说明：如果使用BaseRecyclerViewAdapterHelper这个框架的话，直接使用下面的两行代码，也可以。
                // mListener.onEditTextAfterTextChanged(s, helper.getLayoutPosition());这个监听回调屏蔽即可。

                DemoBean demoBean = mData.get(helper.getLayoutPosition());
                demoBean.setAge(s + "");

                Log.i(TAG, "afterTextChanged: demoBean.age="+demoBean.age);

            }
        });
    }

    /**
     *  写这个接口回调,使用的场景为：一般我们会先初始化adapter，等待网络请求数据，成功后，得到数据源后，再去刷新adapter
     *  这样我们在网络请求前，就算传进来的数据，也是无效数据。因此需要回调到，调用该adapter的activity中，这样才能方便的使用
     *  从网络请求后的数据源。
     *
     *  不过，如果使用BaseRecyclerViewAdapterHelper这个框架的话，可不必这样，原因在 在 public void afterTextChanged(Editable s)的
     *  回调中，已说明
     *
     */
    public interface OnItemEditTextChangedListener {
        void onEditTextAfterTextChanged(Editable editable, int position);
    }
}
