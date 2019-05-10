package com.mad.trafficclient.ws_java.ob60;


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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/9 21:30
 */
public class Subscribe_Main extends Fragment {
    private Context context;
    private GridView ob60_toSubscribe;
    private GridView ob60_toAdd;

    private List<String> subscribeList;
    private List<String> addList;
    private SubscribeAdapter subscribeAdapter;
    private AddAdapter addAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob60_main, container, false);
        initView(view);
        context = getContext();
        subscribeList = new ArrayList<>();
        addList = new ArrayList<>();
        addList.add("推荐");
        addList.add("安全驾驶");
        addList.add("交通分类");
        addList.add("科技类");
        addList.add("路况类");
        addList.add("汽车类");
        addList.add("二手车类");
        addList.add("改装车");
        addList.add("违章");
        subscribeAdapter = new SubscribeAdapter();
        addAdapter = new AddAdapter();
        ob60_toSubscribe.setAdapter(subscribeAdapter);
        ob60_toAdd.setAdapter(addAdapter);
        subscribeAdapter.notifyDataSetChanged();
        addAdapter.notifyDataSetChanged();
        return view;
    }

    private void initView(View view) {
        ob60_toSubscribe = (GridView) view.findViewById(R.id.ob60_toSubscribe);
        ob60_toAdd = (GridView) view.findViewById(R.id.ob60_toAdd);
    }


    class SubscribeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return subscribeList.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ob60_subscribe_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob60_subscribe_text.setText(subscribeList.get(position));
            viewHolder.ob60_grid_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addList.add(subscribeList.get(position));
                    subscribeList.remove(position);
                    subscribeAdapter.notifyDataSetChanged();
                    addAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ob60_subscribe_text;
            public ImageView ob60_grid_remove;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob60_subscribe_text = (TextView) rootView.findViewById(R.id.ob60_subscribe_text);
                this.ob60_grid_remove = (ImageView) rootView.findViewById(R.id.ob60_grid_remove);
            }

        }
    }

    class AddAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return addList.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ob60_add_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.ob60_add_text.setText(addList.get(position));
            viewHolder.ob60_grid_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subscribeList.add(addList.get(position));
                    addList.remove(position);
                    addAdapter.notifyDataSetChanged();
                    subscribeAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            public View rootView;
            public TextView ob60_add_text;
            public ImageView ob60_grid_add;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.ob60_add_text = (TextView) rootView.findViewById(R.id.ob60_add_text);
                this.ob60_grid_add = (ImageView) rootView.findViewById(R.id.ob60_grid_add);
            }

        }
    }
}
