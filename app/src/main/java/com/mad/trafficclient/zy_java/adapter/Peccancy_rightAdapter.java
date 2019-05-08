package com.mad.trafficclient.zy_java.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.bean.AllPeccancyBean;
import com.mad.trafficclient.zy_java.bean.AllTypeBean;
import com.mad.trafficclient.zy_java.bean.Peccancy_rightBean;
import com.mad.trafficclient.zy_java.data.CarData;

import java.util.List;

public class Peccancy_rightAdapter extends BaseAdapter {
    private List<Peccancy_rightBean.CarActionBean> list;
    private Context context;

    public Peccancy_rightAdapter(List<Peccancy_rightBean.CarActionBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_right_peccancy, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.right_road.setText(list.get(position).getPaddr());
        viewHolder.right_time.setText(list.get(position).getDatetime());
        Log.i("无试图", "getView:无试图 ");
        List<AllTypeBean.ROWSDETAILBean> alltype_list = CarData.getAlltype_list();

        for (int i = 0; i < alltype_list.size(); i++) {
            if (alltype_list.get(i).getPcode().equals(list.get(position).getPcode())) {
                viewHolder.right_pcmark.setText(alltype_list.get(i).getPremarks()+"");
                viewHolder.tx_min.setText(alltype_list.get(i).getPscore()+"");
                viewHolder.tx_money.setText(alltype_list.get(i).getPmoney()+"");
                break;
            }

        }
        return convertView;
    }


    public static
    class ViewHolder {
        public View rootView;
        public TextView right_time;
        public TextView right_road;
        public TextView right_pcmark;
        public TextView tx_min;
        public TextView tx_money;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.right_time = (TextView) rootView.findViewById(R.id.right_time);
            this.right_road = (TextView) rootView.findViewById(R.id.right_road);
            this.right_pcmark = (TextView) rootView.findViewById(R.id.right_pcmark);
            this.tx_min = (TextView) rootView.findViewById(R.id.tx_min);
            this.tx_money = (TextView) rootView.findViewById(R.id.tx_money);
        }

    }
}
