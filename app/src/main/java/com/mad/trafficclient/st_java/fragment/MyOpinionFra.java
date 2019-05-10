package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.adapter.MyOpinionAda;
import com.mad.trafficclient.st_java.adapter.MyOpinionAdaUPdate;
import com.mad.trafficclient.st_java.bean.GetAllSuggestion;
import com.mad.trafficclient.st_java.bean.MyOpinion;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyOpinionFra extends Fragment {
    private ListView listView;
    private Context context;
    private ArrayList<MyOpinion> myOpinions;
    private MyOpinionAda myOpinionAda;
    private ImageView iv_back;
    private FragmentManager supportFragmentManager;
    private UrlBean urlBean;
    private String userName;
    private RequestQueue requestQueue;
    private static final String TAG = "MyOpinionFra";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_my_opinion, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFragmentManager = getActivity().getSupportFragmentManager();
                supportFragmentManager.popBackStack();
            }
        });
    }

    private void initData() {
        context = getContext();
        urlBean = Util.loadSetting(context);
        userName = Util.getUserName(context);
        requestQueue = Volley.newRequestQueue(context);
        myOpinions = new ArrayList<>();
        myOpinions.add(new MyOpinion("测试", "无", "13753973040"));
        myOpinionAda = new MyOpinionAda(myOpinions, context);
//        listView.setAdapter(myOpinionAda);
        //http://localhost:8080/api/v2/get_all_suggestion
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_all_suggestion", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i(TAG, "onResponse: " + jsonObject);
                if (jsonObject.optString("RESULT").equals("S")) {
                    Gson gson = new Gson();
                    GetAllSuggestion getAllSuggestion = gson.fromJson(jsonObject.toString(), GetAllSuggestion.class);
                    MyOpinionAdaUPdate myOpinionAdaUPdate = new MyOpinionAdaUPdate(getAllSuggestion, context);
                    listView.setAdapter(myOpinionAdaUPdate);
                } else {
                    Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
    }
}
