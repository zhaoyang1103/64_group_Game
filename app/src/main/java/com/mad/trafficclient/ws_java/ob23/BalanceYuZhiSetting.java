package com.mad.trafficclient.ws_java.ob23;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;

/**
 * Created by Go_Fight_Now on 2019/5/7 18:27
 */
public class BalanceYuZhiSetting extends Fragment implements View.OnClickListener {
    private TextView ob23_tv_yuzhi;
    private EditText ob23_edi_yuzhi;
    private Button ob23_btn_setting;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob23_main, container, false);
        initView(view);
        context = getContext();
        ob23_tv_yuzhi.setText("我的1-4号车账户余额告警阈值为 "+ BalanceUtil.getBalanceYuzhi(context) +" 元");
        return view;
    }

    private void initView(View view) {
        ob23_tv_yuzhi = (TextView) view.findViewById(R.id.ob23_tv_yuzhi);
        ob23_edi_yuzhi = (EditText) view.findViewById(R.id.ob23_edi_yuzhi);
        ob23_btn_setting = (Button) view.findViewById(R.id.ob23_btn_setting);

        ob23_btn_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ob23_btn_setting:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String yuzhi = ob23_edi_yuzhi.getText().toString().trim();
        if (TextUtils.isEmpty(yuzhi)) {
            Toast.makeText(getContext(), "阈值不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            BalanceUtil.saveBalanceYuzhi(context,Integer.parseInt(yuzhi));
            ob23_tv_yuzhi.setText("我的1-4号车账户余额告警阈值为 "+ BalanceUtil.getBalanceYuzhi(context) +" 元");
        }

        // TODO validate success, do something


    }
}
