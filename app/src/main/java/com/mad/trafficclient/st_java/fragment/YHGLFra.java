package com.mad.trafficclient.st_java.fragment;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.YHFLBean;

import java.util.ArrayList;

public class YHGLFra extends Fragment {
    private ListView listView;
    private Context context;
    private YHGLAda yhglAda;
    private ArrayList<YHFLBean> beans;


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

    }

    private void initData() {
        context = getContext();
        beans = new ArrayList<>();
        beans.add(new YHFLBean(false, true, true));
        beans.add(new YHFLBean(false, true, true));
        beans.add(new YHFLBean(false, true, true));
        yhglAda = new YHGLAda(beans);
        listView.setAdapter(yhglAda);
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
    }

    public class YHGLAda extends BaseAdapter {

        private ViewHolder viewHolder;
        private YHFLBean item;
        private ArrayList<YHFLBean> beans;


        public YHGLAda(ArrayList<YHFLBean> beans) {
            this.beans = beans;
        }

        @Override
        public int getCount() {
            return beans != null ? beans.size() : 0;
        }

        @Override
        public YHFLBean getItem(int position) {
            return beans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(context, R.layout.ada_yhgl, null);
            item = getItem(position);
            viewHolder = new ViewHolder(view);
            if (item.isBtn_shoucang_state()) {
                viewHolder.btn_shoucang.setVisibility(View.VISIBLE);
                viewHolder.btn_shanchu.setVisibility(View.VISIBLE);
            } else {
                viewHolder.btn_shoucang.setVisibility(View.GONE);
                viewHolder.btn_shanchu.setVisibility(View.GONE);
            }
            initAdaData();
            initAdaListener();
            return view;
        }

        private boolean state = true;

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
            viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.isBtn_shoucang_state()) {
                        Toast.makeText(context, "VISIBLE", Toast.LENGTH_SHORT).show();
//                        viewHolder.btn_shoucang.setVisibility(View.VISIBLE);
//                        viewHolder.btn_shanchu.setVisibility(View.VISIBLE);
                        item.setBtn_shoucang_state(false);
                    } else {
                        Toast.makeText(context, "GONE", Toast.LENGTH_SHORT).show();
//                        viewHolder.btn_shoucang.setVisibility(View.GONE);
//                        viewHolder.btn_shanchu.setVisibility(View.GONE);
                        item.setBtn_shoucang_state(true);
                    }
                    notifyDataSetChanged();
                }
            });

        }

        private void initAdaData() {

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
