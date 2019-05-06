/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.zy_java.bean.BusManageBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class BusManage10 extends Fragment implements View.OnClickListener {

    private ExpandableListView expand_list;
    private TextView tx_allperson;
    private Button bt_showxiangqing;
    private Context context;
    private RequestQueue requestQueue;
    private String api_getdis = "";
    private String api_getperosn = "";
    private ArrayList<String> group;
    private ArrayList<ArrayList<BusManageBean.ROWSDETAILBean>> children;
    private ArrayList<BusManageBean.ROWSDETAILBean> station_1, station_2;
    private List<Integer> listper = new ArrayList<>();
    private Timer timer;
    private ExpandBusAdapter adapter;

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
        api_getdis = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_bus_station_info";
        api_getperosn = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/v2/get_bus_capacity";
        initData();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int all = 0;
            for (int i = 0; i < listper.size(); i++) {
                all += listper.get(i);
            }
            tx_allperson.setText("当前承载能力" + all + "人");
            adapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }
    };

    private void initData() {
        group = new ArrayList<>();
        group.add("中医院站");
        group.add("联想大厦站");
        children = new ArrayList<>();
        station_1 = new ArrayList<>();
        station_2 = new ArrayList<>();
        station_1.add(new BusManageBean.ROWSDETAILBean(1, 1530, 1530 * 60 / 20000, 91));
        station_1.add(new BusManageBean.ROWSDETAILBean(2, 2140, 2140 * 60 / 20000, 63));
        station_2.add(new BusManageBean.ROWSDETAILBean(1, 3512, 3512 * 60 / 20000, 91));
        station_2.add(new BusManageBean.ROWSDETAILBean(2, 4266, 4266 * 60 / 20000, 63));
        children.add(station_1);
        children.add(station_2);
        for (int i = 0; i < 15; i++) {
            listper.add(100);
        }

        adapter = new ExpandBusAdapter();
        expand_list.setAdapter(adapter);
        expand_list.expandGroup(0);
        expand_list.expandGroup(1);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getBusBean();
                getperson();
                handler.sendEmptyMessage(0);

            }
        }, 0, 3000);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_showxiangqing:

                break;
        }
    }

    private void getBusBean() {
        for (int stationId = 0; stationId < 2; stationId++) {
            JSONObject object = new JSONObject();
            try {
                object.put("UserName", "user1");
                object.put("BusStationId", stationId + 1);

                final int finalstationId = stationId;
                requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_getdis, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Gson gson = new Gson();
                        BusManageBean busManageBean = gson.fromJson(jsonObject.toString(), BusManageBean.class);
                        //得到站台距离每一个小车的距离
                        if (finalstationId == 0) {
                            for (int i = 0; i < 2; i++) {
                                station_1.get(i).setDistance(busManageBean.getROWS_DETAIL().get(i).getDistance() / 10);
                                station_1.get(i).setTime(busManageBean.getROWS_DETAIL().get(i).getDistance() / 10 * 60 / 20000);
                                station_1.get(i).setBusId(busManageBean.getROWS_DETAIL().get(i).getBusId());
                            }


                        } else if (finalstationId == 1) {

                            for (int i = 0; i < 2; i++) {
                                station_2.get(i).setDistance(busManageBean.getROWS_DETAIL().get(i).getDistance() / 10);
                                station_2.get(i).setTime(busManageBean.getROWS_DETAIL().get(i).getDistance() / 10 * 60 / 20000);
                                station_2.get(i).setBusId(busManageBean.getROWS_DETAIL().get(i).getBusId());
                            }


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

    private void getperson() {
        for (int i = 0; i < 15; i++) {
            JSONObject jsonObject1 = new JSONObject();
            try {
                jsonObject1.put("UserName", "user1");
                jsonObject1.put("BusId", i + 1);
                final int finalI = i;
                requestQueue.add(new JsonObjectRequest(JsonObjectRequest.Method.POST, api_getperosn, jsonObject1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            int busCapacity = jsonObject.getInt("BusCapacity");
                            Log.i("人数", "onResponse: " + busCapacity);
                            listper.set(finalI, busCapacity);
                            if (finalI == 0) {
                                station_1.get(finalI).setPerson(busCapacity);
                                station_2.get(finalI).setPerson(busCapacity);
                            } else if (finalI == 1) {
                                station_1.get(finalI).setPerson(busCapacity);
                                station_2.get(finalI).setPerson(busCapacity);
                            }
                            Collections.sort(station_1, new Comparator<BusManageBean.ROWSDETAILBean>() {
                                @Override
                                public int compare(BusManageBean.ROWSDETAILBean o1, BusManageBean.ROWSDETAILBean o2) {
                                    return o1.getDistance() - o2.getDistance();
                                }
                            });
                            Collections.sort(station_2, new Comparator<BusManageBean.ROWSDETAILBean>() {
                                @Override
                                public int compare(BusManageBean.ROWSDETAILBean o1, BusManageBean.ROWSDETAILBean o2) {
                                    return o1.getDistance() - o2.getDistance();
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("人数", "onErrorResponse: 失败");
                    }
                }));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    class ExpandBusAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_busgroup, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.tx_groupbus.setText(group.get(groupPosition) + "");
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_buschildren, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            BusManageBean.ROWSDETAILBean bean = children.get(groupPosition).get(childPosition);
            viewHolder.chi_time.setText(bean.getTime() + "分钟后到达");
            viewHolder.chi_distance.setText("距离站台" + bean.getDistance() / 10 + "米");
            viewHolder.chi_id_person.setText(bean.getBusId() + "号(" + bean.getPerson() + "）人");
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        public
        class ViewHolder {
            public View rootView;
            public TextView chi_id_person;
            public TextView chi_time;
            public TextView chi_distance;
            public TextView tx_groupbus;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.chi_id_person = (TextView) rootView.findViewById(R.id.chi_id_person);
                this.chi_time = (TextView) rootView.findViewById(R.id.chi_time);
                this.chi_distance = (TextView) rootView.findViewById(R.id.chi_distance);
                this.tx_groupbus = (TextView) rootView.findViewById(R.id.tx_groupbus);
            }

        }
    }
}
