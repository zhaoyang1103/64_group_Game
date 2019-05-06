package com.mad.trafficclient.ws_java.ob7;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;
import com.mad.trafficclient.ws_java.ob5.SenseUtil;

/**
 * Created by Go_Fight_Now on 2019/5/6 19:03
 */
public class YuZhiSetting extends Fragment {
    private Switch ob7_switch;
    private EditText ob7_edi_wendu;
    private EditText ob7_edi_shidu;
    private EditText ob7_edi_guang;
    private EditText ob7_edi_co2;
    private EditText ob7_edi_pm25;
    private EditText ob7_edi_status;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob7_main, container, false);
        context = getContext();
        initView(view);
        ob7_switch.setChecked(SenseUtil.getSwitch(context));
        if (ob7_switch.isChecked()){
            ob7_edi_wendu.setEnabled(false);
            ob7_edi_shidu.setEnabled(false);
            ob7_edi_guang.setEnabled(false);
            ob7_edi_co2.setEnabled(false);
            ob7_edi_pm25.setEnabled(false);
            ob7_edi_status.setEnabled(false);
        } else {
            ob7_edi_wendu.setEnabled(true);
            ob7_edi_shidu.setEnabled(true);
            ob7_edi_guang.setEnabled(true);
            ob7_edi_co2.setEnabled(true);
            ob7_edi_pm25.setEnabled(true);
            ob7_edi_status.setEnabled(true);
        }
        ob7_edi_wendu.setText(SenseUtil.getYuzhi(context,"wendu")+"");
        ob7_edi_shidu.setText(SenseUtil.getYuzhi(context,"shidu")+"");
        ob7_edi_guang.setText(SenseUtil.getYuzhi(context,"guang")+"");
        ob7_edi_co2.setText(SenseUtil.getYuzhi(context,"co2")+"");
        ob7_edi_pm25.setText(SenseUtil.getYuzhi(context,"pm25")+"");
        ob7_edi_status.setText(SenseUtil.getYuzhi(context,"status")+"");
        return view;
    }

    private void initView(View view) {
        ob7_switch = (Switch) view.findViewById(R.id.ob7_switch);
        ob7_edi_wendu = (EditText) view.findViewById(R.id.ob7_edi_wendu);
        ob7_edi_shidu = (EditText) view.findViewById(R.id.ob7_edi_shidu);
        ob7_edi_guang = (EditText) view.findViewById(R.id.ob7_edi_guang);
        ob7_edi_co2 = (EditText) view.findViewById(R.id.ob7_edi_co2);
        ob7_edi_pm25 = (EditText) view.findViewById(R.id.ob7_edi_pm25);
        ob7_edi_status = (EditText) view.findViewById(R.id.ob7_edi_status);

        ob7_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                if (ob7_switch.isChecked()){
                    ob7_edi_wendu.setEnabled(false);
                    ob7_edi_shidu.setEnabled(false);
                    ob7_edi_guang.setEnabled(false);
                    ob7_edi_co2.setEnabled(false);
                    ob7_edi_pm25.setEnabled(false);
                    ob7_edi_status.setEnabled(false);
                } else {
                    ob7_edi_wendu.setEnabled(true);
                    ob7_edi_shidu.setEnabled(true);
                    ob7_edi_guang.setEnabled(true);
                    ob7_edi_co2.setEnabled(true);
                    ob7_edi_pm25.setEnabled(true);
                    ob7_edi_status.setEnabled(true);
                }
                SenseUtil.saveSwitch(context,ob7_switch.isChecked());
            }
        });
    }

    private void submit() {
        // validate
        String wendu = ob7_edi_wendu.getText().toString().trim();
        if (TextUtils.isEmpty(wendu)) {
            Toast.makeText(getContext(), "温度不能为空", Toast.LENGTH_SHORT).show();
            ob7_switch.setChecked(false);
            return;
        }

        String shidu = ob7_edi_shidu.getText().toString().trim();
        if (TextUtils.isEmpty(shidu)) {
            Toast.makeText(getContext(), "湿度不能为空", Toast.LENGTH_SHORT).show();
            ob7_switch.setChecked(false);
            return;
        }

        String guang = ob7_edi_guang.getText().toString().trim();
        if (TextUtils.isEmpty(guang)) {
            Toast.makeText(getContext(), "光照不能为空", Toast.LENGTH_SHORT).show();
            ob7_switch.setChecked(false);
            return;
        }

        String co2 = ob7_edi_co2.getText().toString().trim();
        if (TextUtils.isEmpty(co2)) {
            Toast.makeText(getContext(), "Co2不能为空", Toast.LENGTH_SHORT).show();
            ob7_switch.setChecked(false);
            return;
        }

        String pm25 = ob7_edi_pm25.getText().toString().trim();
        if (TextUtils.isEmpty(pm25)) {
            Toast.makeText(getContext(), "PM2.5不能为空", Toast.LENGTH_SHORT).show();
            ob7_switch.setChecked(false);
            return;
        }

        String status = ob7_edi_status.getText().toString().trim();
        if (TextUtils.isEmpty(status)) {
            Toast.makeText(getContext(), "道路状况不能为空", Toast.LENGTH_SHORT).show();
            ob7_switch.setChecked(false);
            return;
        } else {
            SenseUtil.saveYuzhi(context,Integer.parseInt(wendu),
                    Integer.parseInt(shidu),
                    Integer.parseInt(guang),
                    Integer.parseInt(co2),
                    Integer.parseInt(pm25),
                    Integer.parseInt(status));
        }

        // TODO validate success, do something


    }
}
