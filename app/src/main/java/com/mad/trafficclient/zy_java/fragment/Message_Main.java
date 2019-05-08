/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.view.Left_Message;
import com.mad.trafficclient.zy_java.view.Right_message;


public class Message_Main extends Fragment implements View.OnClickListener {

    private RelativeLayout fragment_test;
    private TextView message_query;
    private TextView message_fenxi;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.my_message, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        fragment_test = (RelativeLayout) view.findViewById(R.id.fragment_test);
        message_query = (TextView) view.findViewById(R.id.message_query);
        message_fenxi = (TextView) view.findViewById(R.id.message_fenxi);
        message_query.setOnClickListener(this);
        message_fenxi.setOnClickListener(this);
        message_query.setBackgroundResource(R.drawable.back_mymessage_2);
        message_fenxi.setBackgroundResource(R.drawable.back_mymessage);
        getFragmentManager().beginTransaction().replace(R.id.fragment_test, new Left_Message()).commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_query:
                getFragmentManager().beginTransaction().replace(R.id.fragment_test, new Left_Message()).commit();
                message_query.setBackgroundResource(R.drawable.back_mymessage_2);
                message_fenxi.setBackgroundResource(R.drawable.back_mymessage);
                break;
            case R.id.message_fenxi:
                getFragmentManager().beginTransaction().replace(R.id.fragment_test, new Right_message()).commit();
                message_query.setBackgroundResource(R.drawable.back_mymessage);
                message_fenxi.setBackgroundResource(R.drawable.back_mymessage_2);
                break;
        }
    }
}
