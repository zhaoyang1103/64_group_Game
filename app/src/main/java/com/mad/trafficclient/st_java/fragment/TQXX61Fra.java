package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.Get_weather;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TQXX61Fra extends Fragment {
    private TextView WCurrent;
    private TextView weather;
    private ImageView refresh;
    private TextView tv_time;
    private GridView gridView;
    private LineChart lineChart;
    private Context context;
    private UrlBean urlBean;
    private RequestQueue requestQueue;
    private String userName;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat simpleDateFormat1;
    private SimpleDateFormat simpleDateFormat2;
    private Get_weather get_weather;
    private Ada ada;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_tqxx_61, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refrashData();
            }
        });
    }

    private void initData() {
        context = getContext();
        urlBean = Util.loadSetting(context);
        userName = Util.getUserName(context);
        requestQueue = Volley.newRequestQueue(context);
        simpleDateFormat = new SimpleDateFormat("hh:mm刷新");
        simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat2 = new SimpleDateFormat("MM/dd");

        lineChart.setTouchEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getXAxis().setEnabled(false);
        lineChart.setGridBackgroundColor(Color.parseColor("#00000000"));
        lineChart.setDescription("");
        lineChart.getLegend().setEnabled(false);

        refrashData();
    }

    private void refrashData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/get_weather", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (jsonObject.optString("RESULT").equals("S")) {
                    Gson gson = new Gson();
                    get_weather = gson.fromJson(jsonObject.toString(), Get_weather.class);
                    List<Get_weather.ROWSDETAILBean> rows_detail = get_weather.getROWS_DETAIL();
                    WCurrent.setText(get_weather.getWCurrent() + "°");
                    weather.setText(rows_detail.get(1).getWeather());
                    tv_time.setText(simpleDateFormat.format(new Date()));
                    ada = new Ada();
                    gridView.setAdapter(ada);
                    ada.notifyDataSetChanged();

                    ArrayList<String> strings = new ArrayList<>();
                    ArrayList<Float[]> floats = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        Get_weather.ROWSDETAILBean rowsdetailBean = rows_detail.get(i);
                        String[] split = rowsdetailBean.getTemperature().split("~");
                        if (split.length == 2) {
                            strings.add("");
                            Float[] floats1 = new Float[2];
                            floats1[0] = Float.parseFloat(split[0]);
                            floats1[1] = Float.parseFloat(split[1]);
                            floats.add(floats1);
                        }
                    }

                    int[] colors = {Color.GREEN, Color.BLUE};
                    ArrayList<LineDataSet> dataSets = new ArrayList<>();
                    for (int i = 0; i < 2; i++) {
                        ArrayList<Entry> yVals = new ArrayList<>();
                        for (int j = 0; j < floats.size(); j++) {
                            yVals.add(new Entry(floats.get(j)[i], j));
                        }
                        LineDataSet e = new LineDataSet(yVals, "");
                        e.setLineWidth(5);
                        e.setColor(colors[i]);
                        e.setCircleColor(colors[i]);
                        e.setCircleColorHole(colors[i]);
                        e.setDrawValues(true);
                        dataSets.add(e);
                    }
                    dataSets.get(0).setCircleColor(colors[1]);
                    dataSets.get(0).setCircleColorHole(colors[1]);
                    LineData data = new LineData(strings, dataSets);
                    lineChart.setData(data);
                    lineChart.invalidate();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    private void initView(View view) {
        WCurrent = (TextView) view.findViewById(R.id.WCurrent);
        weather = (TextView) view.findViewById(R.id.weather);
        refresh = (ImageView) view.findViewById(R.id.refresh);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        gridView = (GridView) view.findViewById(R.id.gridView);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
    }

    public class Ada extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(context, R.layout.ada_tqxx_61, null);
            view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, gridView.getHeight()));
            ViewHolder viewHolder = new ViewHolder(view);
            List<Get_weather.ROWSDETAILBean> rows_detail = get_weather.getROWS_DETAIL();
            Get_weather.ROWSDETAILBean rowsdetailBean = rows_detail.get(i);
            Date parse = null;
            try {
                parse = simpleDateFormat1.parse(rowsdetailBean.getWData());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (i == 0) {
                viewHolder.tv_week.setText("昨天");
            } else if (i == 1) {
                viewHolder.tv_week.setText("今天");
            } else if (i == 2) {
                viewHolder.tv_week.setText("明天");
            } else {
                viewHolder.tv_week.setText("周" + Util.getWeek(parse));
            }
            viewHolder.tv_date.setText(simpleDateFormat2.format(parse));

            String weather = rowsdetailBean.getWeather();
            switch (weather) {
                case "多云":
                    viewHolder.image.setBackgroundResource(R.drawable.icon101);
                    break;
                case "小雨":
                    viewHolder.image.setBackgroundResource(R.drawable.icon102);
                    break;
                case "中雨":
                    viewHolder.image.setBackgroundResource(R.drawable.icon103);
                    break;
                case "大雨":
                    viewHolder.image.setBackgroundResource(R.drawable.icon104);
                    break;
                case "雷阵雨":
                    viewHolder.image.setBackgroundResource(R.drawable.icon105);
                    break;
                default:
                    viewHolder.image.setBackgroundResource(R.drawable.icon_1);
                    break;
            }

            return view;
        }

        public class ViewHolder {
            public View rootView;
            public TextView tv_week;
            public TextView tv_date;
            public ImageView image;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tv_week = (TextView) rootView.findViewById(R.id.tv_week);
                this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
                this.image = (ImageView) rootView.findViewById(R.id.image);
            }

        }
    }
}
