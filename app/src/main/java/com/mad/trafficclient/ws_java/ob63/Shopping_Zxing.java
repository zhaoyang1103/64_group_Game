package com.mad.trafficclient.ws_java.ob63;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.util.Zxing_Util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Go_Fight_Now on 2019/5/11 19:22
 */
public class Shopping_Zxing extends Fragment {
    private ImageView imageView_Sliding;
    private TextView ob63_fk_info;
    private ImageView ob63_fk_zxing;
    private static Shopping_Zxing fragment;
    private Context context;
    private Timer timer;
    private boolean flag;
    private String content;
    private Bitmap bitmap;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ob63_fk_zxing.setImageBitmap(bitmap);
            ob63_fk_info.setText(content);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob63_zxing, container, false);
        initView(view);
        context = getContext();
        flag = true;
        ob63_fk_info.setVisibility(View.INVISIBLE);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (flag){
                    content = "付款项目：" + fragment.getArguments().getString("name") + "   付款金额 ：" + fragment.getArguments().getInt("balance") +  "元";
                    flag = false;
                } else {
                    content = "付款项目：" + fragment.getArguments().getString("name") + "   付款金额： " + fragment.getArguments().getInt("balance") +  "元";
                    flag = true;
                }
                bitmap = Zxing_Util.showQcode(content, 400, 400);
                handler.sendEmptyMessage(0);
            }
        },0,5000);
        return view;
    }

    public static Shopping_Zxing newInstance(Bundle args) {
        fragment = new Shopping_Zxing();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        ob63_fk_info = (TextView) view.findViewById(R.id.ob63_fk_info);
        ob63_fk_zxing = (ImageView) view.findViewById(R.id.ob63_fk_zxing);

        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        ob63_fk_zxing.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "长按了图片", Toast.LENGTH_SHORT).show();
                ob63_fk_info.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }
}
