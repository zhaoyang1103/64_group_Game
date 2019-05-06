package com.mad.trafficclient.ws_java.ob1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Go_Fight_Now on 2019/5/5 19:27
 */
public class Account_Main extends Fragment implements View.OnClickListener {
    private TextView ob1_tv_balance;
    private Spinner ob1_spinner;
    private EditText ob1_edi_jine;
    private Button ob1_btn_query;
    private Button ob1_btn_charge;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob1_main, container, false);
        context = getContext();
        initView(view);
        QueryBalance(1);
        return view;
    }

    private void QueryBalance(int carid) {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_account_balance";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CarId", carid);
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        Toast.makeText(context, "查询成功", Toast.LENGTH_SHORT).show();
                        ob1_tv_balance.setText(jsonObject.optInt("Balance") + "元");
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

    private void ChargeBlance(final int carid, final int money) {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/set_car_account_recharge";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CarId", carid);
            jsonObject.put("Money", money);
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
                        AccountBean bean = new AccountBean(carid,money,Util.getUserName(context), new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()));
                        Log.i("Account_Main", "bean.toString()" + ":" + bean.toString());
                        new AccountDao(context).add(bean);
                        QueryBalance(carid);
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
        ob1_tv_balance = (TextView) view.findViewById(R.id.ob1_tv_balance);
        ob1_spinner = (Spinner) view.findViewById(R.id.ob1_spinner);
        ob1_edi_jine = (EditText) view.findViewById(R.id.ob1_edi_jine);
        ob1_btn_query = (Button) view.findViewById(R.id.ob1_btn_query);
        ob1_btn_charge = (Button) view.findViewById(R.id.ob1_btn_charge);

        ob1_btn_query.setOnClickListener(this);
        ob1_btn_charge.setOnClickListener(this);
//        ob1_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
//                QueryBalance(position + 1);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        ob1_edi_jine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if (!string.equals("")){
                    if (Integer.parseInt(string)<1 || Integer.parseInt(string)>999){
                        s.clear();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob1_btn_query:
                QueryBalance((int) ob1_spinner.getSelectedItemId() + 1);
                break;
            case R.id.ob1_btn_charge:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String jine = ob1_edi_jine.getText().toString().trim();
        if (TextUtils.isEmpty(jine)) {
            Toast.makeText(getContext(), "充值金额不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ChargeBlance((int) ob1_spinner.getSelectedItemId() + 1, Integer.parseInt(jine));
        }

        // TODO validate success, do something


    }
}
