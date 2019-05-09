package com.mad.trafficclient.ws_java.ob39;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mad.trafficclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/9 12:08
 */
public class NewsMedia_Main extends Fragment implements View.OnClickListener {
    private LinearLayout ob39_ll_keji;
    private LinearLayout ob39_keji;
    private LinearLayout ob39_ll_jiaoyu;
    private LinearLayout ob39_jiaoyu;
    private LinearLayout ob39_ll_tiyu;
    private LinearLayout ob39_tiyu;
    private ListView ob39_listview;
    private Context context;
    private List<String> list;
    private NewsMediaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob39_main, container, false);
        initView(view);
        context = getContext();
        list = new ArrayList<>();
        list.add("科技第1条");
        list.add("科技第2条");
        adapter = new NewsMediaAdapter();
        ob39_listview.setAdapter(adapter);
        return view;
    }

    private void initView(View view) {
        ob39_ll_keji = (LinearLayout) view.findViewById(R.id.ob39_ll_keji);
        ob39_keji = (LinearLayout) view.findViewById(R.id.ob39_keji);
        ob39_ll_jiaoyu = (LinearLayout) view.findViewById(R.id.ob39_ll_jiaoyu);
        ob39_jiaoyu = (LinearLayout) view.findViewById(R.id.ob39_jiaoyu);
        ob39_ll_tiyu = (LinearLayout) view.findViewById(R.id.ob39_ll_tiyu);
        ob39_tiyu = (LinearLayout) view.findViewById(R.id.ob39_tiyu);
        ob39_listview = (ListView) view.findViewById(R.id.ob39_listview);

        ob39_keji.setOnClickListener(this);
        ob39_jiaoyu.setOnClickListener(this);
        ob39_tiyu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        list.clear();
        switch (v.getId()) {
            case R.id.ob39_keji:
                list.add("科技第1条");
                list.add("科技第2条");
                adapter.notifyDataSetChanged();
                ob39_ll_keji.setBackgroundColor(Color.YELLOW);
                ob39_ll_jiaoyu.setBackgroundColor(Color.TRANSPARENT);
                ob39_ll_tiyu.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.ob39_jiaoyu:
                list.add("教育第1条");
                list.add("教育第2条");
                list.add("教育第3条");
                adapter.notifyDataSetChanged();
                ob39_ll_keji.setBackgroundColor(Color.TRANSPARENT);
                ob39_ll_jiaoyu.setBackgroundColor(Color.YELLOW);
                ob39_ll_tiyu.setBackgroundColor(Color.TRANSPARENT);
                break;
            case R.id.ob39_tiyu:
                list.add("体育第1条");
                list.add("体育第2条");
                list.add("体育第3条");
                list.add("体育第4条");
                adapter.notifyDataSetChanged();
                ob39_ll_keji.setBackgroundColor(Color.TRANSPARENT);
                ob39_ll_jiaoyu.setBackgroundColor(Color.TRANSPARENT);
                ob39_ll_tiyu.setBackgroundColor(Color.YELLOW);
                break;
        }
    }


    class NewsMediaAdapter extends BaseAdapter {

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
            convertView = View.inflate(context, R.layout.ob39_list_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob39_lv_text.setText(list.get(position));
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ob39_lv_text;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob39_lv_text = (TextView) rootView.findViewById(R.id.ob39_lv_text);
            }

        }
    }
}
