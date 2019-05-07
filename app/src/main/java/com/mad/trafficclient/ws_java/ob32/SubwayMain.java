package com.mad.trafficclient.ws_java.ob32;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.MainActivity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/7 18:49
 */
public class SubwayMain extends Fragment implements View.OnClickListener {
    private ImageView imageView_Sliding;
    private Button ob32_btn_ditieguihua;
    private ListView ob32_listview;
    private SlidingPaneLayout slidepanel;

    private List<SubwayBean.ROWSDETAILBean> list;
    private Context context;
    private SubwayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob32_main, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        getData();
        return view;
    }

    public void getData(){
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_metro";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", Util.getUserName(context));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")){
                        list = new Gson().fromJson(jsonObject.toString(), SubwayBean.class).getROWS_DETAIL();
                        adapter = new SubwayAdapter();
                        ob32_listview.setAdapter(adapter);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, "获取地铁信息失败", Toast.LENGTH_SHORT).show();
                }
            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity) context;
        slidepanel = (SlidingPaneLayout) mainActivity.findViewById(R.id.slidingPL);
    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        ob32_btn_ditieguihua = (Button) view.findViewById(R.id.ob32_btn_ditieguihua);
        ob32_listview = (ListView) view.findViewById(R.id.ob32_listview);

        ob32_btn_ditieguihua.setOnClickListener(this);
        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob32_btn_ditieguihua:
                Bundle args = new Bundle();
                args.putString("title","地铁规划");
                args.putString("map","metro/metro_001.jpg");
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent,SubwayDetails.newInstance(args)).commit();
                break;
        }
    }


    class SubwayAdapter extends BaseAdapter {

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
            convertView = View.inflate(context, R.layout.ob32_list_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob32_tv_title.setText(list.get(position).getMetro_code() + "("+list.get(position).getStart_place()+" - "+list.get(position).getEnd_place()+")线路图");
            viewHolder.ob32_imgbtn_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putString("title",list.get(position).getStart_place()+" - "+list.get(position).getEnd_place());
                    args.putString("map",list.get(position).getImg());
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent,SubwayDetails.newInstance(args)).commit();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ob32_tv_title;
            public ImageView ob32_imgbtn_details;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob32_tv_title = (TextView) rootView.findViewById(R.id.ob32_tv_title);
                this.ob32_imgbtn_details = (ImageView) rootView.findViewById(R.id.ob32_imgbtn_details);
            }

        }
    }
}
