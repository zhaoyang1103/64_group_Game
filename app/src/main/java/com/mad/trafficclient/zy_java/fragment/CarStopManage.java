/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static android.util.Log.i;


public class CarStopManage extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private TextView tx_candar;
    private TextView tx_carwhat;
    private ImageView image_lisght;
    private Switch switch_2;
    private Switch switch_3;
    private Switch switch_1;
    private Context context;
    private RequestQueue requestQueue;
    private String api_get = "";
    private boolean flag;
    private Switch[] switches;
    private String api_set;
    private boolean[] flagss;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.car_stop_manage, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        tx_candar = (TextView) view.findViewById(R.id.tx_candar);
        tx_carwhat = (TextView) view.findViewById(R.id.tx_carwhat);
        image_lisght = (ImageView) view.findViewById(R.id.image_lisght);
        switch_2 = (Switch) view.findViewById(R.id.switch_2);
        switch_3 = (Switch) view.findViewById(R.id.switch_3);
        switch_1 = (Switch) view.findViewById(R.id.switch_1);
        flagss = new boolean[3];
        switches = new Switch[]{switch_1, switch_2, switch_3};
        context = getContext();
        requestQueue = Volley.newRequestQueue(context);
        api_get = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_move ";
        api_set = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/set_car_move  ";
        AnimationDrawable animationDrawable = (AnimationDrawable) image_lisght.getDrawable();
        animationDrawable.start();
        switch_1.setOnClickListener(this);
        switch_2.setOnClickListener(this);
        switch_3.setOnClickListener(this);
        switch_1.setOnCheckedChangeListener(this);
        switch_2.setOnCheckedChangeListener(this);
        switch_3.setOnCheckedChangeListener(this);
        long l = System.currentTimeMillis();
        String format = simpleDateFormat.format(l);
        tx_candar.setText(format);
        int i = Integer.parseInt(format.substring(8, 10));
        int day=   format.charAt(9);
//            int day=simpleDateFormat.parse(format).getDay()+1;
            showData(i);
            i("CarStopManage", "date日期"+i);

        tx_candar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void showData(int dd) {

        if (dd % 2 == 0) {
            tx_carwhat.setText("双号属性车辆：2");
            switch_1.setChecked(false);
            switch_3.setChecked(false);
            switch_1.setEnabled(false);
            switch_3.setEnabled(false);
            switch_2.setEnabled(true);
            getCarMove(2);
        } else {
            tx_carwhat.setText("单号属性车辆：1,3");
            switch_2.setEnabled(false);
            switch_2.setChecked(false);
            switch_3.setEnabled(true);
            switch_1.setEnabled(true);
            getCarMove(1);
            getCarMove(3);

        }

    }

    private void getCarMove(final int id) {
        JSONObject object = new JSONObject();
        try {
            object.put("UserName", "user1");
            object.put("CarId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_set, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String state = jsonObject.getString("CarAction");

                    if (state.equals("Stop")) {
                        switches[id - 1].setChecked(false);
                    } else {
                        switches[id - 1].setChecked(true);
                    }
                    Toast.makeText(context, "读取成功", Toast.LENGTH_SHORT).show();

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
            object.put("UserName", "user1");
            object.put("CarId", id);
            if (flag) {
                object.put("CarAction", "Start");

            } else {
                object.put("CarAction", "Stop");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_get, object, new Response.Listener<JSONObject>() {
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_1:
//                setCatMove(1, switch_1.isChecked());
                break;
            case R.id.switch_2:
                break;
            case R.id.switch_3:
                break;
        }

    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View view = View.inflate(context, R.layout.caldarview, null);
        final DatePicker viewById = view.findViewById(R.id.datePicker);
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tx_candar.setText(viewById.getYear() + "年" + viewById.getMonth() + "月" + viewById.getDayOfMonth() + "日");
                showData(viewById.getDayOfMonth());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_1:
                setCatMove(1, isChecked);
                flagss[0] = isChecked;
                break;
            case R.id.switch_2:
                setCatMove(2, isChecked);
                flagss[1] = isChecked;
                break;
            case R.id.switch_3:
                setCatMove(3, isChecked);
                flagss[2] = isChecked;
                break;
        }
    }
}
