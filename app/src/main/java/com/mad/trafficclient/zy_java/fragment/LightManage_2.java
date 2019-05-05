/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.mad.trafficclient.R;


public class LightManage_2 extends Fragment implements View.OnClickListener {

    private Spinner spinner;
    private Button bt_querylight;
    private ListView lv_light;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.lightmanage_2, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner);
        bt_querylight = (Button) view.findViewById(R.id.bt_querylight);
        lv_light = (ListView) view.findViewById(R.id.lv_light);
        bt_querylight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_querylight:
                break;
        }
    }
}
