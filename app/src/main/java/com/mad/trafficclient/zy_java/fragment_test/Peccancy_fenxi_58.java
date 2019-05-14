/**
 *
 */
package com.mad.trafficclient.zy_java.fragment_test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.bean.AllPeccancyBean;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.manage.ChartManage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Peccancy_fenxi_58 extends Fragment implements View.OnClickListener {

    private TextView sp_start_time;
    private TextView sp_end_time;
    private LineChart lien_chart;
    private long start_time = 0;
    private long end_time = 0;
    private List<AllPeccancyBean.ROWSDETAILBean> rowsdetailBeans;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.peccancy_fenxi, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        rowsdetailBeans = CarData.getallpeccancy_list();
        sp_start_time = (TextView) view.findViewById(R.id.sp_start_time);
        sp_end_time = (TextView) view.findViewById(R.id.sp_end_time);
        lien_chart = (LineChart) view.findViewById(R.id.lien_chart);
        sp_start_time.setOnClickListener(this);
        sp_end_time.setOnClickListener(this);
        sp_start_time.setText("2016-05-01");
        sp_end_time.setText("2016-06-01");
        start_time=getLongM("2016-05-01");
        end_time=getLongM("2016-06-01");
        showLinechart();
    }


    private long getLongM(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(time);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sp_start_time:
                showStartDialog();
                break;
            case R.id.sp_end_time:
                showEndDialog();
                break;
        }
    }

    private void showStartDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("开始时间");
        View view = View.inflate(context, R.layout.caldarview, null);
        final DatePicker datePicker = view.findViewById(R.id.datePicker);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String time = datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth();
                sp_start_time.setText(time);
                start_time = getLongM(time);
                showLinechart();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void showEndDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("开始时间");
        View view = View.inflate(context, R.layout.caldarview, null);
        final DatePicker datePicker = view.findViewById(R.id.datePicker);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String time = datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth();
                sp_end_time.setText(time);
                end_time = getLongM(time);
                showLinechart();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }


    private void showLinechart() {
        ArrayList<String> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        String[] strings = new String[]{"重庆路", "沈阳路", "南京路", "上海路", "广州路", "高速公路", "北京路"};
        for (int i = 0; i < strings.length; i++) {
            x.add(strings[i]);
        }
        int[] ints = new int[7];
        for (int i = 0; i < rowsdetailBeans.size(); i++) {
            String now_time = rowsdetailBeans.get(i).getDatetime();
            long longM = getLongM(now_time);
            if (longM > start_time && longM < end_time) {
                if (rowsdetailBeans.get(i).getPaddr().equals(strings[0])) {
                    ints[0]++;
                } else if (rowsdetailBeans.get(i).getPaddr().equals(strings[1])) {
                    ints[1]++;

                } else if (rowsdetailBeans.get(i).getPaddr().equals(strings[2])) {
                    ints[2]++;

                } else if (rowsdetailBeans.get(i).getPaddr().equals(strings[3])) {
                    ints[3]++;

                } else if (rowsdetailBeans.get(i).getPaddr().equals(strings[4])) {
                    ints[4]++;

                } else if (rowsdetailBeans.get(i).getPaddr().equals(strings[5])) {
                    ints[5]++;

                } else if (rowsdetailBeans.get(i).getPaddr().equals(strings[6])) {
                    ints[6]++;
                }

            }
        }
        for (int j = 0; j < ints.length; j++) {
            y.add(ints[j]);
        }
        ChartManage chartManage = new ChartManage(lien_chart);
        chartManage.showLineChart(x, y);

    }
}
