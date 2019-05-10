package com.mad.trafficclient.ws_java.ob60;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mad.trafficclient.R;

/**
 * Created by Go_Fight_Now on 2019/5/9 21:30
 */
public class Subscribe_Main extends Fragment {
    private ImageView ob60_img_add;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob60_main, container, false);
        initView(view);
        context = getContext();
        return view;
    }

    private void initView(View view) {
        ob60_img_add = (ImageView) view.findViewById(R.id.ob60_img_add);

        ob60_img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "框内数据", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
