/**
 *
 */
package com.mad.trafficclient.zy_java.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.ws_java.ob23.BalanceUtil;
import com.mad.trafficclient.zy_java.bean.Balacne_Bean_1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Car_balacne_1 extends Fragment {

    private GridView gv_balance;
    private List<Balacne_Bean_1> list;
    private Context context;
    private String getBalance_api = "";
    private RequestQueue requestQueue;
    private Balance_1Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.mybalance_1, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        gv_balance = (GridView) view.findViewById(R.id.gv_balance);
        context = getContext();
        list = new ArrayList<>();
        getBalance_api = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_car_account_balance";
        requestQueue = Volley.newRequestQueue(context);
        for (int i = 0; i < 4; i++) {
            list.add(new Balacne_Bean_1(0, "", 0));
        }
        getBalance();
        adapter = new Balance_1Adapter();
        gv_balance.setAdapter(adapter);

    }

    private void getBalance() {
        for (int i = 0; i < 4; i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("UserName", Util.getUserName(context));
                jsonObject.put("CarId", i + 1);
                final int finalI = i;
                requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, getBalance_api, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
//                            list.add(new Balacne_Bean_1(finalI + 1, "", jsonObject.getInt("Balance")));
                            list.set(finalI, new Balacne_Bean_1(finalI + 1, "", jsonObject.getInt("Balance")));
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
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


    }

    class Balance_1Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_my_balance, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.tx_carid_show.setText(list.get(position).getCarid() + "号小车");
            viewHolder.item_car_index.setText(list.get(position).getIndex() + "");
            if (list.get(position).getIndex() > BalanceUtil.getBalanceYuzhi(context)) {
                viewHolder.item_car_state.setText("警告");
                viewHolder.item_re_back.setBackgroundColor(Color.RED);
            } else {
                viewHolder.item_car_state.setText("正常");
                viewHolder.item_re_back.setBackgroundColor(Color.GREEN);
            }
            return convertView;
        }

        public
        class ViewHolder {
            public View rootView;
            public TextView tx_carid_show;
            public TextView item_car_state;
            public TextView item_car_index;
            public RelativeLayout item_re_back;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tx_carid_show = (TextView) rootView.findViewById(R.id.tx_carid_show);
                this.item_car_state = (TextView) rootView.findViewById(R.id.item_car_state);
                this.item_car_index = (TextView) rootView.findViewById(R.id.item_car_index);
                this.item_re_back = (RelativeLayout) rootView.findViewById(R.id.item_re_back);
            }

        }
    }


    private void showDialog(int carid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("我的充值");
        View view = View.inflate(context, R.layout.car_recharge_1, null);
        builder.setView(view);
        final ViewHolder1 viewHolder1 = new ViewHolder1(view);
        viewHolder1.queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = Integer.parseInt(viewHolder1.dialog_ed_money.getText().toString());

            }
        });
        builder.show();


    }

    public static
    class ViewHolder1 {
        public View rootView;
        public EditText dialog_ed_money;
        public Button queding;
        public Button quxiao;

        public ViewHolder1(View rootView) {
            this.rootView = rootView;
            this.dialog_ed_money = (EditText) rootView.findViewById(R.id.dialog_ed_money);
            this.queding = (Button) rootView.findViewById(R.id.queding);
            this.quxiao = (Button) rootView.findViewById(R.id.quxiao);
        }

    }
}
