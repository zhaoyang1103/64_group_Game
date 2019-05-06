package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LKCXFra extends Fragment {
    private TextView v_xyl;
    private TextView v_lxl;
    private TextView v_xfl;
    private TextView v_yyl;
    private TextView v_tcc;
    private TextView v_hcgs;
    private ImageView refresh;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_temperature;
    private TextView tv_humidity;
    private TextView tv_pm2_5;
    private ImageView iv_1;
    private ImageView iv_2;
    private Context context;
    private RequestQueue requestQueue;
    private UrlBean urlBean;
    private String userName;
    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_lkcx, container, false);
        initView(view);
        ininData();
        initListener();
        return view;
    }

    private void initListener() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_all_sense();
            }
        });
    }

    private void ininData() {
        /**
         * 查询道路状态 http://localhost:8080/api/v2/get_road_status
         *
         */
        context = getContext();
        requestQueue = Volley.newRequestQueue(context);
        urlBean = Util.loadSetting(context);
        userName = Util.getUserName(context);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JSONObject jsonRequest = new JSONObject();
                try {
                    jsonRequest.put("UserName", userName);
                    jsonRequest.put("RoadId", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_road_status", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int status = jsonObject.optInt("Status");
                            switch (status) {
                                case 1:
                                    v_xyl.setBackgroundColor(Color.parseColor("#6ab82e"));
                                    break;
                                case 2:
                                    v_xyl.setBackgroundColor(Color.parseColor("#ece93a"));
                                    break;
                                case 3:
                                    v_xyl.setBackgroundColor(Color.parseColor("#f49b25"));
                                    break;
                                case 4:
                                    v_xyl.setBackgroundColor(Color.parseColor("#e33532"));
                                    break;
                                case 5:
                                    v_xyl.setBackgroundColor(Color.parseColor("#b01e23"));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
                try {
                    jsonRequest.put("UserName", userName);
                    jsonRequest.put("RoadId", 2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_road_status", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int status = jsonObject.optInt("Status");
                            switch (status) {
                                case 1:
                                    v_lxl.setBackgroundColor(Color.parseColor("#6ab82e"));
                                    break;
                                case 2:
                                    v_lxl.setBackgroundColor(Color.parseColor("#ece93a"));
                                    break;
                                case 3:
                                    v_lxl.setBackgroundColor(Color.parseColor("#f49b25"));
                                    break;
                                case 4:
                                    v_lxl.setBackgroundColor(Color.parseColor("#e33532"));
                                    break;
                                case 5:
                                    v_lxl.setBackgroundColor(Color.parseColor("#b01e23"));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
                try {
                    jsonRequest.put("UserName", userName);
                    jsonRequest.put("RoadId", 3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_road_status", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int status = jsonObject.optInt("Status");
                            switch (status) {
                                case 1:
                                    v_yyl.setBackgroundColor(Color.parseColor("#6ab82e"));
                                    break;
                                case 2:
                                    v_yyl.setBackgroundColor(Color.parseColor("#ece93a"));
                                    break;
                                case 3:
                                    v_yyl.setBackgroundColor(Color.parseColor("#f49b25"));
                                    break;
                                case 4:
                                    v_yyl.setBackgroundColor(Color.parseColor("#e33532"));
                                    break;
                                case 5:
                                    v_yyl.setBackgroundColor(Color.parseColor("#b01e23"));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
                try {
                    jsonRequest.put("UserName", userName);
                    jsonRequest.put("RoadId", 4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_road_status", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int status = jsonObject.optInt("Status");
                            switch (status) {
                                case 1:
                                    v_xfl.setBackgroundColor(Color.parseColor("#6ab82e"));
                                    break;
                                case 2:
                                    v_xfl.setBackgroundColor(Color.parseColor("#ece93a"));
                                    break;
                                case 3:
                                    v_xfl.setBackgroundColor(Color.parseColor("#f49b25"));
                                    break;
                                case 4:
                                    v_xfl.setBackgroundColor(Color.parseColor("#e33532"));
                                    break;
                                case 5:
                                    v_xfl.setBackgroundColor(Color.parseColor("#b01e23"));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
                try {
                    jsonRequest.put("UserName", userName);
                    jsonRequest.put("RoadId", 5);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_road_status", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int status = jsonObject.optInt("Status");
//                            switch (status) {
//                                case 1:
//                                    v_xyl.setBackgroundColor(Color.parseColor("#6ab82e"));
//                                    break;
//                                case 2:
//                                    v_xyl.setBackgroundColor(Color.parseColor("#ece93a"));
//                                    break;
//                                case 3:
//                                    v_xyl.setBackgroundColor(Color.parseColor("#f49b25"));
//                                    break;
//                                case 4:
//                                    v_xyl.setBackgroundColor(Color.parseColor("#e33532"));
//                                    break;
//                                case 5:
//                                    v_xyl.setBackgroundColor(Color.parseColor("#b01e23"));
//                                    break;
//                                default:
//                                    break;
//                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
                try {
                    jsonRequest.put("UserName", userName);
                    jsonRequest.put("RoadId", 6);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_road_status", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int status = jsonObject.optInt("Status");
                            switch (status) {
                                case 1:
                                    v_hcgs.setBackgroundColor(Color.parseColor("#6ab82e"));
                                    break;
                                case 2:
                                    v_hcgs.setBackgroundColor(Color.parseColor("#ece93a"));
                                    break;
                                case 3:
                                    v_hcgs.setBackgroundColor(Color.parseColor("#f49b25"));
                                    break;
                                case 4:
                                    v_hcgs.setBackgroundColor(Color.parseColor("#e33532"));
                                    break;
                                case 5:
                                    v_hcgs.setBackgroundColor(Color.parseColor("#b01e23"));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
                try {
                    jsonRequest.put("UserName", userName);
                    jsonRequest.put("RoadId", 7);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_road_status", jsonRequest, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            int status = jsonObject.optInt("Status");
                            switch (status) {
                                case 1:
                                    v_tcc.setBackgroundColor(Color.parseColor("#6ab82e"));
                                    break;
                                case 2:
                                    v_tcc.setBackgroundColor(Color.parseColor("#ece93a"));
                                    break;
                                case 3:
                                    v_tcc.setBackgroundColor(Color.parseColor("#f49b25"));
                                    break;
                                case 4:
                                    v_tcc.setBackgroundColor(Color.parseColor("#e33532"));
                                    break;
                                case 5:
                                    v_tcc.setBackgroundColor(Color.parseColor("#b01e23"));
                                    break;
                                default:
                                    break;
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

        /**
         * 查询“所有传感器”的当前值http://localhost:8080/api/v2/get_all_sense
         */
        get_all_sense();

        /**
         * Date
         */
        tv_date.setText(new SimpleDateFormat("yyyy-M-d").format(new Date()));
        tv_week.setText(new SimpleDateFormat("EEEE").format(new Date()));

        /**
         * 动画
         */
        AnimationDrawable background1 = (AnimationDrawable) iv_1.getBackground();
        background1.start();
        AnimationDrawable background2 = (AnimationDrawable) iv_2.getBackground();
        background2.start();
    }

    private void get_all_sense() {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("UserName", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_all_sense", jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (jsonObject.optString("RESULT").equals("S")) {
                    Gson gson = new Gson();
                    Get_all_sense get_all_sense = gson.fromJson(jsonObject.toString(), Get_all_sense.class);
                    tv_temperature.setText(get_all_sense.getTemperature() + "℃");
                    tv_humidity.setText(get_all_sense.getHumidity() + "％");
                    tv_pm2_5.setText(get_all_sense.get_$Pm25126() + "g/m^3");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    private void initView(View view) {
        v_xyl = (TextView) view.findViewById(R.id.v_xyl);
        v_lxl = (TextView) view.findViewById(R.id.v_lxl);
        v_xfl = (TextView) view.findViewById(R.id.v_xfl);
        v_yyl = (TextView) view.findViewById(R.id.v_yyl);
        v_tcc = (TextView) view.findViewById(R.id.v_tcc);
        v_hcgs = (TextView) view.findViewById(R.id.v_hcgs);
        refresh = (ImageView) view.findViewById(R.id.refresh);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_week = (TextView) view.findViewById(R.id.tv_week);
        tv_temperature = (TextView) view.findViewById(R.id.tv_temperature);
        tv_humidity = (TextView) view.findViewById(R.id.tv_humidity);
        tv_pm2_5 = (TextView) view.findViewById(R.id.tv_pm2_5);
        iv_1 = (ImageView) view.findViewById(R.id.iv_1);
        iv_2 = (ImageView) view.findViewById(R.id.iv_2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }
}
