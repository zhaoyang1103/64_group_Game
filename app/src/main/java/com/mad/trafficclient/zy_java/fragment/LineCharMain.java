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
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.manage.Chart_1;
import com.mad.trafficclient.zy_java.manage.Chart_2;
import com.mad.trafficclient.zy_java.manage.Chart_3;
import com.mad.trafficclient.zy_java.manage.Chart_4;
import com.mad.trafficclient.zy_java.manage.Chart_5;
import com.mad.trafficclient.zy_java.manage.Chart_6;
import com.mad.trafficclient.zy_java.manage.Chart_7;
import com.mad.trafficclient.zy_java.view.GlideView;

import java.util.ArrayList;
import java.util.List;


public class LineCharMain extends Fragment {

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
        if (CarData.getFlag() < 3) {
            getFragmentManager().popBackStack();
            Toast.makeText(context, "数据正在读取", Toast.LENGTH_SHORT).show();
        }

        initView(view);
        return view;
    }

    private void initView(View view) {
        tx_show = (TextView) view.findViewById(R.id.tx_show);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        glideview = (GlideView) view.findViewById(R.id.glideview);
        list = new ArrayList<>();
        context = getContext();

        tx_show.setText("有违章车辆和无违章车辆的占比统计");

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
                    case 6:
                        tx_show.setText("排名前十位的交通违法行为的占比统计");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void addData() {
        list.add(new Chart_1());
        list.add(new Chart_2());
        list.add(new Chart_3());
        list.add(new Chart_4());
        list.add(new Chart_5());
        list.add(new Chart_6());
        list.add(new Chart_7());
        if (list.size() > 0) {
            glideview.setCount(list.size());
            glideview.setIndex(context, 0);

        }
    }
}
