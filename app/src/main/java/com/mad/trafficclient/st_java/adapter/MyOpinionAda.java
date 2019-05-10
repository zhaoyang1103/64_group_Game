package com.mad.trafficclient.st_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.MyOpinion;

import java.util.ArrayList;

public class MyOpinionAda extends BaseAdapter {
    private ArrayList<MyOpinion> myOpinions;
    private Context context;

    public MyOpinionAda(ArrayList<MyOpinion> myOpinions, Context context) {
        this.myOpinions = myOpinions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return myOpinions != null ? myOpinions.size() : 0;
    }

    @Override
    public MyOpinion getItem(int position) {
        return myOpinions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_my_opinion, null);
        ViewHolder viewHolder = new ViewHolder(view);
        MyOpinion item = getItem(position);
        viewHolder.title.setText("标题：" + item.getTitle());
        viewHolder.state.setText("状态：" + item.getState());
        viewHolder.submit_time.setText("提交时间：" + item.getSubmit_time());
        viewHolder.call_back_content.setText("回复内容：" + item.getCall_back_content());
        viewHolder.call_back_time.setText("回复时间：" + item.getCall_back_time());
        return view;
    }

    public static
    class ViewHolder {
        public View rootView;
        public TextView title;
        public TextView state;
        public TextView submit_time;
        public TextView call_back_content;
        public TextView call_back_time;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.state = (TextView) rootView.findViewById(R.id.state);
            this.submit_time = (TextView) rootView.findViewById(R.id.submit_time);
            this.call_back_content = (TextView) rootView.findViewById(R.id.call_back_content);
            this.call_back_time = (TextView) rootView.findViewById(R.id.call_back_time);
        }

    }
}
