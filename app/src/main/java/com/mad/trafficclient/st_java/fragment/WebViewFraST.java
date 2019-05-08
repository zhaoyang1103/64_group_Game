package com.mad.trafficclient.st_java.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.mad.trafficclient.R;

public class WebViewFraST extends Fragment {
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_webview_st, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        webView.loadUrl("file:///android_asset/index_st.html");
    }

    private void initView(View view) {
        webView = (WebView) view.findViewById(R.id.webView);
    }
}
