package com.mad.trafficclient.zy_java.manage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob5.IndexBean;
import com.mad.trafficclient.ws_java.ob5.SenseDao;
import com.mad.trafficclient.zy_java.fragment.LineCharMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.util.Log.i;


public class LineShowFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int postion_data;
    private LineChart lineChart;
    private Timer timer;
    private List<IndexBean> list;
    private SenseDao senseDao;
    private ArrayList<String> x;
    private ArrayList<Integer> y;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            chartManage.showLineChart(x, y);
            super.handleMessage(msg);
        }
    };
    private ChartManage chartManage;

    public LineShowFrag() {
        // Required empty public constructor
    }


    public static Fragment getIntance(int position) {
        LineShowFrag lineCharMain = new LineShowFrag();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        lineCharMain.setArguments(bundle);
        return lineCharMain;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            postion_data = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.linechart, container, false);

        initView(view);
        return view;
    }


    private void initView(View view) {
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
        chartManage = new ChartManage(lineChart);
        list = new ArrayList<>();
        senseDao = new SenseDao(getContext());
        x = new ArrayList<>();
        y = new ArrayList<>();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showData();

            }
        }, 0, 3000);

    }

    private  synchronized  void showData() {
      x=new ArrayList<>();
      y=new ArrayList<>();
        list = senseDao.queryForAll();
        for (int i = 0; i < list.size(); i++) {
            i("LineShowFrag", "" + list.get(i).toString());
        }

        if (postion_data == 0) {
            for (int i = 0; i < list.size(); i++) {
                x.add(list.get(i).getTime());
                y.add(list.get(i).getTemperature());
            }
        } else if (postion_data == 1) {
            for (int i = 0; i < list.size(); i++) {
                x.add(list.get(i).getTime());
                y.add(list.get(i).getHumidity());

            }
        } else if (postion_data == 2) {
            for (int i = 0; i < list.size(); i++) {
                x.add(list.get(i).getTime());
                y.add(list.get(i).getLightIntensity());

            }
        } else if (postion_data == 3) {
            for (int i = 0; i < list.size(); i++) {
                x.add(list.get(i).getTime());
                y.add(list.get(i).getCo2());

            }
        } else if (postion_data == 4) {
            for (int i = 0; i < list.size(); i++) {
                x.add(list.get(i).getTime());
                y.add(list.get(i).get_$Pm25316());

            }
        } else if (postion_data == 5) {
            for (int i = 0; i < list.size(); i++) {
                x.add(list.get(i).getTime());
                y.add(list.get(i).getStatus());

            }
        }
        handler.sendEmptyMessage(0);

    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
