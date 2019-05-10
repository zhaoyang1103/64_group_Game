package com.mad.trafficclient.st_java.fragment.sshjfra;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mad.trafficclient.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SSHJFra2 extends Fragment {
    private LineChart lineChart;
    private Context context;
    private SharedPreferences sshjFra_sp;
    private Timer timer;
    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    lineChart.invalidate();
                    break;
                case 1:
                    Float aFloat = (Float) msg.obj;
                    Notification.Builder builder = new Notification.Builder(context);
                    Notification notification = builder.setContentTitle("通知").setContentText("当前空气温度:" + aFloat).setSmallIcon(R.drawable.icon102).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)).setWhen(1000).build();
                    NotificationManager systemService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    systemService.notify(2, notification);
                    break;
                case 2:
                    break;
            }
        }
    };
    private ArrayList<Float> floats;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_sshj_1, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {

    }

    private void initData() {
        context = getContext();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                sshjFra_sp = context.getSharedPreferences("SSHJFra_sp", Context.MODE_PRIVATE);
                String get_$Pm25126 = sshjFra_sp.getString("getTemperature", "");
                String[] split = get_$Pm25126.split("-");
                ArrayList<String> strings = new ArrayList<>();
                floats = new ArrayList<>();
                if (split.length > 2) {
                    if (split.length > 5) {
                        for (int i = 1; i < 6; i++) {
                            strings.add(i * 3 + "");
                        }
                        for (int i = 5; i > 0; i--) {
                            floats.add(Float.valueOf(split[split.length - i]));
                        }
                    } else {
                        for (int i = 0; i < split.length; i++) {
                            strings.add((i + 1) * 3 + "");
                            floats.add(Float.valueOf(split[i]));
                        }
                    }
                }
                ArrayList<Entry> yVals = new ArrayList<>();
                ArrayList<Integer> colors = new ArrayList<>();
                for (int i = 0; i < floats.size(); i++) {
                    Float val = floats.get(i);
                    yVals.add(new Entry(val, i));
                    if (val > 10) {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = val;
                        handler.sendMessage(message);
                        colors.add(Color.RED);
                    } else {
                        colors.add(Color.GREEN);
                    }

                }
                LineDataSet dataSet = new LineDataSet(yVals, "");
                dataSet.setCircleColors(colors);
                LineData data = new LineData(strings, dataSet);
                lineChart.setData(data);
                handler.sendEmptyMessage(0);
            }
        }, 0, 3000);
    }

    private void initView(View view) {
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }
}
