/**
 *
 */
package com.mad.trafficclient.zy_java.manage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.manage.ChartManage;

import java.util.ArrayList;
import java.util.Map;


public class Chart_6 extends Fragment {


    private BarChart barchart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.barchart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        barchart = (BarChart) view.findViewById(R.id.barchart);

        Map map = CarData.getMap();
        ChartManage chartManage = new ChartManage(barchart);
        chartManage.showBarchart6(((ArrayList<String>) map.get("x6")), ((ArrayList<Float>) map.get("y6")));
    }
}
