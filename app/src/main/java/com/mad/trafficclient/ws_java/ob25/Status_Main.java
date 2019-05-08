package com.mad.trafficclient.ws_java.ob25;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.ws_java.ob5.IndexBean;
import com.mad.trafficclient.ws_java.ob5.SenseBean;
import com.mad.trafficclient.ws_java.ob5.SenseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Go_Fight_Now on 2019/5/7 18:01
 */
public class Status_Main extends Fragment {
    private TextView ob25_tv_pm25;
    private TextView ob25_tv_wendu;
    private TextView ob25_tv_shidu;
    private TextView ob25_tv_road_1;
    private TextView ob25_bg_1;
    private TextView ob25_tv_road_2;
    private TextView ob25_bg_2;
    private TextView ob25_tv_road_3;
    private TextView ob25_bg_3;
    private Context context;
    private int count;
    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob25_main, container, false);
        initView(view);
        context = getContext();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                GetAllSense();
                GetRoadStatus();
            }
        },0,3000);
        return view;
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
                        ob25_tv_pm25.setText("PM2.5：" + jsonObject.optInt("pm2.5"));
                        ob25_tv_wendu.setText("温   度：" + jsonObject.optInt("temperature"));
                        ob25_tv_shidu.setText("湿   度：" + jsonObject.optInt("humidity"));
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

    public void GetRoadStatus() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_road_status";
        count = 0;
        for (int i = 0; i < 3; i++) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("RoadId", i + 1);
                jsonObject.put("UserName", Util.getUserName(context));

                final int finalI = i;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            String info = "";
                            int color = 0;
                            if (jsonObject.optInt("Status") == 1){
                                info = "通畅";
                                color = Color.parseColor("#0ebd12");
                            } else if (jsonObject.optInt("Status") == 2) {
                                info = "较通畅";
                                color = Color.parseColor("#98ed1f");
                            }else if (jsonObject.optInt("Status") == 3) {
                                info = "拥挤";
                                color = Color.parseColor("#ffff01");
                            }else if (jsonObject.optInt("Status") == 4) {
                                info = "堵塞";
                                color = Color.parseColor("#ff0103");
                            }else if (jsonObject.optInt("Status") == 5) {
                                info = "爆表";
                                color = Color.parseColor("#4c060e");
                            }
                            switch (finalI){
                                case 0:
                                    ob25_tv_road_1.setText("1号道路：" + info);
                                    ob25_bg_1.setBackgroundColor(color);
                                    break;
                                case 1:
                                    ob25_tv_road_2.setText("2号道路：" + info);
                                    ob25_bg_2.setBackgroundColor(color);
                                    break;
                                case 2:
                                    ob25_tv_road_3.setText("3号道路：" + info);
                                    ob25_bg_3.setBackgroundColor(color);
                                    break;

                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "道路状态获取失败", Toast.LENGTH_SHORT).show();
                    }
                }));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void initView(View view) {
        ob25_tv_pm25 = (TextView) view.findViewById(R.id.ob25_tv_pm25);
        ob25_tv_wendu = (TextView) view.findViewById(R.id.ob25_tv_wendu);
        ob25_tv_shidu = (TextView) view.findViewById(R.id.ob25_tv_shidu);
        ob25_tv_road_1 = (TextView) view.findViewById(R.id.ob25_tv_road_1);
        ob25_bg_1 = (TextView) view.findViewById(R.id.ob25_bg_1);
        ob25_tv_road_2 = (TextView) view.findViewById(R.id.ob25_tv_road_2);
        ob25_bg_2 = (TextView) view.findViewById(R.id.ob25_bg_2);
        ob25_tv_road_3 = (TextView) view.findViewById(R.id.ob25_tv_road_3);
        ob25_bg_3 = (TextView) view.findViewById(R.id.ob25_bg_3);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
