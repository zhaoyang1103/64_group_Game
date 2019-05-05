package com.mad.trafficclient.zy_java.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.bean.LightManageBean;

import java.util.List;

public class LightManageAdapter extends BaseAdapter {
    private List<LightManageBean> list;
    private Context context;
    private ViewHolder viewHolder;

    public LightManageAdapter(List<LightManageBean> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_lightmanage2, null);
        viewHolder = new ViewHolder(convertView);
        viewHolder.item_road.setText(list.get(position).getRoad() + "");
        Log.i("黄灯测试", "getView: " + list.get(position).getYellowTime());
        viewHolder.item_redtime.setText(list.get(position).getRedTime() + "");
        viewHolder.item_greentime.setText(list.get(position).getGreenTime() + "");
        viewHolder.item_yellowtime.setText(list.get(position).getYellowTime() + "");
        return convertView;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView item_road;
        public TextView item_redtime;
        public TextView item_yellowtime;
        public TextView item_greentime;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_road = (TextView) rootView.findViewById(R.id.item_road);
            this.item_redtime = (TextView) rootView.findViewById(R.id.item_redtime);
            this.item_yellowtime = (TextView) rootView.findViewById(R.id.item_yellowtime);
            this.item_greentime = (TextView) rootView.findViewById(R.id.item_greentime);
        }

    }
}
