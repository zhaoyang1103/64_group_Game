/**
 *
 */
package com.mad.trafficclient.zy_java.manage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.data.Cardata2;
import com.mad.trafficclient.zy_java.manage.ChartManage;

import java.util.ArrayList;
import java.util.Map;


public class Chart_3 extends Fragment {

    private HorizontalBarChart horichart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.horibarchart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        horichart = (HorizontalBarChart) view.findViewById(R.id.horichart);

        Map map = CarData.getMap();
        ChartManage chartManage = new ChartManage(horichart);
        chartManage.showHoriChart(((ArrayList<String>) map.get("x3")), ((ArrayList<Float>) map.get("y3")));
    }
}
