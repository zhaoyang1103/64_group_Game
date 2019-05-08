/**
 *
 */
package com.mad.trafficclient.zy_java.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.ws_java.ob5.IndexBean;
import com.mad.trafficclient.zy_java.manage.ChartManage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Right_message extends Fragment {

    private PieChart piechart;
    private Context context;
    private Timer timer;
    private ChartManage chartManage;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            chartManage.showMessagePieChart(x, y);
            super.handleMessage(msg);
        }
    };
    private ArrayList<String> x;
    private ArrayList<Integer> y;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.piemessagechart, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        piechart = (PieChart) view.findViewById(R.id.piechart);
        timer = new Timer();
        chartManage = new ChartManage(piechart);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                GetAllSense();
                int[] tu = Left_Message.getTu();
                x = new ArrayList<>();
                y = new ArrayList<>();
                x.add("PM2.5  " + tu[0]);
                x.add("光照  " + tu[1]);
                x.add("温度  " + tu[2]);
                x.add("湿度  " + tu[3]);
                x.add("CO2  " + tu[4]);
                for (int i = 0; i < tu.length; i++) {
                    y.add(tu[i]);
                }
                handler.sendEmptyMessage(0);
            }
        }, 0, 3000);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    public void GetAllSense() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_sense";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                    if (jsonObject.optString("RESULT").equals("S")) {
                        Gson gson = new Gson();
                        IndexBean indexBean = gson.fromJson(jsonObject.toString(), IndexBean.class);
                        ArrayList<String> x = new ArrayList<>();
                        ArrayList<Integer> y = new ArrayList<>();
                        x.add("PM2.5" + indexBean.get_$Pm25316());
                        x.add("光照" + indexBean.getLightIntensity());
                        x.add("温度" + indexBean.getTemperature());
                        x.add("湿度" + indexBean.getHumidity());
                        x.add("CO2" + indexBean.getCo2());
                        y.add(indexBean.get_$Pm25316());
                        y.add(indexBean.getLightIntensity());
                        y.add(indexBean.getTemperature());
                        y.add(indexBean.getHumidity());
                        y.add(indexBean.getCo2());

                        chartManage.showMessagePieChart(x, y);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, "传感器各项值获取失败", Toast.LENGTH_SHORT).show();
                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
