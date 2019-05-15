package com.mad.trafficclient.ws_java.ob63;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob35.TourismDetails;
import com.mad.trafficclient.ws_java.ob9.Acc_Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/10 19:24
 */
public class TravelAssistant_Main extends Fragment {
    private GridView ob63_gridview;
    private Context context;
    private List<TravelAssistant_Bean> list;
    private TravelAssistant_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob63_main, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        list.add(new TravelAssistant_Bean("bus_1",
                "故宫",
                60,
                "绝大多数的傻逼，都喜欢在节假日的时候去景点旅游，被挤成傻子还是要去，我都傻了我都.",
                3,
                "010-88888888"));
        list.add(new TravelAssistant_Bean("bus_2",
                "长城",
                50,
                "北京第三区交通委提醒您：道路千万条，安全第一条，行车不规范，亲人两行泪.",
                4,
                "010-66666666"));
        list.add(new TravelAssistant_Bean("add2",
                "水立方",
                120,
                "道路千万条\n安全第一条\n行车不规范\n亲人两行泪.",
                5,
                "010-22222222"));
        adapter = new TravelAssistant_Adapter();
        ob63_gridview.setAdapter(adapter);
        return view;
    }

    private void initView(View view) {
        ob63_gridview = (GridView) view.findViewById(R.id.ob63_gridview);
    }


    class TravelAssistant_Adapter extends BaseAdapter {

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
            convertView = View.inflate(context, R.layout.ob63_grid_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob63_img.setImageResource(Acc_Util.getImage(list.get(position).getTouris_image()));
            viewHolder.ob63_tv_name.setText(list.get(position).getName());
            viewHolder.ob63_tv_jiage.setText("票价 ￥" + list.get(position).getMoney());
            viewHolder.ob63_btn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putInt("id", position);
                    args.putString("photo", list.get(position).getTouris_image());
                    args.putString("name", list.get(position).getName());
                    args.putString("details_text",list.get(position).getDetails_text());
                    args.putInt("balance", list.get(position).getMoney());
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, TravelAssistant_Shopping.newInstance(args)).commit();
                }
            });

            viewHolder.ob63_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putString("details_text", "景点介绍\n" + list.get(position).getDetails_text());
                    args.putString("phonenumber", list.get(position).getNumber());
                    args.putString("photo", list.get(position).getTouris_image());
                    args.putInt("dianping", list.get(position).getDianping());
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, TravelAssistant_Details.newInstance(args)).commit();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public ImageView ob63_img;
            public TextView ob63_tv_name;
            public Button ob63_btn_buy;
            public TextView ob63_tv_jiage;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob63_img = (ImageView) rootView.findViewById(R.id.ob63_img);
                this.ob63_tv_name = (TextView) rootView.findViewById(R.id.ob63_tv_name);
                this.ob63_btn_buy = (Button) rootView.findViewById(R.id.ob63_btn_buy);
                this.ob63_tv_jiage = (TextView) rootView.findViewById(R.id.ob63_tv_jiage);
            }

        }
    }
}
