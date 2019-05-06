package com.mad.trafficclient.ws_java.ob5;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Go_Fight_Now on 2019/5/6 16:43
 */
public class Sense_Main extends Fragment {
    private GridView ob5_gridview;
    private List<SenseBean> list;
    private Context context;
    private Timer timer;
    private int count;
    private SenseAdapter adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i("Sense_Main", "count" + ":" + count);
            if (count == 6) {
                adapter.notifyDataSetChanged();
                count = 0;
            }
        }
    };
    private SenseDao senseDao;
    private IndexBean indexBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob5_main, container, false);
        initView(view);
        context = getContext();
        senseDao = new SenseDao(context);
        list = new ArrayList<>();
        adapter = new SenseAdapter(context,list);
        ob5_gridview.setAdapter(adapter);
        indexBean=new IndexBean();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                list.clear();
                GetAllSense();
                GetRoadStatus();
            }
        },0,3000);
        return view;
    }

    public void GetAllSense(){
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_sense";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")){
                        list.add(0,new SenseBean("温度",jsonObject.optInt("temperature"),SenseUtil.getYuzhi(context,"wendu")));
                        list.add(1,new SenseBean("湿度",jsonObject.optInt("humidity"),SenseUtil.getYuzhi(context,"shidu")));
                        list.add(2,new SenseBean("光照强度",jsonObject.optInt("LightIntensity"),SenseUtil.getYuzhi(context,"guang")));
                        list.add(3,new SenseBean("Co2",jsonObject.optInt("co2"),SenseUtil.getYuzhi(context,"co2")));
                        list.add(4,new SenseBean("PM2.5",jsonObject.optInt("pm2.5"),SenseUtil.getYuzhi(context,"pm25")));
                        indexBean = new Gson().fromJson(jsonObject.toString(), IndexBean.class);
                        indexBean.setTime(new SimpleDateFormat("hh:mm").format(System.currentTimeMillis()));
                        count+=5;
                        handler.sendEmptyMessage(0);
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
    public void GetRoadStatus(){
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_road_status";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("RoadId",1);
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")){
                        list.add(new SenseBean("道路状态",jsonObject.optInt("Status"),SenseUtil.getYuzhi(context,"status")));
                        count++;
                        indexBean.setStatus(jsonObject.optInt("Status"));
                        senseDao.add(indexBean);
                        handler.sendEmptyMessage(0);
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

    private void initView(View view) {
        ob5_gridview = (GridView) view.findViewById(R.id.ob5_gridview);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }
}
