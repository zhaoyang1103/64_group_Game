package com.mad.trafficclient.ws_java.ob40;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.ws_java.ob1.AccountBean;
import com.mad.trafficclient.ws_java.ob1.AccountDao;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Go_Fight_Now on 2019/5/9 17:40
 */
public class ICRecharge extends Fragment implements View.OnClickListener {
    private TextView ob40_tv_balance;
    private EditText ob40_edi_jine;
    private Button ob40_btn_chongzhi;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob40_main, container, false);
        initView(view);
        context = getContext();
        QueryBalance();
        return view;
    }

    private void QueryBalance() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_account_balance";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CarId", 1);
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        Toast.makeText(context, "查询成功", Toast.LENGTH_SHORT).show();
                        ob40_tv_balance.setText(jsonObject.optInt("Balance") + "");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void ChargeBlance(final int money) {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/set_car_account_recharge";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CarId", 1);
            jsonObject.put("Money", money);
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
                        ob40_edi_jine.setText("");
                        QueryBalance();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, "充值失败", Toast.LENGTH_SHORT).show();
                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initView(View view) {
        ob40_tv_balance = (TextView) view.findViewById(R.id.ob40_tv_balance);
        ob40_edi_jine = (EditText) view.findViewById(R.id.ob40_edi_jine);
        ob40_btn_chongzhi = (Button) view.findViewById(R.id.ob40_btn_chongzhi);

        ob40_btn_chongzhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob40_btn_chongzhi:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String jine = ob40_edi_jine.getText().toString().trim();
        if (TextUtils.isEmpty(jine)) {
            Toast.makeText(getContext(), "金额不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ChargeBlance(Integer.parseInt(jine));
        }

        // TODO validate success, do something


    }
}
