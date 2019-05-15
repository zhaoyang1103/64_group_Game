/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Peccancy_fenxiFragment extends Fragment implements View.OnClickListener {


    private LineChart lien_chart;
    private List<AllPeccancyBean.ROWSDETAILBean> rowsdetailBeans;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat simpleDateFormat1;
    private Context context;
    private long start_long;
    private long end_long;
    private TextView sp_start_time;
    private TextView sp_end_time;

    //private  void
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

        lien_chart = (LineChart) view.findViewById(R.id.lien_chart);
        rowsdetailBeans = CarData.getallpeccancy_list();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");


        sp_start_time = (TextView) view.findViewById(R.id.sp_start_time);
        sp_start_time.setOnClickListener(this);
        sp_end_time = (TextView) view.findViewById(R.id.sp_end_time);
        sp_end_time.setOnClickListener(this);
    }


    private void showstartDialog_1() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final View view = View.inflate(context, R.layout.caldarview, null);
        final DatePicker viewById = view.findViewById(R.id.datePicker);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int year = viewById.getYear();
                int month = viewById.getMonth();
                int day = viewById.getDayOfMonth();
                String time = year + "-" + (month + 1) + "-" + day;
                start_long = getLong1(time);
                sp_start_time.setText(time);

                Map yData = getYData(start_long, end_long);
                ChartManage chartManage = new ChartManage(lien_chart);
                chartManage.showLineChart((ArrayList<String>) yData.get("x"), (ArrayList<Integer>) yData.get("y"));
            }
        });

        alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private void show_endDialog_1() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final View view = View.inflate(context, R.layout.caldarview, null);
        final DatePicker viewById = view.findViewById(R.id.datePicker);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int year = viewById.getYear();
                int month = viewById.getMonth();
                int day = viewById.getDayOfMonth();
                String time = year + "-" + (month + 1) + "-" + day;
                end_long = getLong1(time);

                sp_end_time.setText(time);

                Map yData = getYData(start_long, end_long);
                ChartManage chartManage = new ChartManage(lien_chart);
                chartManage.showLineChart((ArrayList<String>) yData.get("x"), (ArrayList<Integer>) yData.get("y"));

            }
        });

        alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private Map getYData(long start_time, long endtime) {
        float[] floats = new float[7];
        for (int i = 0; i < rowsdetailBeans.size(); i++) {
            Log.i("地址", "getYData: " + rowsdetailBeans.get(i).getPaddr());
            Log.i("时间", "getYData: " + rowsdetailBeans.get(i).getDatetime());
            String datetime = rowsdetailBeans.get(i).getDatetime();
            long time = getLong(datetime);
            Log.i("开始的long", "onClick: " + start_long);
            Log.i("结束的long", "onClick: " + end_long);
            Log.i("中间时间", "getYData: " + time);
            if (time <= endtime && time >= start_time) {
                if (rowsdetailBeans.get(i).getPaddr().equals("重庆路")) {
                    floats[0]++;
                    Log.i("打印", "getYData: " + floats[0]);

                } else if (rowsdetailBeans.get(i).getPaddr().equals("沈阳路")) {
                    floats[1]++;
                } else if (rowsdetailBeans.get(i).getPaddr().equals("南京路")) {
                    floats[2]++;
                } else if (rowsdetailBeans.get(i).getPaddr().equals("上海路")) {
                    floats[3]++;
                } else if (rowsdetailBeans.get(i).getPaddr().equals("广州路")) {
                    floats[4]++;
                } else if (rowsdetailBeans.get(i).getPaddr().equals("高速公路")) {
                    floats[5]++;
                } else if (rowsdetailBeans.get(i).getPaddr().equals("北京路")) {
                    floats[6]++;
                }

            }


        }

        ArrayList<String> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        System.out.println(Arrays.asList(y) + "邮电费嘚瑟护");
        x.add("重庆路");
        x.add("沈阳路");
        x.add("南京路");
        x.add("上海路");
        x.add("广州路");
        x.add("高速公路");
        x.add("北京路");
        for (int i = 0; i < floats.length; i++) {
            y.add((int) floats[i]);
        }
        Map map = new HashMap();
        map.put("x", x);
        map.put("y", y);
        return map;
    }

    private long getLong(String date) {
        try {
            Date parse = simpleDateFormat.parse(date);
            long time = parse.getTime();
            Log.i("123", "getLong: " + time);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private long getLong1(String date) {
        try {
            Date parse = simpleDateFormat1.parse(date);
            long time = parse.getTime();
            Log.i("123", "getLong: " + time);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sp_start_time:
                showstartDialog_1();
                break;
            case R.id.sp_end_time:
                show_endDialog_1();
                break;
        }
    }
}
