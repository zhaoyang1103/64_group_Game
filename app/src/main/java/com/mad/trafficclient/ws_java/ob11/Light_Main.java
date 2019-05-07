package com.mad.trafficclient.ws_java.ob11;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.mad.trafficclient.ws_java.ob9.Acc_Bean;
import com.mad.trafficclient.ws_java.ob9.Acc_Dao;
import com.mad.trafficclient.ws_java.ob9.JiluBean;
import com.mad.trafficclient.zy_java.data.CarData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/7 11:39
 */
public class Light_Main extends Fragment implements View.OnClickListener {
    private Spinner ob11_spinner;
    private ImageView ob11_img_light;
    private Button ob11_btn_chaxun;
    private Button ob11_btn_piliangshezhi;
    private ListView ob11_listview;
    private Context context;
    private List<LightBean> list;
    private int count;
    private LightAdapter lightAdapter;
    public static List<Boolean> checkbox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob11_main, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        checkbox = new ArrayList<>();
        lightAdapter = new LightAdapter();
        ob11_listview.setAdapter(lightAdapter);
        QueryLight();
        return view;
    }

    private void QueryLight() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_trafficlight_config";
        count = 0;
        list.clear();
        for (int i = 0; i < 5; i++) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("TrafficLightId", i + 1);
                jsonObject.put("UserName", Util.getUserName(context));

                final int finalI = i;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            Gson gson = new Gson();
                            LightBean lightBean = gson.fromJson(jsonObject.toString(), LightBean.class);
                            lightBean.setId(finalI + 1);
                            list.add(lightBean);
                            count++;
                            if (count == 5) {
                                PaiXu();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "查询红绿灯信息失败", Toast.LENGTH_SHORT).show();
                    }
                }));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void ChargeLight(final List<Integer> lightlist, int redTime, int greenTime, int yellowTime) {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/set_trafficlight_config";
        count = 0;
        for (int i = 0; i < lightlist.size(); i++) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("TrafficLightId", lightlist.get(i));
                jsonObject.put("RedTime", redTime);
                jsonObject.put("GreenTime", greenTime);
                jsonObject.put("YellowTime", yellowTime);
                jsonObject.put("UserName", Util.getUserName(context));
                Log.i("Go_Fight_Now 提醒您", "jsonObject.toString()" + ":" + jsonObject.toString());
                final int finalI = i;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                            count++;
                            if (count == lightlist.size()) {
                                for (int i = 0; i < 4; i++) {
                                    checkbox.set(i, false);
                                }
                                QueryLight();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
                    }
                }));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void PaiXu() {
        //升序
        if (ob11_spinner.getSelectedItemId() == 0) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return o1.getId() - o2.getId();
                }
            });
        }
        //降序
        if (ob11_spinner.getSelectedItemId() == 1) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return o2.getId() - o1.getId();
                }
            });
        }
        //升序
        if (ob11_spinner.getSelectedItemId() == 2) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return Integer.parseInt(o1.getRedTime()) - Integer.parseInt(o2.getRedTime());
                }
            });
        }
        //降序
        if (ob11_spinner.getSelectedItemId() == 3) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return Integer.parseInt(o2.getRedTime()) - Integer.parseInt(o1.getRedTime());
                }
            });
        }
        //升序
        if (ob11_spinner.getSelectedItemId() == 4) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return Integer.parseInt(o1.getGreenTime()) - Integer.parseInt(o2.getGreenTime());
                }
            });
        }
        //降序
        if (ob11_spinner.getSelectedItemId() == 5) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return Integer.parseInt(o2.getGreenTime()) - Integer.parseInt(o1.getGreenTime());
                }
            });
        }
        //升序
        if (ob11_spinner.getSelectedItemId() == 6) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return Integer.parseInt(o1.getYellowTime()) - Integer.parseInt(o2.getYellowTime());
                }
            });
        }
        //降序
        if (ob11_spinner.getSelectedItemId() == 7) {
            Collections.sort(list, new Comparator<LightBean>() {
                @Override
                public int compare(LightBean o1, LightBean o2) {
                    return Integer.parseInt(o2.getYellowTime()) - Integer.parseInt(o1.getYellowTime());
                }
            });
        }
        lightAdapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        ob11_spinner = (Spinner) view.findViewById(R.id.ob11_spinner);
        ob11_img_light = (ImageView) view.findViewById(R.id.ob11_img_light);
        ob11_btn_chaxun = (Button) view.findViewById(R.id.ob11_btn_chaxun);
        ob11_btn_piliangshezhi = (Button) view.findViewById(R.id.ob11_btn_piliangshezhi);
        ob11_listview = (ListView) view.findViewById(R.id.ob11_listview);

        ob11_btn_chaxun.setOnClickListener(this);
        ob11_btn_piliangshezhi.setOnClickListener(this);

        AnimationDrawable animationDrawable = (AnimationDrawable) ob11_img_light.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob11_btn_chaxun:
                PaiXu();
                break;
            case R.id.ob11_btn_piliangshezhi:
                final List<Integer> lightlist = new ArrayList<>();
                for (int i = 0; i < checkbox.size(); i++) {
                    if (checkbox.get(i)) {
                        lightlist.add(list.get(i).getId());
                    }
                }
                if (lightlist.size() > 0 && lightlist != null) {
                    final Dialog settingkuang = new Dialog(context);
                    settingkuang.setContentView(R.layout.ob11_setting);
                    settingkuang.setTitle("红绿灯设置");
                    settingkuang.show();
                    Button ob11_setting_quxiao = (Button) settingkuang.getWindow().findViewById(R.id.ob11_setting_quxiao);
                    Button ob11_setting_queding = (Button) settingkuang.getWindow().findViewById(R.id.ob11_setting_queding);
                    final EditText ob11_setting_green_time = (EditText) settingkuang.getWindow().findViewById(R.id.ob11_setting_green_time);
                    final EditText ob11_setting_yellow_time = (EditText) settingkuang.getWindow().findViewById(R.id.ob11_setting_yellow_time);
                    final EditText ob11_setting_red_time = (EditText) settingkuang.getWindow().findViewById(R.id.ob11_setting_red_time);
                    ob11_setting_quxiao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            settingkuang.dismiss();
                        }
                    });
                    ob11_setting_queding.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChargeLight(lightlist,
                                    Integer.parseInt(ob11_setting_red_time.getText().toString().trim()),
                                    Integer.parseInt(ob11_setting_green_time.getText().toString().trim()),
                                    Integer.parseInt(ob11_setting_yellow_time.getText().toString().trim()));
                            settingkuang.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(context, "请选择要修改的路口", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    class LightAdapter extends BaseAdapter {

        public LightAdapter() {
            for (int i = 0; i < 5; i++) {
                checkbox.add(false);
            }
        }

        @Override
        public int getCount() {
            return list.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.ob11_list_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob11_lv_id.setText(list.get(position).getId() + "");
            viewHolder.ob11_lv_red_time.setText(list.get(position).getRedTime());
            viewHolder.ob11_lv_yellow_time.setText(list.get(position).getYellowTime());
            viewHolder.ob11_lv_green_time.setText(list.get(position).getGreenTime());
            viewHolder.ob11_lv_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkbox.set(position, isChecked);
                }
            });

            viewHolder.ob11_lv_setting.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    final Dialog settingkuang = new Dialog(context);
                    settingkuang.setContentView(R.layout.ob11_setting);
                    settingkuang.setTitle("红绿灯设置");
                    settingkuang.show();
                    Button ob11_setting_quxiao = (Button) settingkuang.getWindow().findViewById(R.id.ob11_setting_quxiao);
                    Button ob11_setting_queding = (Button) settingkuang.getWindow().findViewById(R.id.ob11_setting_queding);
                    final EditText ob11_setting_green_time = (EditText) settingkuang.getWindow().findViewById(R.id.ob11_setting_green_time);
                    final EditText ob11_setting_yellow_time = (EditText) settingkuang.getWindow().findViewById(R.id.ob11_setting_yellow_time);
                    final EditText ob11_setting_red_time = (EditText) settingkuang.getWindow().findViewById(R.id.ob11_setting_red_time);
                    ob11_setting_quxiao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            settingkuang.dismiss();
                        }
                    });
                    ob11_setting_queding.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Integer> lightid = new ArrayList<>();
                            lightid.add(list.get(position).getId());
                            ChargeLight(lightid,
                                    Integer.parseInt(ob11_setting_red_time.getText().toString().trim()),
                                    Integer.parseInt(ob11_setting_green_time.getText().toString().trim()),
                                    Integer.parseInt(ob11_setting_yellow_time.getText().toString().trim()));
                            settingkuang.dismiss();
                        }
                    });
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ob11_lv_id;
            public TextView ob11_lv_red_time;
            public TextView ob11_lv_yellow_time;
            public TextView ob11_lv_green_time;
            public CheckBox ob11_lv_check;
            public Button ob11_lv_setting;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob11_lv_id = (TextView) rootView.findViewById(R.id.ob11_lv_id);
                this.ob11_lv_red_time = (TextView) rootView.findViewById(R.id.ob11_lv_red_time);
                this.ob11_lv_yellow_time = (TextView) rootView.findViewById(R.id.ob11_lv_yellow_time);
                this.ob11_lv_green_time = (TextView) rootView.findViewById(R.id.ob11_lv_green_time);
                this.ob11_lv_check = (CheckBox) rootView.findViewById(R.id.ob11_lv_check);
                this.ob11_lv_setting = (Button) rootView.findViewById(R.id.ob11_lv_setting);
            }

        }
    }
}
