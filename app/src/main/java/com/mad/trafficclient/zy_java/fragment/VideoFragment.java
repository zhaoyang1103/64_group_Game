/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.acticity.Zy_VideoPlayActivity;


public class VideoFragment extends Fragment implements View.OnClickListener {

    private TextView tx_video;
    private TextView tx_Image;
    private GridView gv_video_list;
    private int[] ints;
    private Context context;
    private GvVideoAdapter adapter;
    private boolean flag = true;
    private String[] videoints;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.videofragment, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        videoints = new String[]{"car1", "car2", "car3", "car4", "traffic", "bwm"};

        ints = new int[]{R.drawable.weizhang1, R.drawable.weizhang1, R.drawable.weizhang1, R.drawable.weizhang1, R.drawable.weizhang1, R.drawable.weizhang1};
        context = getContext();
        tx_video = (TextView) view.findViewById(R.id.tx_video);
        tx_Image = (TextView) view.findViewById(R.id.tx_Image);
        gv_video_list = (GridView) view.findViewById(R.id.gv_video_list);
        tx_Image.setOnClickListener(this);
        tx_video.setOnClickListener(this);
        adapter = new GvVideoAdapter();
        gv_video_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_video:
                tx_video.setBackgroundResource(R.drawable.video_on);
                tx_Image.setBackgroundResource(R.drawable.video_off);
                ints = new int[]{R.drawable.video_icon, R.drawable.video_icon, R.drawable.video_icon, R.drawable.video_icon, R.drawable.video_icon, R.drawable.video_icon};
                adapter.notifyDataSetChanged();
                flag = true;
                break;
            case R.id.tx_Image:
                tx_video.setBackgroundResource(R.drawable.video_off);
                tx_Image.setBackgroundResource(R.drawable.video_on);
                ints = new int[]{R.drawable.weizhang1, R.drawable.weizhang2, R.drawable.weizhang3, R.drawable.weizhang4};
                adapter.notifyDataSetChanged();
                flag = false;
                break;
        }
    }

    class GvVideoAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return ints.length;
        }

        @Override
        public Object getItem(int position) {
            return ints[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.item_video, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.tupian.setImageResource(ints[position]);
            if (flag) {
                viewHolder.tx_name.setText(videoints[position]);
            } else {
                viewHolder.tx_name.setText("");

            }

            viewHolder.tupian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag) {
                        Intent intent = new Intent(context, Zy_VideoPlayActivity.class);
                        intent.putExtra("videoPosition", position);
                        intent.putExtra("video", flag);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, Zy_VideoPlayActivity.class);
                        intent.putExtra("videoPosition", position);
                        intent.putExtra("video", flag);
                        startActivity(intent);
                    }
                }
            });


            return convertView;
        }

        public
        class ViewHolder {
            public View rootView;
            public ImageView tupian;
            public TextView tx_name;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tupian = (ImageView) rootView.findViewById(R.id.tupian);
                this.tx_name = (TextView) rootView.findViewById(R.id.tx_name);
            }

        }
    }
}
