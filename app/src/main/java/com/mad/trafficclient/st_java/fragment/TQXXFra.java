package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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


public class TQXXFra extends Fragment {
    private ImageView image;
    private TextView WData;
    private TextView city;
    private TextView WCurrent;
    private TextView tv_1;
    private GridView gridView;


    private Context context;
    private UrlBean urlBean;
    private String userName;
    private RequestQueue requestQueue;
    private ArrayList<Bean> beans;
    private Ada ada;
    private ImageView imageView_Sliding;
    private TextView tv_title;
    private ImageView refresh;
    private LinearLayout top_title;
    private int measuredHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_tqxx, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshData();
            }
        });
//        gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int h = gridView.getHeight();
//                System.out.println(h);
//            }
//        });
    }

    private void initData() {
        context = getContext();
        urlBean = Util.loadSetting(context);
        userName = Util.getUserName(context);
        requestQueue = Volley.newRequestQueue(context);

        beans = new ArrayList<>();
        beans.add(new Bean());
        beans.add(new Bean());
        beans.add(new Bean());
        beans.add(new Bean());
        beans.add(new Bean());
        ada = new Ada();
        gridView.setAdapter(ada);
        refreshData();
    }

    private void refreshData() {
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
                    Get_weather get_weather = gson.fromJson(jsonObject.toString(), Get_weather.class);
                    WCurrent.setText(get_weather.getWCurrent() + "度");

                    List<Get_weather.ROWSDETAILBean> rows_detail = get_weather.getROWS_DETAIL();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("d日");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年MM月dd日");
                    Date date = new Date();
                    WData.setText(simpleDateFormat2.format(date) + " 周" + Util.getWeek(date));

                    String t2 = rows_detail.get(1).getWeather();
                    if (t2.equals("小雨")) {
                        image.setBackgroundResource(R.drawable.icon101);
                    } else if (t2.equals("中雨")) {
                        image.setBackgroundResource(R.drawable.icon102);
                    } else if (t2.equals("大雨")) {
                        image.setBackgroundResource(R.drawable.icon103);
                    } else if (t2.equals("雷阵雨")) {
                        image.setBackgroundResource(R.drawable.icon104);
                    } else if (t2.equals("多云")) {
                        image.setBackgroundResource(R.drawable.icon105);
                    } else {
                        image.setBackgroundResource(R.drawable.icon_1);
                    }

                    beans.clear();

                    for (int i = 1; i < 6; i++) {
                        Get_weather.ROWSDETAILBean rowsdetailBean = rows_detail.get(i);
                        String wData = rowsdetailBean.getWData();
                        Date parse = null;
                        try {
                            parse = simpleDateFormat.parse(wData);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Bean bean = new Bean();
                        if (i == 1) {
                            bean.setT1(simpleDateFormat1.format(parse) + "(今天)");
                        } else if (i == 2) {
                            bean.setT1(simpleDateFormat1.format(parse) + "(明天)");
                        } else if (i == 3) {
                            bean.setT1(simpleDateFormat1.format(parse) + "(后天)");
                        } else {
                            bean.setT1(simpleDateFormat1.format(parse) + "(周" + Util.getWeek(parse) + ")");
                        }
                        bean.setT2(rowsdetailBean.getWeather());
                        String[] split1 = rowsdetailBean.getTemperature().split("~");
                        if (split1.length == 2) {
                            bean.setT3(split1[0] + "/" + split1[1] + "℃");
                        }
                        beans.add(bean);
                    }
                    ada.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }

    private void initView(View view) {
        image = (ImageView) view.findViewById(R.id.image);
        WData = (TextView) view.findViewById(R.id.WData);
        city = (TextView) view.findViewById(R.id.city);
        WCurrent = (TextView) view.findViewById(R.id.WCurrent);
        tv_1 = (TextView) view.findViewById(R.id.tv_1);
        gridView = (GridView) view.findViewById(R.id.gridView);
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        refresh = (ImageView) view.findViewById(R.id.refresh);
        top_title = (LinearLayout) view.findViewById(R.id.top_title);
    }

    public class Bean {
        private String t1 = "";

        private int icon = 0;
        private String t2 = "";
        private String t3 = "";

        public Bean() {
        }

        public Bean(String t1, int icon, String t2, String t3) {
            this.t1 = t1;
            this.icon = icon;
            this.t2 = t2;
            this.t3 = t3;
        }

        public String getT1() {
            return t1;
        }

        public void setT1(String t1) {
            this.t1 = t1;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getT2() {
            return t2;
        }

        public void setT2(String t2) {
            this.t2 = t2;
        }

        public String getT3() {
            return t3;
        }

        public void setT3(String t3) {
            this.t3 = t3;
        }
    }


    public class Ada extends BaseAdapter {
        @Override
        public int getCount() {
            return beans != null ? beans.size() : 0;
        }

        @Override
        public Bean getItem(int i) {
            return beans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Bean item = getItem(i);
             view = View.inflate(context, R.layout.ada_tqxx, null);
//            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ada_tqxx,viewGroup,false);
            view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, gridView.getHeight()));
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.tv_1.setText(item.getT1());
            String t2 = item.getT2();
            if (t2.equals("小雨")) {
                viewHolder.background.setBackgroundColor(Color.parseColor("#2196F3"));
                viewHolder.icon.setBackgroundResource(R.drawable.icon101);
            } else if (t2.equals("中雨")) {
                viewHolder.background.setBackgroundColor(Color.parseColor("#1895F7"));
                viewHolder.icon.setBackgroundResource(R.drawable.icon102);
            } else if (t2.equals("大雨")) {
                viewHolder.background.setBackgroundColor(Color.parseColor("#5FA1D6"));
                viewHolder.icon.setBackgroundResource(R.drawable.icon103);
            } else if (t2.equals("雷阵雨")) {
                viewHolder.background.setBackgroundColor(Color.parseColor("#2990E4"));
                viewHolder.icon.setBackgroundResource(R.drawable.icon104);
            } else if (t2.equals("多云")) {
                viewHolder.background.setBackgroundColor(Color.parseColor("#97B4D1"));
                viewHolder.icon.setBackgroundResource(R.drawable.icon105);
            } else {
                viewHolder.background.setBackgroundColor(Color.parseColor("#77C1F9"));
                viewHolder.icon.setBackgroundResource(R.drawable.icon_1);
            }
            viewHolder.tv_2.setText(t2);
            viewHolder.tv_3.setText(item.getT3());
            return view;
        }


        public class ViewHolder {
            public View rootView;
            public TextView tv_1;
            public ImageView icon;
            public TextView tv_2;
            public TextView tv_3;
            public LinearLayout background;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tv_1 = (TextView) rootView.findViewById(R.id.tv_1);
                this.icon = (ImageView) rootView.findViewById(R.id.icon);
                this.tv_2 = (TextView) rootView.findViewById(R.id.tv_2);
                this.tv_3 = (TextView) rootView.findViewById(R.id.tv_3);
                this.background = (LinearLayout) rootView.findViewById(R.id.background);
            }

        }
    }
}
