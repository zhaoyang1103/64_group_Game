package com.mad.trafficclient.st_java.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.YHGLBean;
import com.mad.trafficclient.zy_java.bean.AllPersonBean;
import com.mad.trafficclient.zy_java.data.CarData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YHGLFra extends Fragment {
    private ListView listView;
    private Context context;
    private YHGLAda yhglAda;
    private ArrayList<YHGLBean> beans;
    private List<AllPersonBean.ROWSDETAILBean> rowsdetailBeans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_yhgl, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    private void initData() {
        context = getContext();
        rowsdetailBeans = CarData.getallperson_list();
        beans = new ArrayList<>();
        for (int i = 0; i < rowsdetailBeans.size(); i++) {
            AllPersonBean.ROWSDETAILBean rowsdetailBean = rowsdetailBeans.get(i);
            YHGLBean e = new YHGLBean();
            String psex = rowsdetailBean.getPsex();
            if (psex.equals("男")) {
                e.setIcon(R.drawable.touxiang_2);
            } else {
                e.setIcon(R.drawable.touxiang_1);
            }
            e.setUsername("用户名:" + rowsdetailBean.getUsername());
            e.setPname("姓名:" + rowsdetailBean.getPname());
            e.setPtel("电话" + rowsdetailBean.getPtel());
            e.setBtn_shoucang_state(false);
            e.setBtn_shanchu_state(false);
            e.setTime_state(false);
            e.setTv_shoucang_state(false);
            beans.add(e);
        }
        yhglAda = new YHGLAda(beans);
        listView.setAdapter(yhglAda);
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
    }

    public class YHGLAda extends BaseAdapter {

        private ViewHolder viewHolder;
        private YHGLBean item;
        private ArrayList<YHGLBean> beans;


        public YHGLAda(ArrayList<YHGLBean> beans) {
            this.beans = beans;
        }

        @Override
        public int getCount() {
            return beans != null ? beans.size() : 0;
        }

        @Override
        public YHGLBean getItem(int position) {
            return beans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(context, R.layout.ada_yhgl, null);
            item = getItem(position);
            viewHolder = new ViewHolder(view);
            viewHolder.image.setBackgroundResource(item.getIcon());
            viewHolder.username.setText(item.getUsername());
            viewHolder.pname.setText(item.getPname());
            viewHolder.ptel.setText(item.getPtel());
            viewHolder.time.setText(item.getTime());
            if (item.isBtn_shoucang_state()) {
                viewHolder.btn_shoucang.setVisibility(View.VISIBLE);
                viewHolder.btn_shanchu.setVisibility(View.VISIBLE);
                viewHolder.image.setVisibility(View.GONE);
            } else {
                viewHolder.btn_shoucang.setVisibility(View.GONE);
                viewHolder.btn_shanchu.setVisibility(View.GONE);
                viewHolder.image.setVisibility(View.VISIBLE);
            }
            if (item.isTime_state()) {
                viewHolder.time.setVisibility(View.VISIBLE);
            } else {
                viewHolder.time.setVisibility(View.GONE);
            }
            if (item.isTv_shoucang_state()) {
                viewHolder.tv_shoucang.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_shoucang.setVisibility(View.GONE);
            }
            viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (beans.get(position).isBtn_shoucang_state()) {
                        beans.get(position).setBtn_shoucang_state(false);
                    } else {
                        beans.get(position).setBtn_shoucang_state(true);
                    }
                    notifyDataSetChanged();
                }
            });

            viewHolder.btn_shanchu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    beans.remove(position);
                    notifyDataSetChanged();
                }
            });

            viewHolder.btn_shoucang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    YHGLBean yhflBean = beans.get(position);
                    yhflBean.setTv_shoucang_state(true);
                    yhflBean.setTime_state(true);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.M.d hh:mm");
                    yhflBean.setTime(simpleDateFormat.format(new Date()));
                    notifyDataSetChanged();
                }
            });

            initAdaListener();
            return view;
        }

        private void initAdaListener() {
//            viewHolder.relativeLayout.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_MOVE:
//                            Toast.makeText(context, "move", Toast.LENGTH_SHORT).show();
//                            if (state) {
//                                viewHolder.btn_shoucang.setVisibility(View.VISIBLE);
//                                viewHolder.btn_shanchu.setVisibility(View.VISIBLE);
//                            }
//                            else {
//                                Toast.makeText(context, "GONE", Toast.LENGTH_SHORT).show();
//
//                                viewHolder.btn_shoucang.setVisibility(View.GONE);
//                                viewHolder.btn_shanchu.setVisibility(View.GONE);
//                            }
//                        case MotionEvent.ACTION_UP:
//                            if(state)
//                            {
//                                state=false;
//                            }
//                            else {
//                                state=true;
//                            }
//                            break;
//                    }
//
//
//                    return true;
//                }
//            });
        }

        public class ViewHolder {
            public View rootView;
            public ImageView image;
            public TextView username;
            public TextView pname;
            public TextView ptel;
            public TextView time;
            public TextView tv_guanliyuan;
            public TextView tv_shoucang;
            public RelativeLayout relativeLayout;
            public TextView btn_shoucang;
            public TextView btn_shanchu;
            public ImageView iv_back;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.image = (ImageView) rootView.findViewById(R.id.image);
                this.username = (TextView) rootView.findViewById(R.id.username);
                this.pname = (TextView) rootView.findViewById(R.id.pname);
                this.ptel = (TextView) rootView.findViewById(R.id.ptel);
                this.time = (TextView) rootView.findViewById(R.id.time);
                this.tv_guanliyuan = (TextView) rootView.findViewById(R.id.tv_guanliyuan);
                this.tv_shoucang = (TextView) rootView.findViewById(R.id.tv_shoucang);
                this.relativeLayout = (RelativeLayout) rootView.findViewById(R.id.relativeLayout);
                this.btn_shoucang = (TextView) rootView.findViewById(R.id.btn_shoucang);
                this.btn_shanchu = (TextView) rootView.findViewById(R.id.btn_shanchu);
                this.iv_back = (ImageView) rootView.findViewById(R.id.iv_back);
            }

        }
    }
}
