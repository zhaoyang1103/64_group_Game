/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.mad.trafficclient.R;


public class Zy_webview extends Fragment implements View.OnClickListener {

    private WebView zy_webview;
    private Button bt_start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.zy_webview, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        zy_webview = (WebView) view.findViewById(R.id.zy_webview);
//        zy_webview.loadDataWithBaseURL(null, null, "zy_web.html", "utf-8", null);
        String url = "file:///android_asset/zy_web.html";
        zy_webview.loadUrl(url);
        bt_start = (Button) view.findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                Intent intent=new Intent(getContext(),com.amap.api.maps.offlinemap.OfflineMapActivity.class);
                startActivity(intent);
                break;
        }
    }
}
