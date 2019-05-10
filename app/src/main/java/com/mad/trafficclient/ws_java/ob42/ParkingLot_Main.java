package com.mad.trafficclient.ws_java.ob42;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/9 19:37
 */
public class ParkingLot_Main extends Fragment implements View.OnClickListener {
    private ImageView ob42_img;
    private TextView ob42_tv_name;
    private TextView ob42_tv_standard;
    private TextView ob42_tv_spare;
    private Button ob42_btn_refresh;
    private Context context;
    private List<ParkingLotBean.ROWSDETAILBean> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob42_main, container, false);
        initView(view);
        context = getContext();
        return view;
    }

    private void QueryPark() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_park";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        Toast.makeText(context, "查询成功", Toast.LENGTH_SHORT).show();
                        list = new Gson().fromJson(jsonObject.toString(), ParkingLotBean.class).getROWS_DETAIL();
                        ob42_tv_name.setText(list.get(0).getParking_name());
                        ob42_tv_standard.setText("收费标准：" + list.get(0).getPayment_method());
                        ob42_tv_spare.setText("剩余车位：" + list.get(0).getFree_parking_number() + "/" + list.get(0).getTotal_parking_number());
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

    private void initView(View view) {
        ob42_img = (ImageView) view.findViewById(R.id.ob42_img);
        ob42_tv_name = (TextView) view.findViewById(R.id.ob42_tv_name);
        ob42_tv_standard = (TextView) view.findViewById(R.id.ob42_tv_standard);
        ob42_tv_spare = (TextView) view.findViewById(R.id.ob42_tv_spare);
        ob42_btn_refresh = (Button) view.findViewById(R.id.ob42_btn_refresh);

        ob42_btn_refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob42_btn_refresh:
                QueryPark();
                break;
        }
    }
}
