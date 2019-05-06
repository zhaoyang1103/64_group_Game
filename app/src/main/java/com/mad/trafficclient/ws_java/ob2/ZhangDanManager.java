package com.mad.trafficclient.ws_java.ob2;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob1.AccountBean;
import com.mad.trafficclient.ws_java.ob1.AccountDao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/6 16:14
 */
public class ZhangDanManager extends Fragment implements View.OnClickListener {
    private Spinner ob2_spinner;
    private Button ob2_btn_query;
    private ListView ob2_listview;
    private TextView ob2_tishi;
    private Context context;
    private List<AccountBean> list;
    private ZhangDanAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob2_main, container, false);
        context = getContext();
        initView(view);
        list = new AccountDao(context).queryForAll();
        Collections.sort(list, new Comparator<AccountBean>() {
            @Override
            public int compare(AccountBean o1, AccountBean o2) {
                return o2.getId() - o1.getId();
            }
        });
        adapter = new ZhangDanAdapter();
        ob2_listview.setAdapter(adapter);
        ob2_listview.setEmptyView(ob2_tishi);
        return view;
    }
    private void initView(View view) {
        ob2_spinner = (Spinner) view.findViewById(R.id.ob2_spinner);
        ob2_btn_query = (Button) view.findViewById(R.id.ob2_btn_query);
        ob2_listview = (ListView) view.findViewById(R.id.ob2_listview);
        ob2_tishi = (TextView) view.findViewById(R.id.ob2_tishi);

        ob2_btn_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob2_btn_query:
                if (ob2_spinner.getSelectedItemId() == 0){
                    Collections.sort(list, new Comparator<AccountBean>() {
                        @Override
                        public int compare(AccountBean o1, AccountBean o2) {
                            return o1.getId() - o2.getId();
                        }
                    });
                }
                if (ob2_spinner.getSelectedItemId() == 1){
                    Collections.sort(list, new Comparator<AccountBean>() {
                        @Override
                        public int compare(AccountBean o1, AccountBean o2) {
                            return o2.getId() - o1.getId();
                        }
                    });
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }


    class ZhangDanAdapter extends BaseAdapter {
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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.ob2_list_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob2_lv_id.setText(position + 1 +"");
            viewHolder.ob2_lv_carid.setText(list.get(position).getCarid()+"");
            viewHolder.ob2_lv_balance.setText(list.get(position).getBalanc()+"");
            viewHolder.ob2_lv_user.setText(list.get(position).getUser()+"");
            viewHolder.ob2_lv_time.setText(list.get(position).getTime()+"");
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ob2_lv_id;
            public TextView ob2_lv_carid;
            public TextView ob2_lv_balance;
            public TextView ob2_lv_user;
            public TextView ob2_lv_time;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob2_lv_id = (TextView) rootView.findViewById(R.id.ob2_lv_id);
                this.ob2_lv_carid = (TextView) rootView.findViewById(R.id.ob2_lv_carid);
                this.ob2_lv_balance = (TextView) rootView.findViewById(R.id.ob2_lv_balance);
                this.ob2_lv_user = (TextView) rootView.findViewById(R.id.ob2_lv_user);
                this.ob2_lv_time = (TextView) rootView.findViewById(R.id.ob2_lv_time);
            }

        }
    }

}

