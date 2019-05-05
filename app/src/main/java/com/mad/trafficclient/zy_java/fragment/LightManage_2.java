/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.zy_java.adapter.LightManageAdapter;
import com.mad.trafficclient.zy_java.bean.LightManageBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LightManage_2 extends Fragment implements View.OnClickListener {

    private Spinner spinner;
    private Button bt_querylight;
    private ListView lv_light;
    private List<LightManageBean> list;
    private Context context;
    private RequestQueue requestQueue;
    private LightManageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.lightmanage_2, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        bt_querylight = (Button) view.findViewById(R.id.bt_querylight);
        lv_light = (ListView) view.findViewById(R.id.lv_light);
        bt_querylight.setOnClickListener(this);
        context = getContext();
        list = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(context);
        adapter = new LightManageAdapter(list, context);
        lv_light.setAdapter(adapter);
        getLightData();
    }

    private void getLightData() {
        list.clear();
        String api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_trafficlight_config";
        for (int i = 0; i < 3; i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("UserName", "user1");
                object.put("TrafficLightId", i + 1);

                final int finalI = i;
                requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        LightManageBean lightManageBean = gson.fromJson(jsonObject.toString(), LightManageBean.class);
                        lightManageBean.setRoad(finalI + 1);
                        list.add(lightManageBean);
//                        Log.i("数据集合", "onResponse: "+ Arrays.asList(list));


                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_querylight:
                sortData();
                break;
        }
    }

    private void sortData() {
        if (spinner.getSelectedItemId() == 0) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o1.getRoad() - o2.getRoad();
                }
            });
        }
        if (spinner.getSelectedItemId() == 1) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o2.getRoad() - o1.getRoad();
                }
            });
        }

        if (spinner.getSelectedItemId() == 2) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o1.getRedTime() - o2.getRedTime();
                }
            });
        }
        if (spinner.getSelectedItemId() == 3) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o2.getRedTime() - o1.getRedTime();
                }
            });
        }


        if (spinner.getSelectedItemId() == 4) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o1.getGreenTime() - o2.getGreenTime();
                }
            });
        }
        if (spinner.getSelectedItemId() == 5) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o2.getGreenTime() - o1.getGreenTime();
                }
            });
        }


        if (spinner.getSelectedItemId() == 6) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o1.getYellowTime() - o2.getYellowTime();
                }
            });
        }
        if (spinner.getSelectedItemId() == 7) {
            Collections.sort(list, new Comparator<LightManageBean>() {
                @Override
                public int compare(LightManageBean o1, LightManageBean o2) {
                    return o2.getYellowTime() - o1.getYellowTime();
                }
            });
        }


        adapter.notifyDataSetChanged();

    }


}
