/**
 *
 */
package com.mad.trafficclient.zy_java.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.zy_java.adapter.Peccancy_leftAdapter;
import com.mad.trafficclient.zy_java.adapter.Peccancy_rightAdapter;
import com.mad.trafficclient.zy_java.bean.Peccancy_rightBean;
import com.mad.trafficclient.zy_java.fragment.PeccancyQuery_1;
import com.mad.trafficclient.zy_java.fragment.VideoFragment;
import com.mad.trafficclient.zy_java.util.ZyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PeccancyQuery_2 extends Fragment implements View.OnClickListener {

    private ListView left_listview;
    private ListView right_listview;
    private ImageView image_return;
    private FragmentTransaction fragmentTransaction;
    private Context context;
    private RequestQueue requestQueue;
    private String get_sinpeccancy_api;
    private List<Peccancy_rightBean.CarActionBean> right_list;
    private Peccancy_rightAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.peccancy_query_2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        left_listview = (ListView) view.findViewById(R.id.left_listview);
        right_listview = (ListView) view.findViewById(R.id.right_listview);
        image_return = (ImageView) view.findViewById(R.id.image_return);
        image_return.setOnClickListener(this);
        context = getContext();
        get_sinpeccancy_api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_peccancy";
        fragmentTransaction = getFragmentManager().beginTransaction();
        requestQueue = Volley.newRequestQueue(context);
        final List<String> list = ZyUtil.getCarnumber(context);
        Peccancy_leftAdapter peccancy_leftAdapter = new Peccancy_leftAdapter(list, context);
        left_listview.setAdapter(peccancy_leftAdapter);

        peccancy_leftAdapter.notifyDataSetChanged();
        //适配器绑定list   外list控制里list变化
        left_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jundagecarnumber(list.get(position));
                Log.i("车牌bug测试", "onItemClick: " + list.get(position));
            }
        });
        right_list = new ArrayList<>();
        adapter = new Peccancy_rightAdapter(right_list, context);
        right_listview.setAdapter(adapter);
        if (list.size() > 0) {
            jundagecarnumber(list.get(0));
        }
        right_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragmentTransaction.replace(R.id.maincontent,new VideoFragment()).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_return:
                fragmentTransaction.replace(R.id.maincontent, new PeccancyQuery_1()).commit();
                break;
        }
    }

    private void jundagecarnumber(final String s) {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", "user1");
            object.put("carnumber", s);
            requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, get_sinpeccancy_api, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Gson gson = new Gson();
                    Peccancy_rightBean peccancy_rightBean = gson.fromJson(jsonObject.toString(), Peccancy_rightBean.class);
                    List<Peccancy_rightBean.CarActionBean> carAction_list = peccancy_rightBean.getCarAction();
                    Log.i("违章尺寸", "onResponse: " + carAction_list.size());
                    adapter = new Peccancy_rightAdapter(carAction_list, context);
//                    right_list=carAction_list;
                    right_listview.setAdapter(adapter);
//                    right_listview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, " 网络异常", Toast.LENGTH_SHORT).show();
                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
