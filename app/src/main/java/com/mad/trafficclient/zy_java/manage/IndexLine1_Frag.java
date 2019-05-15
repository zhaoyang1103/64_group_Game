/**
 *
 */
package com.mad.trafficclient.zy_java.manage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob5.IndexBean;
import com.mad.trafficclient.ws_java.ob5.SenseDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class IndexLine1_Frag extends Fragment {

    private LineChart lineChart;
    private Timer timer;
    private Map map;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            chartManage.showLineChart(((ArrayList<String>) map.get("x")), (ArrayList<Integer>) map.get("y"));
        }
    };
    private SenseDao dao;
    private ChartManage chartManage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.linechart, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
        timer = new Timer();
        Context context = getContext();
        dao = new SenseDao(context);
        chartManage = new ChartManage(lineChart);
        map = new HashMap(); map.put("x","1");
        map.put("y",1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayList<String> x = new ArrayList<>();
                ArrayList<Integer> y = new ArrayList<>();
                List<IndexBean> indexBeans = dao.queryForAll();
                for (int i = 0; i < indexBeans.size(); i++) {
                    x.add(indexBeans.get(i).getTime());
                    y.add(indexBeans.get(i).getTemperature());
                }
                map.put("x", x);
                map.put("y", y);
                handler.sendEmptyMessage(0);

            }
        }, 0, 3000);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
