/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.fragment.grzxfra.CZJLFra;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.zy_java.manage.Chart_1;
import com.mad.trafficclient.zy_java.view.Car_balacne_1;
import com.mad.trafficclient.zy_java.view.My_talance_2;

import java.util.ArrayList;
import java.util.List;


public class Car_setDown_fragement extends Fragment implements View.OnClickListener {

    private ImageView imageView_Sliding;
    private TextView tv_title;
    private TextView tx_username;
    private LinearLayout top_title;
    private ViewPager car_viewpager;
    private TextView my_balance;
    private TextView my_talance;
    private TextView my_record;
    private List<Fragment> list;

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
//        imageView_Sliding=getActivity().findViewById(R.id.imageView_Sliding);

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tx_username = (TextView) view.findViewById(R.id.tx_username);
        tx_username.setText("当前用户名：" + Util.getUserName(getContext()) + "");
        top_title = (LinearLayout) view.findViewById(R.id.top_title);
        car_viewpager = (ViewPager) view.findViewById(R.id.car_viewpager);
        my_balance = (TextView) view.findViewById(R.id.my_balance);
        my_talance = (TextView) view.findViewById(R.id.my_talance);
        my_record = (TextView) view.findViewById(R.id.my_record);
        my_balance.setOnClickListener(this);
        my_talance.setOnClickListener(this);
        my_record.setOnClickListener(this);
        list = new ArrayList<>();
        list.add(new Car_balacne_1());
        list.add(new My_talance_2());
        list.add(new CZJLFra());
        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlidingPaneLayout viewById = getActivity().findViewById(R.id.slidingPL);
                viewById.openPane();

            }
        });
        car_viewpager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    private void showBlck(TextView textView) {
        TextView[] tes = new TextView[]{my_balance, my_talance, my_record};
        for (int i = 0; i < tes.length; i++) {
            if (tes[i].equals(textView)) {
                tes[i].setTextColor(Color.BLACK);
                car_viewpager.setCurrentItem(i);
            } else {
                tes[i].setTextColor(Color.GRAY);
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_balance:
                showBlck(my_balance);
                break;
            case R.id.my_talance:
                showBlck(my_talance);
                break;
            case R.id.my_record:
                showBlck(my_record);
                break;
        }
    }


}
