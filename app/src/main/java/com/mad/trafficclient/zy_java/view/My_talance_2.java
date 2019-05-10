/**
 *
 */
package com.mad.trafficclient.zy_java.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class My_talance_2 extends Fragment {

    private ListView my_talance_listview;
    private List<Integer> list;
    private Context context;
    private RequestQueue requestQueue;
    private String api_get;
    private String api_set;
    private boolean[] flag = new boolean[4];
    private Talacne_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.my_talance_cotrol_fragement, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        my_talance_listview = (ListView) view.findViewById(R.id.my_talance_listview);
        list = new ArrayList<>();
        context = getContext();

        api_get = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_move";
        api_set = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/set_car_move";
        requestQueue = Volley.newRequestQueue(context);
        for (int i = 0; i < 4; i++) {
            list.add(i + 1);
        }
        adapter = new Talacne_Adapter();
        my_talance_listview.setAdapter(adapter);
        for (int i = 0; i < 4; i++) {
            getCarMove(i + 1);
        }
    }

    private void getCarMove(final int id) {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", Util.getUserName(context));
            object.put("CarId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_get, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                String state = null;
                try {
                    state = jsonObject.getString("CarAction");
                    Log.i("取到小车的数据", "onResponse: "+jsonObject.toString());
                    if (state.equals("Stop")) {
                        flag[id - 1] = false;
                    } else {
                        flag[id - 1] = true;
                    }
                    Log.i("状态", "onResponse: "+ Arrays.asList(flag));
                    Toast.makeText(context, "读取成功", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "读取失败", Toast.LENGTH_SHORT).show();

            }
        }));

    }

    private void setCatMove(int id, boolean flag) {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName",  Util.getUserName(context));
            object.put("CarId", id);
            if (flag) {
                object.put("CarAction", "Start");

            } else {
                object.put("CarAction", "Stop");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_set, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    if (jsonObject.getString("RESULT").equals("S")) {

                        Toast.makeText(context, "设置成功", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "设置失败", Toast.LENGTH_SHORT).show();
            }
        }));

    }

    class Talacne_Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_mytalance_listview, null);
            final ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.car_id.setText(list.get(position) + "");
            if (flag[position]) {
                setCatMove(position + 1, true);
                viewHolder.item_ta_start.setBackgroundResource(R.drawable.lv_car);
                viewHolder.item_ta_stop.setBackgroundResource(R.drawable.bai);
            } else {
                setCatMove(position + 1, false);
                viewHolder.item_ta_start.setBackgroundResource(R.drawable.bai);
                viewHolder.item_ta_stop.setBackgroundResource(R.drawable.lv_car);
            }


            viewHolder.item_ta_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCatMove(position + 1, true);
                    viewHolder.item_ta_start.setBackgroundResource(R.drawable.lv_car);
                    viewHolder.item_ta_stop.setBackgroundResource(R.drawable.bai);
                }
            });
            viewHolder.item_ta_stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCatMove(position + 1, false);

                    viewHolder.item_ta_start.setBackgroundResource(R.drawable.bai);
                    viewHolder.item_ta_stop.setBackgroundResource(R.drawable.lv_car);
                }
            });
            return convertView;
        }

        public
        class ViewHolder {
            public View rootView;
            public TextView car_id;
            public TextView item_ta_start;
            public TextView item_ta_stop;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.car_id = (TextView) rootView.findViewById(R.id.car_id);
                this.item_ta_start = (TextView) rootView.findViewById(R.id.item_ta_start);
                this.item_ta_stop = (TextView) rootView.findViewById(R.id.item_ta_stop);
            }

        }
    }
}
