/**
 *
 */
package com.mad.trafficclient.zy_java.manage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.manage.ChartManage;

import java.util.ArrayList;
import java.util.Map;


public class Chart_2 extends Fragment {

    private PieChart piechart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.piechart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        piechart = (PieChart) view.findViewById(R.id.piechart);
        Map map = CarData.getMap();
        ChartManage chartManage = new ChartManage(piechart);
        chartManage.showPieChart(((ArrayList<String>) map.get("x2")), ((ArrayList<Float>) map.get("y2")));
    }
}
