package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.mad.trafficclient.st_java.fragment.sshjfra.SSHJFra1;
import com.mad.trafficclient.st_java.fragment.sshjfra.SSHJFra2;
import com.mad.trafficclient.st_java.fragment.sshjfra.SSHJFra3;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.zy_java.view.GlideView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SSHJFra extends Fragment {
    private ViewPager viewPager;
    private GlideView glideView;
    private Context context;
    private UrlBean urlBean;
    private String userName;
    private RequestQueue requestQueue;
    private Timer timer;
    private SharedPreferences sshjFra_sp;
    private ArrayList<Fragment> fragments;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_sshj, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                glideView.setIndex(context, position);
                switch (position) {
                    case 0:
                        title.setText("PM2.5指数");
                        break;
                    case 1:
                        title.setText("空气温度指数");
                        break;
                    case 2:
                        title.setText("光照强度指数");
                        break;
                }
            }
        });
    }

    private void initData() {
        context = getContext();
        urlBean = Util.loadSetting(context);
        userName = Util.getUserName(context);
        requestQueue = Volley.newRequestQueue(context);
        timer = new Timer();
        sshjFra_sp = context.getSharedPreferences("SSHJFra_sp", Context.MODE_PRIVATE);
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
                            sshjFra_sp.edit().putString("get_$Pm25126", sshjFra_sp.getString("get_$Pm25126", "") + get_all_sense.get_$Pm25126() + "-").commit();
                            sshjFra_sp.edit().putString("getTemperature", sshjFra_sp.getString("getTemperature", "") + get_all_sense.getTemperature() + "-").commit();
                            sshjFra_sp.edit().putString("getLightIntensity", sshjFra_sp.getString("getLightIntensity", "") + get_all_sense.getLightIntensity() + "-").commit();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));
            }
        }, 0, 3000);
        fragments = new ArrayList<>();
        fragments.add(new SSHJFra1());
        fragments.add(new SSHJFra2());
        fragments.add(new SSHJFra3());
        viewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        glideView.setCount(3);
        glideView.setIndex(context, 0);
        title.setText("PM2.5指数");

    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        glideView = (GlideView) view.findViewById(R.id.glideView);
        title = (TextView) view.findViewById(R.id.title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
