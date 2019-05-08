package com.mad.trafficclient.st_java.fragment;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.Get_all_sense;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class SHZSminFra extends Fragment {
    private TextView PM2_5;
    private TextView temperature;
    private TextView humidity;
    private TextView name1;
    private TextView value1;
    private TextView name2;
    private TextView value2;
    private Context context;
    private UrlBean urlBean;
    private String userName;
    private RequestQueue requestQueue;
    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_shzs_min, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {

    }

    private void initData() {
        context = getContext();
        urlBean = Util.loadSetting(context);
        userName = Util.getUserName(context);
        requestQueue = Volley.newRequestQueue(context);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("UserName", userName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_all_sense", jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            Gson gson = new Gson();
                            Get_all_sense get_all_sense = gson.fromJson(jsonObject.toString(), Get_all_sense.class);
                            int pm25126 = get_all_sense.get_$Pm25126();
                            PM2_5.setText("PM2.5:" + pm25126);
                            temperature.setText("温度:" + get_all_sense.getTemperature());
                            humidity.setText("湿度:" + get_all_sense.getHumidity());
                            if (pm25126 < 100) {
                                name1.setText("良好");
                                name1.setTextColor(Color.parseColor("#000000"));
                                value1.setText("气象条件会对晨练影响不大");
                            } else if (pm25126 < 200) {
                                name1.setText("轻度");
                                name1.setTextColor(Color.parseColor("#000000"));
                                value1.setText("受天气影响，较不宜晨练");
                            } else if (pm25126 < 300) {
                                name1.setText("重度");
                                name1.setTextColor(Color.parseColor("#ff0000"));
                                value1.setText("减少外出，出行注意戴口罩");
                            } else {
                                name1.setText("爆表");
                                name1.setTextColor(Color.parseColor("#ff0000"));
                                value1.setText("停止一切外出活动");
                            }

                            int lightIntensity = get_all_sense.getLightIntensity();
                            if (lightIntensity < 200) {
                                name2.setText("非常弱");
                                name2.setTextColor(Color.parseColor("#000000"));
                                value2.setText("您无须担心紫外线");
                            } else if (lightIntensity < 300) {
                                name2.setText("弱");
                                name2.setTextColor(Color.parseColor("#000000"));
                                value1.setText("外出适当涂抹低倍数防晒霜");
                            } else {
                                name2.setText("强");
                                name2.setTextColor(Color.parseColor("#ff0000"));
                                value2.setText("外出需要涂抹中倍数防晒霜");
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
            }
        }, 0, 3000);

    }

    private void initView(View view) {
        PM2_5 = (TextView) view.findViewById(R.id.PM2_5);
        temperature = (TextView) view.findViewById(R.id.temperature);
        humidity = (TextView) view.findViewById(R.id.humidity);
        name1 = (TextView) view.findViewById(R.id.name1);
        value1 = (TextView) view.findViewById(R.id.value1);
        name2 = (TextView) view.findViewById(R.id.name2);
        value2 = (TextView) view.findViewById(R.id.value2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }
}
