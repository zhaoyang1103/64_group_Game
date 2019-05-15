/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.RadarChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.bean.AllTypeBean;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.manage.ChartManage;

import java.util.ArrayList;
import java.util.List;


public class Right_road_fragment extends Fragment {

    private RadarChart radarchart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.rechart, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        radarchart = (RadarChart) view.findViewById(R.id.radarchart);
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        List<AllTypeBean.ROWSDETAILBean> alltype_list = CarData.getAlltype_list();
        for (int i = 0; i < 5; i++) {
            x.add(alltype_list.get(i).getPremarks());
            y.add(alltype_list.get(i).getCount());
        }
        ChartManage chartManage=new ChartManage(radarchart);
        chartManage.showReChart(x,y);

    }
}
