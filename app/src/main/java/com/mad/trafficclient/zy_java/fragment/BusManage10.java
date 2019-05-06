/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;

import org.json.JSONException;
import org.json.JSONObject;


public class BusManage10 extends Fragment implements View.OnClickListener {

    private ExpandableListView expand_list;
    private TextView tx_allperson;
    private Button bt_showxiangqing;
    private Context context;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.busmanage10, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        expand_list = (ExpandableListView) view.findViewById(R.id.expand_list);
        tx_allperson = (TextView) view.findViewById(R.id.tx_allperson);
        bt_showxiangqing = (Button) view.findViewById(R.id.bt_showxiangqing);
        bt_showxiangqing.setOnClickListener(this);
        context = getContext();
        requestQueue = Volley.newRequestQueue(context);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_showxiangqing:

                break;
        }
    }

    private void getBusBean() {
        JSONObject object=new JSONObject();
        try {
            object.put("UserName","user21");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, "", ))

    }


}
