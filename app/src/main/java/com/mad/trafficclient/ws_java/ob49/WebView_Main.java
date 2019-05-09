package com.mad.trafficclient.ws_java.ob49;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.mad.trafficclient.R;

/**
 * Created by Go_Fight_Now on 2019/5/8 9:53
 */
public class WebView_Main extends Fragment {
    private WebView ob49_webview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob49_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ob49_webview = (WebView) view.findViewById(R.id.ob49_webview);
        ob49_webview.loadUrl("file:///android_asset/webView.html");
    }
}
