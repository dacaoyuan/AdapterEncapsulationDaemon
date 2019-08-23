package com.example.adapterencapsulation.activity.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.adapterencapsulation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 默认 ScrollView 拦截 WebView 的滚动事件
 *
 * 测试发现，只要嵌套 webView 的父控件是填充整个布局的话，
 * webView 的高度是match_parent 还是 wrap_content 。都能全部展示
 *
 *
 */

public class ScrollViewNestWebViewActivity extends AppCompatActivity {
    private static final String TAG = "ScrollViewNestWebViewAc";
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_nest_web_view);
        ButterKnife.bind(this);


        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/htmltest.html");
        //mWebView.loadUrl("https://www.baidu.com/");

       /* mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ((WebView) v).requestDisallowInterceptTouchEvent(true);
                Log.i(TAG, "onTouch: ");
                return false;
            }
        });*/
    }
}
