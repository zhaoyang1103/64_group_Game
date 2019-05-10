package com.mad.trafficclient.st_java.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.GetAllSuggestion;
import com.mad.trafficclient.st_java.bean.MyOpinion;

import java.util.List;

public class MyOpinionAdaUPdate extends BaseAdapter {
    private GetAllSuggestion getAllSuggestion;
    private Context context;

    public MyOpinionAdaUPdate(GetAllSuggestion getAllSuggestion, Context context) {
        this.getAllSuggestion = getAllSuggestion;
        this.context = context;
    }

    @Override
    public int getCount() {
        return getAllSuggestion.getROWS_DETAIL().size();
    }

    @Override
    public MyOpinion getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_my_opinion, null);
        ViewHolder viewHolder = new ViewHolder(view);
        List<GetAllSuggestion.ROWSDETAILBean> rows_detail = getAllSuggestion.getROWS_DETAIL();
        GetAllSuggestion.ROWSDETAILBean rowsdetailBean = rows_detail.get(position);
        viewHolder.title.setText("标题：" + rowsdetailBean.getTitle());
        viewHolder.state.setText("状态：" + "未受理");
        viewHolder.submit_time.setText("提交时间：" + rowsdetailBean.getSubmit_time());
        viewHolder.call_back_content.setText("回复内容：" + rowsdetailBean.getReply_content());
        viewHolder.call_back_time.setText("回复时间：" + rowsdetailBean.getReply_time());
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
