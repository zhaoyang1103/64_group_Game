package com.mad.trafficclient.ws_java.ob63;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.mad.trafficclient.ws_java.ob35.Dianping_yuan;
import com.mad.trafficclient.ws_java.ob9.Acc_Util;

/**
 * Created by Go_Fight_Now on 2019/5/10 21:09
 */
public class TravelAssistant_Details extends Fragment {

    private static TravelAssistant_Details fragment;
    private ImageView imageView_Sliding;
    private ImageView ob63_details;
    private TextView ob63_details_text;
    private Dianping_yuan ob63_dianping;
    private TextView ob63_phonenumber;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob63_details, container, false);
        initView(view);
        context = getContext();
        getActivity().findViewById(R.id.top_title).setVisibility(View.GONE);
        ob63_details.setImageResource(Acc_Util.getImage(fragment.getArguments().getString("photo")));
        ob63_dianping.showxing(context,fragment.getArguments().getInt("dianping"));
        ob63_phonenumber.setText(fragment.getArguments().getString("phonenumber"));
        ob63_details_text.setText(fragment.getArguments().getString("details_text"));
        return view;
    }

    public static TravelAssistant_Details newInstance(Bundle args) {
        fragment = new TravelAssistant_Details();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        ob63_details = (ImageView) view.findViewById(R.id.ob63_details);
        ob63_details_text = (TextView) view.findViewById(R.id.ob63_details_text);
        ob63_dianping = (Dianping_yuan) view.findViewById(R.id.ob63_dianping);
        ob63_phonenumber = (TextView) view.findViewById(R.id.ob63_phonenumber);

        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.top_title).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });

        ob63_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + ob63_phonenumber.getText().toString().trim());
                intent.setData(data);
                context.startActivity(intent);
            }
        });
    }
}
