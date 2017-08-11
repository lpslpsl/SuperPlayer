package com.example.lps.superplayer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.lps.superplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {


    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;
    @BindView(R.id.webview)
    WebView mWebview;
    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mview = inflater.inflate(R.layout.fragment_blog, container, false);
        unbinder = ButterKnife.bind(this, mview);
        initview();
        return mview;
    }

    private void initview() {
        mWebview.loadUrl("http://www.jianshu.com/u/de410028efe2");
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressbar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        /**
         * 让webView可以返回上一个页面。而不是直接退出
         * 在Activity可以重写onkeyDown实现
         */
        mWebview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK&&mWebview.canGoBack()){
                    mWebview.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.progressbar, R.id.webview})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.progressbar:
                break;
            case R.id.webview:
                break;
        }
    }

}
