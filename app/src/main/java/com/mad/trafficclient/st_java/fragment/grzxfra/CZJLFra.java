package com.mad.trafficclient.st_java.fragment.grzxfra;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.ws_java.ob9.Acc_Dao;
import com.mad.trafficclient.ws_java.ob9.JiluBean;
import com.mad.trafficclient.zy_java.bean.AllPersonBean;
import com.mad.trafficclient.zy_java.data.CarData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CZJLFra extends Fragment {
    private ImageView icon;
    private TextView pname;
    private TextView money;
    private ListView listView;
    private Context context;
    private String userName;
    private List<AllPersonBean.ROWSDETAILBean> rowsdetailBeans;
    private TextView tv_show;
    private List<JiluBean> jiluBeans;
    private JLAda jlAda;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_czjl, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        context = getContext();
        userName = Util.getUserName(context);
        rowsdetailBeans = CarData.getallperson_list();

        for (int i = 0; i < rowsdetailBeans.size(); i++) {
            AllPersonBean.ROWSDETAILBean rowsdetailBean = rowsdetailBeans.get(i);
            if (rowsdetailBean.getUsername().equals(userName)) {
                //匹配到用户
                pname.setText(rowsdetailBean.getPname());
                String psex = rowsdetailBean.getPsex();
                if (psex.equals("男")) {
                    icon.setBackgroundResource(R.drawable.touxiang_2);
                } else {
                    icon.setBackgroundResource(R.drawable.touxiang_1);
                }
                break;
            }
        }
        listView.setEmptyView(tv_show);
        jiluBeans = new Acc_Dao(context).queryForAll();
        Collections.sort(jiluBeans, new Comparator<JiluBean>() {
            @Override
            public int compare(JiluBean o1, JiluBean o2) {
                return o2.getId() - o1.getId();
            }
        });
        jlAda = new JLAda();
        listView.setAdapter(jlAda);
        int temp = 0;
        for (int i = 0; i < jiluBeans.size(); i++) {
            temp += jiluBeans.get(i).getMoney();
        }
        money.setText("" + temp);

    }

    private void initView(View view) {
        icon = (ImageView) view.findViewById(R.id.icon);
        pname = (TextView) view.findViewById(R.id.pname);
        money = (TextView) view.findViewById(R.id.money);
        listView = (ListView) view.findViewById(R.id.listView);
        tv_show = (TextView) view.findViewById(R.id.tv_show);
    }

    public class JLAda extends BaseAdapter {

        @Override
        public int getCount() {
            return jiluBeans != null ? jiluBeans.size() : 0;
        }

        @Override
        public JiluBean getItem(int position) {
            return jiluBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            JiluBean item = getItem(position);
            View view = View.inflate(context, R.layout.ada_czjl, null);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.user.setText("充值人:" + item.getUser());
            viewHolder.chepai.setText("车牌号:" + item.getChepai());
            viewHolder.money.setText("充值:" + item.getMoney());
            viewHolder.balance.setText("余额:" + item.getBalance());
            String time = item.getTime();
            viewHolder.time.setText("" + time);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEEE");
            try {
                Date parse = simpleDateFormat.parse(time);
                viewHolder.tv_data.setText(simpleDateFormat1.format(parse));
                viewHolder.tv_week.setText(simpleDateFormat2.format(parse));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return view;
        }

        public class ViewHolder {
            public View rootView;
            public TextView tv_data;
            public TextView tv_week;
            public TextView user;
            public TextView chepai;
            public TextView money;
            public TextView balance;
            public TextView time;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tv_data = (TextView) rootView.findViewById(R.id.tv_data);
                this.tv_week = (TextView) rootView.findViewById(R.id.tv_week);
                this.user = (TextView) rootView.findViewById(R.id.user);
                this.chepai = (TextView) rootView.findViewById(R.id.chepai);
                this.money = (TextView) rootView.findViewById(R.id.money);
                this.balance = (TextView) rootView.findViewById(R.id.balance);
                this.time = (TextView) rootView.findViewById(R.id.time);
            }

        }
    }
}
