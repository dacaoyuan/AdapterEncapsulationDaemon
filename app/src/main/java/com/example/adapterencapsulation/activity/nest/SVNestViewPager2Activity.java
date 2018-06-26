package com.example.adapterencapsulation.activity.nest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.adapterencapsulation.R;
import com.example.adapterencapsulation.activity.nest.adapter.MyFragmentPagerAdapter;
import com.example.adapterencapsulation.adapter.MyRecyAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 竖直ScrollView嵌套，水平滑动viewPager(二)
 * <p>
 * 通过源码发现：viewPager内部已做处理，当时水平滑动时，viewPager会请求父控件，不要拦截事件。
 * <p>
 * ScrollView 嵌套 ViewPager 不能显示解决办法:
 * 已在 竖直ScrollView嵌套，水平滑动viewPager(一) 中做解释
 * <p>
 * 现实场景，我们不需要在外层再嵌套ScrollView布局的，没什么实际用处。只为测试，此种嵌套的情况
 */
public class SVNestViewPager2Activity extends AppCompatActivity {
    public static final String TAG = "SVNestViewPager2Activit";

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    MyFragmentPagerAdapter pagerAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            scrollView.scrollTo(0, 0);
        }
    };

  /*  @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        scrollView.scrollTo(0, 0);
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_nest_view_pager2);
        ButterKnife.bind(this);


        /**
         * 当我们上面的view如果滑动到一半的时候，切换到下一个Fragment，在切换回来的时候，RecyclerView的第一个item会自动滑动到顶部
         * 测试发现：下一个fragment如果是ViewPager,没有出现上述的问题。（这个情况，仅做了解一下吧）
         *
         * 下面的一行代码，会有效的解决这个问题。
         */
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS); // 会覆盖子类控件而直接获得焦点

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


       /* viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: position=" + position);
                scrollView.scrollTo(0, 0);
                //scrollView.fullScroll(View.FOCUS_UP);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged: state=" + state);
                if (state == 0) {
                    mHandler.sendEmptyMessage(0);
                }

            }
        });*/

    }


}

