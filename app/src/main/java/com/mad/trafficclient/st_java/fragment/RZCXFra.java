package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import java.util.ArrayList;
import java.util.Date;

public class RZCXFra extends Fragment {
    private SwipeRefreshLayout swipe;
    private TextView tv_state;
    private RelativeLayout title;
    private ListView listView;
    private Context context;
    private String userName;
    private UrlBean urlBean;
    private RequestQueue requestQueue;
    private ArrayList<Get_all_sense> get_all_senses;
    private Ada ada;
    private ArrayList<String> strings;
    private SimpleDateFormat simpleDateFormat;
    private TextView tv_show;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    title.setVisibility(View.GONE);
                    ada.notifyDataSetChanged();
                    break;
            }
        }
    };
    private static final String TAG = "RZCXFra";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_rzcx, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tv_state.setText("正在刷新");
                title.setVisibility(View.VISIBLE);
                getData();
            }
        });
    }

    private void initData() {
        context = getContext();
        userName = Util.getUserName(context);
        urlBean = Util.loadSetting(context);
        requestQueue = Volley.newRequestQueue(context);
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

        listView.setEmptyView(tv_show);

        get_all_senses = new ArrayList<>();
        strings = new ArrayList<>();
        ada = new Ada();
        listView.setAdapter(ada);
    }

    private void getData() {
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
                    get_all_senses.add(0, get_all_sense);
                    strings.add(0, simpleDateFormat.format(new Date()));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
        requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_all_sense", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (jsonObject.optString("RESULT").equals("S")) {
                    Gson gson = new Gson();
                    Get_all_sense get_all_sense = gson.fromJson(jsonObject.toString(), Get_all_sense.class);
                    get_all_senses.add(0, get_all_sense);
                    strings.add(0, simpleDateFormat.format(new Date()));
                    Log.i(TAG, "onResponse: " + get_all_sense.getTemperature() + strings.get(strings.size() - 1));
                    if (swipe.isRefreshing()) {
                        swipe.setRefreshing(false);
                        tv_state.setText("刷新完成");
                        handler.sendEmptyMessageDelayed(0, 1000);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    private void initView(View view) {
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        tv_state = (TextView) view.findViewById(R.id.tv_state);
        title = (RelativeLayout) view.findViewById(R.id.title);
        listView = (ListView) view.findViewById(R.id.listView);
        tv_show = (TextView) view.findViewById(R.id.tv_show);
    }

    public class Ada extends BaseAdapter {

        @Override
        public int getCount() {
            return get_all_senses != null ? get_all_senses.size() : 0;
        }

        @Override
        public Get_all_sense getItem(int i) {
            return get_all_senses.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(context, R.layout.ada_rzcx, null);
            ViewHolder viewHolder = new ViewHolder(view);
            Get_all_sense get_all_sense = get_all_senses.get(i);
            viewHolder.co2.setText(get_all_sense.getCo2() + " PPM");
            viewHolder.humidity.setText(get_all_sense.getHumidity() + " RH");
            viewHolder.LightIntensity.setText(get_all_sense.getLightIntensity() + " SI");
            viewHolder.Pm25126.setText(get_all_sense.get_$Pm25126() + " μg/m3");
            viewHolder.temperature.setText(get_all_sense.getTemperature() + " ℃");
            viewHolder.tv_date.setText(strings.get(i));
            if (i == 0 || i == 1) {
                viewHolder.tv_date.setTextColor(Color.RED);
            }
            return view;
        }

        public class ViewHolder {
            public View rootView;
            public TextView co2;
            public TextView humidity;
            public TextView Pm25126;
            public TextView LightIntensity;
            public TextView temperature;
            public TextView tv_date;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.co2 = (TextView) rootView.findViewById(R.id.co2);
                this.humidity = (TextView) rootView.findViewById(R.id.humidity);
                this.Pm25126 = (TextView) rootView.findViewById(R.id.Pm25126);
                this.LightIntensity = (TextView) rootView.findViewById(R.id.LightIntensity);
                this.temperature = (TextView) rootView.findViewById(R.id.temperature);
                this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            }

        }
    }
}
