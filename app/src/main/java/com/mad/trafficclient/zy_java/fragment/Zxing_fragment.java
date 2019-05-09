/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.view.Zxing_fragment_2;


public class Zxing_fragment extends Fragment implements View.OnClickListener {

    private Spinner spinner_zxing;
    private EditText edit_monmey;
    private EditText edit_time;
    private Button bt_live_zxing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.zxing_qcode_1
                        , container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        spinner_zxing = (Spinner) view.findViewById(R.id.spinner_zxing);
        edit_monmey = (EditText) view.findViewById(R.id.edit_monmey);
        edit_time = (EditText) view.findViewById(R.id.edit_time);
        bt_live_zxing = (Button) view.findViewById(R.id.bt_live_zxing);

        bt_live_zxing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_live_zxing:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String monmey = edit_monmey.getText().toString().trim();
        if (TextUtils.isEmpty(monmey)) {
            Toast.makeText(getContext(), "monmey不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String time = edit_time.getText().toString().trim();
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(getContext(), "time不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            int carid = Integer.parseInt(spinner_zxing.getSelectedItem().toString());
            int money = Integer.parseInt(edit_monmey.getText().toString());
            int pclife = Integer.parseInt(edit_monmey.getText().toString());
            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.maincontent, Zxing_fragment_2.getInstance(carid,money,pclife)).commit();

        }

        // TODO validate success, do something


    }
}
