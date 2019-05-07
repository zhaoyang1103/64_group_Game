/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.zy_java.util.ZyUtil;
import com.mad.trafficclient.zy_java.view.PeccancyQuery_2;

import org.json.JSONException;
import org.json.JSONObject;


public class PeccancyQuery_1 extends Fragment implements View.OnClickListener {

    private EditText edit_query;
    private Button bt_query;
    private String get_sinpeccancy_api = "";
    private Context context;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.peccancy_query_1, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        context = getContext();
        edit_query = (EditText) view.findViewById(R.id.edit_query);
        bt_query = (Button) view.findViewById(R.id.bt_query);
        bt_query.setOnClickListener(this);
        get_sinpeccancy_api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_peccancy";
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_query:
                submit();
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
                    Log.i("查询结果", "onResponse: "+jsonObject.toString());
                    try {
                        if (jsonObject.getString("RESULT").equals("S")) {
                            Toast.makeText(context, "查询成功", Toast.LENGTH_SHORT).show();
                            ZyUtil.saveCarNumber(context, s);
                            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent,new PeccancyQuery_2()).commit();
                        } else {
                            Toast.makeText(context, "查询失败 此车没违章", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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

    private void submit() {
        // validate
        String query = edit_query.getText().toString().trim();
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(getContext(), "query不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String s = "鲁" + edit_query.getText().toString().trim();
            Log.i("发送的车牌号", "submit: "+s);
            jundagecarnumber(s);
        }

        // TODO validate success, do something


    }
}
