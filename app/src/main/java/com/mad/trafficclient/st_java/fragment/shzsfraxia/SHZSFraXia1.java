package com.mad.trafficclient.st_java.fragment.shzsfraxia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.manage.ManageTool;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SHZSFraXia1 extends Fragment {
    private BarChart barChart;
    private TextView title;
    private Context context;
    private SharedPreferences shzsFra_sp;
    private Timer timer;
    private ArrayList<Float> floats;
    private ArrayList<String> strings;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    ManageTool manageTool = new ManageTool(barChart);
                    manageTool.showBar(strings, floats);
                    if (floats.size() == 0) {

                    } else {
                        float max = floats.get(0);
                        float min = floats.get(0);
                        for (int i = 0; i < floats.size(); i++) {
                            Float aFloat = floats.get(i);
                            if (aFloat > max) {
                                max = aFloat;
                            }
                            if (aFloat < min) {
                                min = aFloat;
                            }
                        }
                        title.setText("过去1分钟空气质量最差值:" + min);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_shzs_xia_1, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        context = getContext();
        shzsFra_sp = context.getSharedPreferences("SHZSFra_sp", Context.MODE_PRIVATE);
        strings = new ArrayList<>();
        floats = new ArrayList<>();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                strings.clear();
                floats.clear();
                String get_$Pm25126 = shzsFra_sp.getString("get_$Pm25126", "");
                String[] split = get_$Pm25126.split("-");
                int length = split.length;
                if (length >= 20) {
                    for (int i = 0; i < 20; i++) {
                        strings.add(3 * i + "");
                        floats.add(Float.valueOf(split[length - 20 + i]));
                    }
                } else if (length > 1) {
                    for (int i = 0; i < length; i++) {
                        strings.add(3 * i + "");
                        floats.add(Float.valueOf(split[i]));
                    }
                }
                handler.sendEmptyMessage(0);
            }
        }, 0, 3000);
    }

    private void initView(View view) {
        barChart = (BarChart) view.findViewById(R.id.barChart);
        title = (TextView) view.findViewById(R.id.title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }
}
