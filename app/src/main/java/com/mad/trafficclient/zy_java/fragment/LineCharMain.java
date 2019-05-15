/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob5.SenseDao;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.manage.Chart_1;
import com.mad.trafficclient.zy_java.manage.Chart_2;
import com.mad.trafficclient.zy_java.manage.Chart_3;
import com.mad.trafficclient.zy_java.manage.Chart_4;
import com.mad.trafficclient.zy_java.manage.Chart_5;
import com.mad.trafficclient.zy_java.manage.Chart_6;
import com.mad.trafficclient.zy_java.manage.Chart_7;
import com.mad.trafficclient.zy_java.manage.IndexLine1_Frag;
import com.mad.trafficclient.zy_java.manage.IndexLine2_Frag;
import com.mad.trafficclient.zy_java.manage.IndexLine3_Frag;
import com.mad.trafficclient.zy_java.manage.IndexLine4_Frag;
import com.mad.trafficclient.zy_java.manage.IndexLine5_Frag;
import com.mad.trafficclient.zy_java.manage.IndexLine6_Frag;
import com.mad.trafficclient.zy_java.manage.LineShowFrag;
import com.mad.trafficclient.zy_java.view.GlideView;

import java.util.ArrayList;
import java.util.List;


public class LineCharMain extends Fragment {

    private TextView tx_show;
    private ViewPager viewpager;
    private GlideView glideview;
    private List<Fragment> list;
    private Context context;
    private int postion_data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            postion_data = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.chartmain, container, false);

        initView(view);


        return view;
    }

    @Override
    public void onDestroy() {
        LineShowFrag.getTimer().cancel();
        super.onDestroy();
    }

    public static Fragment getIntance(int position) {
        LineCharMain lineCharMain = new LineCharMain();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        lineCharMain.setArguments(bundle);
        return lineCharMain;
    }

    private void initView(View view) {
        tx_show = (TextView) view.findViewById(R.id.tx_show);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        glideview = (GlideView) view.findViewById(R.id.glideview);
        list = new ArrayList<>();
        context = getContext();
        list.add(new IndexLine1_Frag());
        list.add(new IndexLine2_Frag());
        list.add(new IndexLine3_Frag());
        list.add(new IndexLine4_Frag());
        list.add(new IndexLine5_Frag());
        list.add(new IndexLine6_Frag());
        Adapter adapter = new Adapter(getFragmentManager());
        viewpager.setAdapter(adapter);
        addData();

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.i("滑动", "onPageScrolled: " + i);
            }

            @Override
            public void onPageSelected(int i) {
                glideview.setIndex(context, i);
                getshow(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void addData() {
        if (list.size() > 0) {
            glideview.setCount(list.size());
            viewpager.setCurrentItem(postion_data);
            glideview.setIndex(context, postion_data);
            getshow(postion_data);
        }
    }

    class Adapter extends FragmentStatePagerAdapter {
        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    private void getshow(int postion_data) {
        switch (postion_data) {
            case 0:
                tx_show.setText("温度");
                break;
            case 1:
                tx_show.setText("湿度");
                break;
            case 2:
                tx_show.setText("光照强度");
                break;
            case 3:
                tx_show.setText("co2");
                break;
            case 4:
                tx_show.setText("pm2.5");
                break;
            case 5:
                tx_show.setText("道路状况");
                break;

        }
    }
}
