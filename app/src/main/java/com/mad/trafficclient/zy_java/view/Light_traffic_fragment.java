/**
 *
 */
package com.mad.trafficclient.zy_java.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class Light_traffic_fragment extends Fragment {

    private GridView gv_trafic_light;
    private Context context;
    private RequestQueue requestQueue;
    private String get_light_api;
    private String set_light_api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_traffic_light, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gv_trafic_light = (GridView) view.findViewById(R.id.gv_trafic_light);
        context = getContext();
        requestQueue = Volley.newRequestQueue(context);
        get_light_api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_trafficlight_config";
        set_light_api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_trafficlight_config";

    }

    private void getLight(int roadId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", "user1");
            jsonObject.put("TrafficId", roadId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, get_light_api, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }


    class Traffic_Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
