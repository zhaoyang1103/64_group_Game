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

public class YHDLZC_ZCActivity extends Activity implements View.OnClickListener {
    private ImageView iv_back;
    private EditText et_user_name;
    private EditText et_email;
    private EditText et_password;
    private EditText et_password_make;
    private Button btn_commit;
    private YHDLZCDao yhdlzcDao;
    private ArrayList<YHDLZCBean> yhdlzcBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_yhdlzc_zc);
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
        yhdlzcDao = new YHDLZCDao(YHDLZC_ZCActivity.this);
        yhdlzcBeans = yhdlzcDao.queryAll();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password_make = (EditText) findViewById(R.id.et_password_make);
        btn_commit = (Button) findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
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

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String make = et_password_make.getText().toString().trim();
        if (TextUtils.isEmpty(make)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        if (name.length() < 4) {
            Toast.makeText(this, "填写用户名（不少于4位字母）", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.length() < 6 || password.length() < 6) {
            Toast.makeText(this, "邮箱和用户密码（不少于6位数字）", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(make)) {
            Toast.makeText(this, "两次密码输入不符，请重新输入", Toast.LENGTH_SHORT).show();
            et_password.requestFocus();
            return;
        }

        for (int i = 0; i < yhdlzcBeans.size(); i++) {
            YHDLZCBean yhdlzcBean = yhdlzcBeans.get(i);
            if (yhdlzcBean.getUserName().equals(name)) {
                Toast.makeText(this, "该用户已被注册，请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        YHDLZCBean yhdlzcBean = new YHDLZCBean(0, name, password, email);
        yhdlzcDao.addItem(yhdlzcBean);
        Toast.makeText(this, "注册成功，请返回登录！！！", Toast.LENGTH_SHORT).show();
    }
}
