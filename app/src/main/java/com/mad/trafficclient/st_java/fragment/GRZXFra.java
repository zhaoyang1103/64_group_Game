package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.fragment.grzxfra.CZJLFra;
import com.mad.trafficclient.st_java.fragment.grzxfra.GRXXFra;
import com.mad.trafficclient.st_java.fragment.grzxfra.YZSZFra;
import com.mad.trafficclient.ws_java.ob23.BalanceYuZhiSetting;

import java.util.ArrayList;

public class GRZXFra extends Fragment {
    private TextView title_1;
    private TextView title_2;
    private TextView title_3;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;

    public static GRZXFra newInstance(Bundle args) {
        GRZXFra fragment = new GRZXFra();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_grzx, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        title_1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//                        title_2.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//                        title_3.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                        break;
                    case 1:
                        title_2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//                        title_1.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//                        title_3.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                        break;
                    case 2:
                        title_3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//                        title_1.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//                        title_3.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                        break;
                    default:
                        break;
                }
                super.onPageSelected(position);
            }


        });

        title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        title_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        title_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new GRXXFra());
        fragments.add(new CZJLFra());
        fragments.add(new BalanceYuZhiSetting());
        viewPager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments != null ? fragments.get(i) : new Fragment();
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        title_1.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.getString("MESSAGE").equals("充值记录"))
                viewPager.setCurrentItem(1);
        }
    }

    private void initView(View view) {
        title_1 = (TextView) view.findViewById(R.id.title_1);
        title_2 = (TextView) view.findViewById(R.id.title_2);
        title_3 = (TextView) view.findViewById(R.id.title_3);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    }
}
