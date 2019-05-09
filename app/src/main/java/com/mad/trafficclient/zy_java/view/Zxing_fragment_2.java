/**
 *
 */
package com.mad.trafficclient.zy_java.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.util.Zxing_Util;

import java.util.Timer;
import java.util.TimerTask;


public class Zxing_fragment_2 extends Fragment implements View.OnClickListener {

    private ImageView image_zxing_big;
    private TextView tx_zxing_show;
    private ImageView image_zxing_small;
    private int monry;
    private int carid;
    private int pelife;
    private Bitmap bitmap;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            bitmap = Zxing_Util.showQcode(content, 200, 200);
            image_zxing_small.setImageBitmap(bitmap);
            image_zxing_big.setImageBitmap(bitmap);
        }
    };
    private String content;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.zxing_fragment_2, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            carid = getArguments().getInt("carid");
            monry = getArguments().getInt("money");
            pelife = getArguments().getInt("pelife");
        }
        super.onCreate(savedInstanceState);
    }

    public static Fragment getInstance(int carid, int money, int pelife) {
        Bundle bundle = new Bundle();
        bundle.putInt("carid", carid);
        bundle.putInt("money", carid);
        bundle.putInt("pelife", carid);
        Zxing_fragment_2 zxing_fragment_2 = new Zxing_fragment_2();
        zxing_fragment_2.setArguments(bundle);
        return zxing_fragment_2;

    }

    private void initView(View view) {
        TextView textView = new TextView(getContext());

        image_zxing_big = (ImageView) view.findViewById(R.id.image_zxing_big);
        tx_zxing_show = (TextView) view.findViewById(R.id.tx_zxing_show);
        image_zxing_small = (ImageView) view.findViewById(R.id.image_zxing_small);
        image_zxing_small.setOnClickListener(this);
        image_zxing_big.setOnClickListener(this);
        content = "车牌编号" + carid + ",付款金额" + monry;
        bitmap = Zxing_Util.showQcode(content, 200, 200);
        image_zxing_small.setImageBitmap(bitmap);
        image_zxing_big.setImageBitmap(bitmap);
        image_zxing_small.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tx_zxing_show.setText(content);
                return true;
            }
        });
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);

            }
        }, 0, pelife * 1000);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_zxing_small:
                image_zxing_big.setVisibility(View.VISIBLE);
                image_zxing_small.setVisibility(View.GONE);
                break;
            case R.id.image_zxing_big:
                image_zxing_big.setVisibility(View.GONE);
                image_zxing_small.setVisibility(View.VISIBLE);
                break;


        }
    }
}
