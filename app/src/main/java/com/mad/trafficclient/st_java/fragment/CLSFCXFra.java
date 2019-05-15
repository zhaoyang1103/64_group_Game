package com.mad.trafficclient.st_java.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.mad.trafficclient.R;

import java.util.Calendar;
import java.util.Date;

public class CLSFCXFra extends Fragment implements View.OnClickListener {
    private Spinner sp_start_year;
    private Spinner sp_start_month;
    private Spinner sp_start_day;
    private Spinner sp_end_year;
    private Spinner start_end_month;
    private Spinner start_end_day;
    private ListView listView;
    private Button bt_query;
    private Button bt_exit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_clsfcx, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {

    }

    private void initData() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
    }

    private void initView(View view) {
        sp_start_year = (Spinner) view.findViewById(R.id.sp_start_year);
        sp_start_month = (Spinner) view.findViewById(R.id.sp_start_month);
        sp_start_day = (Spinner) view.findViewById(R.id.sp_start_day);
        sp_end_year = (Spinner) view.findViewById(R.id.sp_end_year);
        start_end_month = (Spinner) view.findViewById(R.id.start_end_month);
        start_end_day = (Spinner) view.findViewById(R.id.start_end_day);
        listView = (ListView) view.findViewById(R.id.listView);
        bt_query = (Button) view.findViewById(R.id.bt_query);
        bt_exit = (Button) view.findViewById(R.id.bt_exit);

        bt_query.setOnClickListener(this);
        bt_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_query:

                break;
            case R.id.bt_exit:

                break;
        }
    }
}
