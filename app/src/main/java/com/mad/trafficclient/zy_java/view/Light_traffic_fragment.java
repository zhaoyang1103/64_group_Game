/**
 *
 */
package com.mad.trafficclient.zy_java.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
import com.mad.trafficclient.zy_java.bean.LightBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Light_traffic_fragment extends Fragment {

    private GridView gv_trafic_light;
    private Context context;
    private RequestQueue requestQueue;
    private String get_light_api;
    private String set_light_api;
    private List<LightBean> list_light;
    private Traffic_Adapter adapter;
    private Spinner ob11_spinner;
    private ImageView ob11_img_light;
    private Button ob11_btn_chaxun;
    private Button ob11_btn_piliangshezhi;
    private ListView ob11_listview;
    private EditText ob11_setting_red_time;
    private EditText ob11_setting_yellow_time;
    private EditText ob11_setting_green_time;
    private Button ob11_setting_queding;
    private Button ob11_setting_quxiao;
    private String[] strings = new String[]{"红灯", "黄灯", "绿灯", "黄灯"};
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            for (int i = 0; i < 4; i++) {
                String change_time_heng = list_light.get(i).getChange_time_heng();
                String change_time_zong = list_light.get(i).getChange_time_zong();
                Pattern pattern = Pattern.compile(rege);
                Matcher matcher = pattern.matcher(change_time_heng);
                Matcher matcher_1 = pattern.matcher(change_time_zong);
                if (matcher.find()) {
                    int group = Integer.parseInt(matcher.group());

                }
                if (matcher_1.find()) {
                    int gorup_2 = Integer.parseInt(matcher_1.group());
                }


            }
            super.handleMessage(msg);
        }
    };
    private Timer timer;
    private String rege;

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
        set_light_api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/set_trafficlight_config";
        list_light = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list_light.add(new LightBean(100, 100, 100));
            list_light.get(i).setChange_time_zong("绿灯15秒");
            list_light.get(i).setChange_time_heng("黄灯15秒");
        }
        rege = "(\\d)+";

        for (int i = 0; i < 4; i++) {
            getLight(i + 1);
        }
        adapter = new Traffic_Adapter();
        gv_trafic_light.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        gv_trafic_light.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
                showDialog(position + 1);
            }
        });


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 1000);
    }

    private void showDialog(final int roadId) {
        View view_dialgo = View.inflate(context, R.layout.ob11_setting, null);
        ob11_setting_red_time = (EditText) view_dialgo.findViewById(R.id.ob11_setting_red_time);
        ob11_setting_yellow_time = (EditText) view_dialgo.findViewById(R.id.ob11_setting_yellow_time);
        ob11_setting_green_time = (EditText) view_dialgo.findViewById(R.id.ob11_setting_green_time);
        ob11_setting_queding = (Button) view_dialgo.findViewById(R.id.ob11_setting_queding);
        ob11_setting_quxiao = (Button) view_dialgo.findViewById(R.id.ob11_setting_quxiao);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setView(view_dialgo);
        ob11_setting_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(roadId);
            }
        });
        final AlertDialog show = dialog.show();

        ob11_setting_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
    }

    private void getLight(final int roadId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", "user1");
            jsonObject.put("TrafficLightId", roadId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, get_light_api, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                LightBean lightBean = gson.fromJson(jsonObject.toString(), LightBean.class);
                list_light.set(roadId - 1, lightBean);
                list_light.get(roadId - 1).setChange_time_heng(strings[0] + list_light.get(roadId - 1).getRedTime() + "秒");
                list_light.get(roadId - 1).setChange_time_zong(strings[2] + list_light.get(roadId - 1).getGreenTime() + "秒");
                Message message = new Message();
                message.arg1 = 0;
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "查询失败", Toast.LENGTH_SHORT).show();
            }
        }));

    }


    private void submit(int roadId) {
        // validate
        String time = ob11_setting_red_time.getText().toString().trim();
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(getContext(), "time不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String time_1 = ob11_setting_yellow_time.getText().toString().trim();
        if (TextUtils.isEmpty(time_1)) {
            Toast.makeText(getContext(), "time不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String time_2 = ob11_setting_green_time.getText().toString().trim();
        if (TextUtils.isEmpty(time_2)) {
            Toast.makeText(getContext(), "time不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            setLight(roadId);
        }

        // TODO validate success, do something


    }

    private void setLight(final int roadId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", "user1");
            jsonObject.put("TrafficLightId", roadId);
            jsonObject.put("RedTime", Integer.parseInt(ob11_setting_red_time.getText().toString()));
            jsonObject.put("GreenTime", Integer.parseInt(ob11_setting_green_time.getText().toString()));
            jsonObject.put("YellowTime", Integer.parseInt(ob11_setting_yellow_time.getText().toString()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, set_light_api, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                try {
                    if (jsonObject.getString("RESULT").equals("S")) {
                        Toast.makeText(getContext(), "红绿灯设置成功", Toast.LENGTH_SHORT).show();
                        getLight(roadId);

                    }

//                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
            return list_light.size();
        }

        @Override
        public Object getItem(int position) {
            return list_light.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_my_traffic_light, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.item_green_time_1.setText("绿灯" + list_light.get(position).getGreenTime() + "秒");
            viewHolder.item_red_time_3.setText("红灯" + list_light.get(position).getRedTime() + "秒");
            viewHolder.item_yellow_time_2.setText("黄灯" + list_light.get(position).getYellowTime() + "秒");
            viewHolder.item_change_time_heng.setText(list_light.get(position).getChange_time_heng());
            viewHolder.item_change_time_zong.setText(list_light.get(position).getChange_time_zong());
            viewHolder.image_heng_show.setImageResource(list_light.get(position).getImage_heng());
            viewHolder.image_zong_show.setImageResource(list_light.get(position).getImage_zong());
            final String green = "绿灯30秒";

            viewHolder.bt_hengxaing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_light.get(position).setChange_time_heng(green);
                    notifyDataSetChanged();
                }
            });
            viewHolder.bt_zongxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_light.get(position).setChange_time_zong(green);
                    notifyDataSetChanged();
                }
            });

            viewHolder.image_heng_show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(position + 1);
                }
            });
            return convertView;
        }


        public
        class ViewHolder {
            public View rootView;
            public TextView item_road;
            public TextView item_green_time_1;
            public TextView item_yellow_time_2;
            public TextView item_red_time_3;
            public TextView item_change_time_heng;
            public TextView item_change_time_zong;
            public ImageView image_heng_show;
            public ImageView image_zong_show;
            public Button bt_hengxaing;
            public Button bt_zongxiang;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.item_road = (TextView) rootView.findViewById(R.id.item_road);
                this.item_green_time_1 = (TextView) rootView.findViewById(R.id.item_green_time_1);
                this.item_yellow_time_2 = (TextView) rootView.findViewById(R.id.item_yellow_time_2);
                this.item_red_time_3 = (TextView) rootView.findViewById(R.id.item_red_time_3);
                this.item_change_time_heng = (TextView) rootView.findViewById(R.id.item_change_time_heng);
                this.item_change_time_zong = (TextView) rootView.findViewById(R.id.item_change_time_zong);
                this.image_heng_show = (ImageView) rootView.findViewById(R.id.image_heng_show);
                this.image_zong_show = (ImageView) rootView.findViewById(R.id.image_zong_show);
                this.bt_hengxaing = (Button) rootView.findViewById(R.id.bt_hengxaing);
                this.bt_zongxiang = (Button) rootView.findViewById(R.id.bt_zongxiang);
            }

        }
    }
}
