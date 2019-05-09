/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;


public class Car_setDown_fragement extends Fragment implements View.OnClickListener {

    private ImageView imageView_Sliding;
    private TextView tv_title;
    private TextView tx_username;
    private LinearLayout top_title;
    private ViewPager car_viewpager;
    private TextView my_balance;
    private TextView my_talance;
    private TextView my_record;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.car_setdown_fragment, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tx_username = (TextView) view.findViewById(R.id.tx_username);
        top_title = (LinearLayout) view.findViewById(R.id.top_title);
        car_viewpager = (ViewPager) view.findViewById(R.id.car_viewpager);
        my_balance = (TextView) view.findViewById(R.id.my_balance);
        my_talance = (TextView) view.findViewById(R.id.my_talance);
        my_record = (TextView) view.findViewById(R.id.my_record);
        my_balance.setOnClickListener(this);
        my_talance.setOnClickListener(this);
        my_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_balance:
                break;
        }
    }
}
