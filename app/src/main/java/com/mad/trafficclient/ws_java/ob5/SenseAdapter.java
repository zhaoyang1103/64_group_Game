package com.mad.trafficclient.ws_java.ob5;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/6 17:34
 */
public class SenseAdapter extends BaseAdapter {
    private Context context;
    private List<SenseBean> list;

    public SenseAdapter(Context context, List<SenseBean> list) {
        this.context = context;
        this.list = list;
    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.ob5_grid_item, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.ob5_gv_name.setText(list.get(position).getName());
        viewHolder.ob5_gv_zhi.setText(list.get(position).getZhi()+"");
        if (SenseUtil.getSwitch(context)){
            if (list.get(position).getZhi() > list.get(position).getYuzhi()){
                viewHolder.ob5_background.setBackgroundColor(Color.RED);
            } else {
                viewHolder.ob5_background.setBackgroundColor(Color.GREEN);
            }
        } else {
            viewHolder.ob5_background.setBackgroundColor(Color.YELLOW);
        }
        return convertView;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView ob5_gv_name;
        public TextView ob5_gv_zhi;
        public RelativeLayout ob5_background;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.ob5_gv_name = (TextView) rootView.findViewById(R.id.ob5_gv_name);
            this.ob5_gv_zhi = (TextView) rootView.findViewById(R.id.ob5_gv_zhi);
            this.ob5_background = (RelativeLayout) rootView.findViewById(R.id.ob5_background);
        }

    }
}
