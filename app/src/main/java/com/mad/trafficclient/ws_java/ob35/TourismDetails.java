package com.mad.trafficclient.ws_java.ob35;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.ws_java.ob9.Acc_Util;

/**
 * Created by Go_Fight_Now on 2019/5/9 11:22
 */
public class TourismDetails extends Fragment {

    private static TourismDetails fragment;
    private ImageView ob35_details;
    private Dianping_yuan ob35_dianping;
    private TextView ob35_phonenumber;
    private TextView ob35_details_text;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob35_details, container, false);
        initView(view);
        context = getContext();
        ob35_details.setImageResource(Acc_Util.getImage(fragment.getArguments().getString("photo")));
        ob35_dianping.showxing(context,fragment.getArguments().getInt("dianping"));
        ob35_phonenumber.setText(fragment.getArguments().getString("phonenumber"));
        ob35_details_text.setText(fragment.getArguments().getString("details_text"));
        return view;
    }

    public static TourismDetails newInstance(Bundle args) {
        fragment = new TourismDetails();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        ob35_details = (ImageView) view.findViewById(R.id.ob35_details);
        ob35_dianping = (Dianping_yuan) view.findViewById(R.id.ob35_dianping);
        ob35_phonenumber = (TextView) view.findViewById(R.id.ob35_phonenumber);
        ob35_details_text = (TextView) view.findViewById(R.id.ob35_details_text);
    }
}
