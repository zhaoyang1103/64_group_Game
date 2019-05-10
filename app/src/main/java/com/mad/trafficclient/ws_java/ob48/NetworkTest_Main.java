package com.mad.trafficclient.ws_java.ob48;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.mad.trafficclient.ws_java.ob5.IndexBean;
import com.mad.trafficclient.ws_java.ob5.SenseBean;
import com.mad.trafficclient.ws_java.ob5.SenseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by Go_Fight_Now on 2019/5/9 20:07
 */
public class NetworkTest_Main extends Fragment implements View.OnClickListener {
    private Button ob48_btn_chaxun;
    private TextView o48_tv_results;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob48_main, container, false);
        initView(view);
        context = getContext();
        return view;
    }

    public void GetAllSense() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_all_sense";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    if (jsonObject.optString("RESULT").equals("S")) {
                        o48_tv_results.setText("返回结果：" + jsonObject.toString());
                        builder.setMessage("网络通畅");
                        builder.show();
                    } else {

                        o48_tv_results.setText("返回结果：网络弟弟了！");
                        builder.setMessage("网络连接失败");
                        builder.show();
                    }
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

    private void initView(View view) {
        ob48_btn_chaxun = (Button) view.findViewById(R.id.ob48_btn_chaxun);
        o48_tv_results = (TextView) view.findViewById(R.id.o48_tv_results);

        ob48_btn_chaxun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob48_btn_chaxun:
                GetAllSense();
                break;
        }
    }
}
