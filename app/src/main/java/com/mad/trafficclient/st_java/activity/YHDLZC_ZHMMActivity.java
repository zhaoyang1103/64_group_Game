package com.mad.trafficclient.st_java.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.YHDLZCBean;
import com.mad.trafficclient.st_java.dao.YHDLZCDao;

import java.util.ArrayList;

public class YHDLZC_ZHMMActivity extends Activity implements View.OnClickListener {

    private ImageView iv_back;
    private EditText et_user_name;
    private EditText et_email;
    private Button btn_find;
    private YHDLZCDao yhdlzcDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_yhdlzc_zhmm);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initData() {
        yhdlzcDao = new YHDLZCDao(YHDLZC_ZHMMActivity.this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_find = (Button) findViewById(R.id.btn_find);

        btn_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = et_user_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = et_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        ArrayList<YHDLZCBean> yhdlzcBeans = yhdlzcDao.queryAll();
        for (int i = 0; i < yhdlzcBeans.size(); i++) {
            YHDLZCBean yhdlzcBean = yhdlzcBeans.get(i);
            if (yhdlzcBean.getUserName().equals(name)) {
                if (yhdlzcBean.getEmail().equals(email)) {
                    Toast.makeText(this, "您的密码为" + yhdlzcBean.getUserPwd() + "请牢记。", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(this, "请核对你的邮箱", Toast.LENGTH_SHORT).show();
                    et_email.requestFocus();
                    return;
                }
            }
            break;
        }
        Toast.makeText(this, "'" + name + "'该用户还未注册", Toast.LENGTH_SHORT).show();
    }
}
