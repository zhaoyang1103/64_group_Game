/**
 *
 */
package com.mad.trafficclient.zy_java.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.zy_java.util.UtilBean;
import com.mad.trafficclient.zy_java.util.ZyUtil;

import java.util.List;


public class Fragment_1_test extends Fragment {

    private TextView textview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout01, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        textview = (TextView) view.findViewById(R.id.textview);
        ZyUtil.saveList(getContext(), new UtilBean("小王", 10, "男"));
        ZyUtil.saveList(getContext(), new UtilBean("小李", 18, "女"));
        ZyUtil.saveList(getContext(), new UtilBean("小狗", 10, "男"));
        ZyUtil.deteleUser(getContext(), "小王");
        List<UtilBean> list = ZyUtil.getList(getContext());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append(list.get(i).toString());
        }
        textview.setText(stringBuffer.toString());
        Toast.makeText(getContext(), "" + stringBuffer.toString(), Toast.LENGTH_SHORT).show();

    }
}
