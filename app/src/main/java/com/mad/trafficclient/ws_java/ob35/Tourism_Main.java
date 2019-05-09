package com.mad.trafficclient.ws_java.ob35;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob9.Acc_Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/9 11:00
 */
public class Tourism_Main extends Fragment {
    private GridView ob35_gridview;
    private Context context;
    private List<TourismBean> list;
    private TourismAdapter tourismAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob35_main, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        list.add(new TourismBean("bus_1",
                "故宫",
                60,
                "绝大多数的傻逼，都喜欢在节假日的时候去景点旅游，被挤成傻子还是要去，我都傻了我都.",
                3,
                "010-88888888"));
        list.add(new TourismBean("bus_2",
                "长城",
                50,
                "北京第三区交通委提醒您：道路千万条，安全第一条，行车不规范，亲人两行泪.",
                4,
                "010-66666666"));
        list.add(new TourismBean("add2",
                "水立方",
                120,
                "道路千万条\n安全第一条\n行车不规范\n亲人两行泪.",
                5,
                "010-22222222"));
        tourismAdapter = new TourismAdapter();
        ob35_gridview.setAdapter(tourismAdapter);
        return view;
    }

    private void initView(View view) {
        ob35_gridview = (GridView) view.findViewById(R.id.ob35_gridview);
    }


    class TourismAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.ob35_grid_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob35_img.setImageResource(Acc_Util.getImage(list.get(position).getTouris_image()));
            viewHolder.ob35_tv_name.setText(list.get(position).getName());
            viewHolder.ob35_tv_jiage.setText("票价 ￥" + list.get(position).getMoney());

            viewHolder.ob35_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putString("details_text","景点介绍\n" + list.get(position).getDetails_text());
                    args.putString("phonenumber",list.get(position).getNumber());
                    args.putString("photo",list.get(position).getTouris_image());
                    args.putInt("dianping",list.get(position).getDianping());
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent,TourismDetails.newInstance(args)).commit();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public ImageView ob35_img;
            public TextView ob35_tv_name;
            public TextView ob35_tv_jiage;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob35_img = (ImageView) rootView.findViewById(R.id.ob35_img);
                this.ob35_tv_name = (TextView) rootView.findViewById(R.id.ob35_tv_name);
                this.ob35_tv_jiage = (TextView) rootView.findViewById(R.id.ob35_tv_jiage);
            }

        }
    }
}
