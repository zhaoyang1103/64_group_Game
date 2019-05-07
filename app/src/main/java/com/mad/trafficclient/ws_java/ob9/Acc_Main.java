package com.mad.trafficclient.ws_java.ob9;


import android.app.Dialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.ListView;
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
 * Created by Go_Fight_Now on 2019/5/6 20:12
 */
public class Acc_Main extends Fragment implements View.OnClickListener {

    private Context context;
    private Acc_Adapter adapter;

    public interface To {
        void Caidan();

        void Jilu();
    }

    private To to;
    private ImageView imageView_Sliding;
    private TextView tv_title;
    private Button piliangchongzhi;
    private Button chongzhijilu;
    private LinearLayout top_title;
    private int count;
    private ListView ob9_listview;
    private List<Acc_Bean> list;
    public static List<Boolean> checkbox;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob9_main, container, false);
        initView(view);
        context = getContext();
        to = (To) getContext();
        list = new ArrayList<>();
        checkbox = new ArrayList<>();
        QueryBalance();
        return view;
    }

    private void QueryBalance() {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_account_balance";
        count = 0;
        list.clear();
        for (int i = 0; i < 4; i++) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CarId", i + 1);
                jsonObject.put("UserName", Util.getUserName(context));

                final int finalI = i;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            Log.i("Acc_Main", "Allcar_list().size()" + ":" + CarData.getAllcar_list().size());
                            Log.i("Acc_Main", "getallperson_list().size()" + ":" + CarData.getallperson_list().size());
                            for (int i = 0; i < CarData.getAllcar_list().size(); i++) {
                                for (int j = 0; j < CarData.getallperson_list().size(); j++) {
                                    if (CarData.getAllcar_list().get(i).getPcardid().equals(CarData.getallperson_list().get(j).getPcardid())) {
                                        if (finalI + 1 == CarData.getAllcar_list().get(i).getNumber()) {
                                            Acc_Bean balance = new Acc_Bean(finalI + 1,
                                                    CarData.getAllcar_list().get(i).getCarbrand(),
                                                    CarData.getAllcar_list().get(i).getCarnumber(),
                                                    CarData.getallperson_list().get(j).getPname(),
                                                    jsonObject.optInt("Balance"));
                                            Log.i("Acc_Main", "balance.toString()" + ":" + balance.toString());
                                            list.add(balance);
                                            count++;
                                            if (count == 4) {
                                                Collections.sort(list, new Comparator<Acc_Bean>() {
                                                    @Override
                                                    public int compare(Acc_Bean o1, Acc_Bean o2) {
                                                        return o1.getId() - o2.getId();
                                                    }
                                                });
                                                adapter = new Acc_Adapter();
                                                ob9_listview.setAdapter(adapter);
                                            }
                                        }
                                    }
                                }
                            }
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
    }

    private void ChargeBlance(final List<Integer> caridlist, final int money) {
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/set_car_account_recharge";
        count = 0;
        for (int i = 0; i < caridlist.size(); i++) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CarId", caridlist.get(i));
                jsonObject.put("Money", money);
                jsonObject.put("UserName", Util.getUserName(context));

                final int finalI = i;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            JiluBean balance = new JiluBean(
                                    list.get(caridlist.get(finalI) - 1).getChepai(),
                                    money,
                                    list.get(caridlist.get(finalI) - 1).getBalance()
                                            + money,
                                    Util.getUserName(context),
                                    new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date()));
                            Log.i("Go_Fight_Now 提醒您", "balance.toString()" + ":" + balance.toString());
                            new Acc_Dao(context).add(balance);
                            Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
                            count++;
                            if (count == caridlist.size()) {
                                for (int i = 0; i < 4; i++) {
                                    checkbox.set(i, false);
                                }
                                QueryBalance();
                            }
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

    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        piliangchongzhi = (Button) view.findViewById(R.id.piliangchongzhi);
        chongzhijilu = (Button) view.findViewById(R.id.chongzhijilu);
        top_title = (LinearLayout) view.findViewById(R.id.top_title);
        ob9_listview = (ListView) view.findViewById(R.id.ob9_listview);

        piliangchongzhi.setOnClickListener(this);
        chongzhijilu.setOnClickListener(this);
        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to.Caidan();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.piliangchongzhi:
                final List<Integer> caridlist = new ArrayList<>();
                for (int i = 0; i < checkbox.size(); i++) {
                    if (checkbox.get(i)) {
                        caridlist.add(list.get(i).getId());
                    }
                }
                if (caridlist.size() > 0 && caridlist != null) {
                    final Dialog chongqian = new Dialog(context);
                    chongqian.setContentView(R.layout.ob9_chongqian);
                    chongqian.setTitle("车辆账户充值");
                    chongqian.show();
                    Button ob9_chong_quxiao = (Button) chongqian.getWindow().findViewById(R.id.ob9_chong_quxiao);
                    Button ob9_chong_chongzhi = (Button) chongqian.getWindow().findViewById(R.id.ob9_chong_chongzhi);
                    final EditText ob9_tv_jine = (EditText) chongqian.getWindow().findViewById(R.id.ob9_tv_jine);
                    TextView ob9_chong_chepai = (TextView) chongqian.getWindow().findViewById(R.id.ob9_chong_chepai);

                    ob9_tv_jine.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String string = s.toString();
                            if (!string.equals("")) {
                                if (Integer.parseInt(string) < 1 || Integer.parseInt(string) > 999) {
                                    s.clear();
                                }
                            }
                        }
                    });
                    StringBuilder stringBuilder = new StringBuilder("");
                    for (int i = 0; i < caridlist.size(); i++) {
                        stringBuilder.append(list.get(caridlist.get(i) - 1).getChepai() + " ");
                    }
                    ob9_chong_chepai.setText(stringBuilder);
                    ob9_chong_quxiao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chongqian.dismiss();
                        }
                    });
                    ob9_chong_chongzhi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChargeBlance(caridlist, Integer.parseInt(ob9_tv_jine.getText().toString().trim()));
                            chongqian.dismiss();
                        }
                    });
                } else {
                    Toast.makeText(context, "请选择要充值的小车", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.chongzhijilu:
                to.Jilu();
                break;
        }
    }


    class Acc_Adapter extends BaseAdapter {

        public Acc_Adapter() {
            for (int i = 0; i < 4; i++) {
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
            convertView = View.inflate(context, R.layout.ob9_list_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob9_lv_chebiao.setImageResource(Acc_Util.getImage(list.get(position).getChebiao()));
            viewHolder.ob9_lv_id.setText(list.get(position).getId() + "");
            viewHolder.ob9_tv_chepai.setText(list.get(position).getChepai() + "");
            viewHolder.ob9_tv_chezhu.setText("车主：" + list.get(position).getChezhu() + "");
            viewHolder.ob9_tv_balance.setText("余额：" + list.get(position).getBalance() + "元");
            viewHolder.ob9_tv_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkbox.set(position, isChecked);
                }
            });
            viewHolder.ob9_btn_chongzhi.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    final Dialog chongqian = new Dialog(context);
                    chongqian.setContentView(R.layout.ob9_chongqian);
                    chongqian.setTitle("车辆账户充值");
                    chongqian.show();
                    Button ob9_chong_quxiao = (Button) chongqian.getWindow().findViewById(R.id.ob9_chong_quxiao);
                    Button ob9_chong_chongzhi = (Button) chongqian.getWindow().findViewById(R.id.ob9_chong_chongzhi);
                    final EditText ob9_tv_jine = (EditText) chongqian.getWindow().findViewById(R.id.ob9_tv_jine);
                    TextView ob9_chong_chepai = (TextView) chongqian.getWindow().findViewById(R.id.ob9_chong_chepai);

                    ob9_tv_jine.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String string = s.toString();
                            if (!string.equals("")) {
                                if (Integer.parseInt(string) < 1 || Integer.parseInt(string) > 999) {
                                    s.clear();
                                }
                            }
                        }
                    });
                    ob9_chong_chepai.setText(list.get(position).getChepai());
                    ob9_chong_quxiao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chongqian.dismiss();
                        }
                    });
                    ob9_chong_chongzhi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Integer> carid = new ArrayList<>();
                            carid.add(list.get(position).getId());
                            ChargeBlance(carid, Integer.parseInt(ob9_tv_jine.getText().toString().trim()));
                            chongqian.dismiss();
                        }
                    });
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ob9_lv_id;
            public ImageView ob9_lv_chebiao;
            public TextView ob9_tv_chepai;
            public TextView ob9_tv_chezhu;
            public TextView ob9_tv_balance;
            public CheckBox ob9_tv_check;
            public Button ob9_btn_chongzhi;
            public LinearLayout ob9_tv_background;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob9_lv_id = (TextView) rootView.findViewById(R.id.ob9_lv_id);
                this.ob9_lv_chebiao = (ImageView) rootView.findViewById(R.id.ob9_lv_chebiao);
                this.ob9_tv_chepai = (TextView) rootView.findViewById(R.id.ob9_tv_chepai);
                this.ob9_tv_chezhu = (TextView) rootView.findViewById(R.id.ob9_tv_chezhu);
                this.ob9_tv_balance = (TextView) rootView.findViewById(R.id.ob9_tv_balance);
                this.ob9_tv_check = (CheckBox) rootView.findViewById(R.id.ob9_tv_check);
                this.ob9_btn_chongzhi = (Button) rootView.findViewById(R.id.ob9_btn_chongzhi);
                this.ob9_tv_background = (LinearLayout) rootView.findViewById(R.id.ob9_tv_background);
            }

        }
    }
}
