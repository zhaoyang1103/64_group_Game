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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.ws_java.ob9.Acc_Util;
import com.mad.trafficclient.zy_java.bean.AllCarBean;
import com.mad.trafficclient.zy_java.bean.AllPersonBean;
import com.mad.trafficclient.zy_java.data.CarData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GRXXFra extends Fragment {
    private ImageView image;
    private TextView pname;
    private TextView psex;
    private TextView ptel;
    private TextView pcardid;
    private TextView pregistdate;
    private GridView gridView;
    private Context context;
    private String userName;
    private List<AllPersonBean.ROWSDETAILBean> rowsdetailBeans;
    private ArrayList<Bean> beans;
    private CarAda carAda;
    private String s_pcardid;
    private List<AllCarBean.ROWSDETAILBean> allcar_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_grxx, container, false);
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
                pname.setText("姓名:" + rowsdetailBean.getPname());
                String psex = rowsdetailBean.getPsex();
                if (psex.equals("男")) {
                    image.setBackgroundResource(R.drawable.touxiang_2);
                } else {
                    image.setBackgroundResource(R.drawable.touxiang_1);
                }
                this.psex.setText("性别:" + psex);
                ptel.setText("手机号码:" + rowsdetailBean.getPtel());
                s_pcardid = rowsdetailBean.getPcardid();
                this.pcardid.setText("身份证号:" + s_pcardid);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.M.d");
                try {
                    String format = simpleDateFormat1.format(simpleDateFormat.parse(rowsdetailBean.getPregistdate()));
                    pregistdate.setText("注册时间" + format);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        beans = new ArrayList<>();
        carAda = new CarAda();
        gridView.setAdapter(carAda);

        allcar_list = CarData.getAllcar_list();
        for (int i = 0; i < allcar_list.size(); i++) {
            AllCarBean.ROWSDETAILBean rowsdetailBean = allcar_list.get(i);
            if (rowsdetailBean.getPcardid().equals(s_pcardid)) {
                beans.add(new Bean(Acc_Util.getImage(rowsdetailBean.getCarbrand()), rowsdetailBean.getCarnumber()));
            }
        }
        carAda.notifyDataSetChanged();

    }

    private void initView(View view) {
        image = (ImageView) view.findViewById(R.id.image);
        pname = (TextView) view.findViewById(R.id.pname);
        psex = (TextView) view.findViewById(R.id.psex);
        ptel = (TextView) view.findViewById(R.id.ptel);
        pcardid = (TextView) view.findViewById(R.id.pcardid);
        pregistdate = (TextView) view.findViewById(R.id.pregistdate);
        gridView = (GridView) view.findViewById(R.id.gridView);
    }

    public class Bean {
        private int icon;
        private String name;

        public Bean(int icon, String name) {
            this.icon = icon;
            this.name = name;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class CarAda extends BaseAdapter {

        @Override
        public int getCount() {
            return beans != null ? beans.size() : 0;
        }

        @Override
        public Bean getItem(int position) {
            return beans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Bean item = getItem(position);
            View view = View.inflate(context, R.layout.ada_grxx, null);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.icon.setBackgroundResource(item.getIcon());
            viewHolder.carnumber.setText(item.getName());
            return view;
        }

        public class ViewHolder {
            public View rootView;
            public ImageView icon;
            public TextView carnumber;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.icon = (ImageView) rootView.findViewById(R.id.icon);
                this.carnumber = (TextView) rootView.findViewById(R.id.carnumber);
            }

        }
    }
}
