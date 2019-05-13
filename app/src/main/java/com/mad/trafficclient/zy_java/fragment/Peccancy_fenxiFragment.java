/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;


import android.app.AlertDialog;
import android.content.Context;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.bean.AllPeccancyBean;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.manage.ChartManage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Peccancy_fenxiFragment extends Fragment {

    private Spinner sp_start_time;
    private Spinner sp_end_time;
    private LineChart lien_chart;
    private List<AllPeccancyBean.ROWSDETAILBean> rowsdetailBeans;
    private SimpleDateFormat simpleDateFormat;
    private Context context;
    private long start_long;
    private long end_long;

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
        sp_start_time = (Spinner) view.findViewById(R.id.sp_start_time);
        sp_end_time = (Spinner) view.findViewById(R.id.sp_end_time);
        lien_chart = (LineChart) view.findViewById(R.id.lien_chart);
        rowsdetailBeans = CarData.getallpeccancy_list();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sp_start_time.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new String[]{"请选择时间"}));
        sp_end_time.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new String[]{"请选择时间"}));
//        sp_start_time.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new String[]{time}));
        sp_start_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showstartDialog_1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_end_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                show_endDialog_1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                String time = year + ":" + month + ":" + day;
                start_long = getLong(time);
                sp_start_time.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new String[]{time}));

                ChartManage chartManage = new ChartManage(lien_chart);
                chartManage.showLineChart((ArrayList<String>) getYData(start_long, end_long).get("x"), (ArrayList<Integer>) getYData(start_long, end_long).get("y"));

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
                String time = year + ":" + month + ":" + day;
                end_long = getLong(time);
                Log.i("结束的long", "onClick: " + end_long);
                sp_end_time.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new String[]{time}));
                dialog.dismiss();
                ChartManage chartManage = new ChartManage(lien_chart);
                chartManage.showLineChart((ArrayList<String>) getYData(start_long, end_long).get("x"), (ArrayList<Integer>) getYData(start_long, end_long).get("y"));
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
        float[] floats = new float[5];
        for (int i = 0; i < rowsdetailBeans.size(); i++) {
            Log.i("地址", "getYData: " + rowsdetailBeans.get(i).getPaddr());
            String datetime = rowsdetailBeans.get(i).getDatetime();
            try {
                Date parse = simpleDateFormat.parse(datetime);
                long time = parse.getTime();
                if (time < endtime && time > start_time) {
                    if (rowsdetailBeans.get(i).getPaddr().equals("学院路")) {
                        floats[0]++;
                    } else if (rowsdetailBeans.get(i).getPaddr().equals("联想路")) {
                        floats[1]++;
                    } else if (rowsdetailBeans.get(i).getPaddr().equals("医院路")) {
                        floats[2]++;
                    } else if (rowsdetailBeans.get(i).getPaddr().equals("幸福路")) {
                        floats[3]++;
                    } else if (rowsdetailBeans.get(i).getPaddr().equals("环城快速路")) {
                        floats[4]++;
                    } else if (rowsdetailBeans.get(i).getPaddr().equals("环城高速")) {
                        floats[5]++;
                    }

                }


            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        ArrayList<String> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        x.add("学院路");
        x.add("联想路");
        x.add("医院路");
        x.add("幸福路");
        x.add("环城快速路");
        x.add("环城高速");
        for (int i = 0; i < floats.length; i++) {
            floats[i]++;
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
}
