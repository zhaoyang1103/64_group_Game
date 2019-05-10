/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.concurrent.CountDownLatch;


public class Swip_test extends Fragment {

    private SwipeRefreshLayout swip_test;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                text_show.setText("刷新完成");
                handermess(1);
            } else {
                text_show.setVisibility(View.GONE);
                swip_test.setRefreshing(false);
            }


            super.handleMessage(msg);
        }
    };
    private TextView text_show;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.swip_fragment, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        swip_test = (SwipeRefreshLayout) view.findViewById(R.id.swip_test);
        text_show = (TextView) view.findViewById(R.id.text_show);
        text_show.setVisibility(View.GONE);

        swip_test.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                text_show.setText("正在刷新");
                text_show.setVisibility(View.VISIBLE);
                handermess(0);
            }
        });

    }

    private void handermess(final int msg) {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Message message = new Message();
                    message.what = msg;
                    handler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();

    }
}
