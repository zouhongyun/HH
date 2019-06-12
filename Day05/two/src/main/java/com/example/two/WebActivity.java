package com.example.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {

    private ImageView mIv;
    /**
     * 文章标题
     */
    private TextView mTvToolbar1;
    private Toolbar mWebToolbar;
    private WebView mWeb;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initData();
        initView();

    }

    private void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mTvToolbar1 = (TextView) findViewById(R.id.tv_toolbar1);
        mWebToolbar = (Toolbar) findViewById(R.id.web_toolbar);
        mWeb = (WebView) findViewById(R.id.web);
         mWeb = (WebView) findViewById(R.id.web);
                 mWeb.getSettings().setJavaScriptEnabled(true);
                 mWeb.setWebChromeClient(new WebChromeClient());
                 mWeb.loadUrl(url);

    }
}
