package com.mad.trafficclient.zy_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.bean.AllPeccancyBean;
import com.mad.trafficclient.zy_java.bean.AllTypeBean;
import com.mad.trafficclient.zy_java.data.CarData;
import com.mad.trafficclient.zy_java.util.ZyUtil;

import java.util.List;

public class Peccancy_leftAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    public Peccancy_leftAdapter(List<String> list, Context context) {
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
        convertView = View.inflate(context, R.layout.item_left_peccancy, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.image_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZyUtil.deleteCarnumber(context, list.get(position));
                list.remove(position);
                notifyDataSetChanged();

            }
        });
        viewHolder.tx_carnumber.setText(list.get(position));
        List<AllPeccancyBean.ROWSDETAILBean> rowsdetailBeans = CarData.getallpeccancy_list();
        List<AllTypeBean.ROWSDETAILBean> alltype_list = CarData.getAlltype_list();
        int count = 0;
        int money = 0;
        int socer = 0;
        for (int i = 0; i < rowsdetailBeans.size(); i++) {
            if (rowsdetailBeans.get(i).getCarnumber().equals(list.get(position))) {
                count++;
                for (int j = 0; j < alltype_list.size(); j++) {
                    if (alltype_list.get(j).getPcode().equals(rowsdetailBeans.get(i).getPcode())) {
                        money += alltype_list.get(j).getPmoney();
                        socer += alltype_list.get(j).getPscore();
                    }

                }
            }
        }

        viewHolder.tx_money.setText(money + "");
        viewHolder.tx_count.setText(count + "");
        viewHolder.tx_min.setText(socer + "");
        return convertView;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView tx_count;
        public TextView tx_min;
        public TextView tx_money;
        public TextView tx_carnumber;
        public ImageView image_remove;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tx_count = (TextView) rootView.findViewById(R.id.tx_count);
            this.tx_min = (TextView) rootView.findViewById(R.id.tx_min);
            this.tx_money = (TextView) rootView.findViewById(R.id.tx_money);
            this.tx_carnumber = (TextView) rootView.findViewById(R.id.tx_carnumber);
            this.image_remove = (ImageView) rootView.findViewById(R.id.image_remove);
        }

    }
}
