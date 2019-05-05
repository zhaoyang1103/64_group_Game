/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.view.GlideView;

import java.util.ArrayList;
import java.util.List;


public class Zy_charmain extends Fragment {

    private TextView tx_show;
    private ViewPager viewpager;
    private GlideView glideview;
    private List<Fragment> list;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.chartmain, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        tx_show = (TextView) view.findViewById(R.id.tx_show);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        glideview = (GlideView) view.findViewById(R.id.glideview);
        list = new ArrayList<>();
        context = getContext();
        addData();
        viewpager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                glideview.setIndex(context, i);
                switch (i) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void addData() {
        if (list.size() > 0) {
            glideview.setCount(list.size());
            glideview.setIndex(context, 0);

        }
    }
}
