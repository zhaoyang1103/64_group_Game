package com.mad.trafficclient.login;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.trafficclient.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegActivity extends Activity implements View.OnClickListener {


    private EditText et_user_name;
    private EditText et_user_pwd;
    private EditText et_user_pwd_second;
    private EditText et_user_phone;
    private Button bt_cancel;
    private Button bt_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initListener();
    }

    private void initListener() {
        et_user_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (s1 != null && !s1.equals("")) {
                    if (s1.length() > 0 && s1.length() < 12) {

                    } else {
                        s.clear();
                    }
                }

            }
        });
    }

    private void initView() {
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_user_pwd = (EditText) findViewById(R.id.et_user_pwd);
        et_user_pwd_second = (EditText) findViewById(R.id.et_user_pwd_second);
        et_user_phone = (EditText) findViewById(R.id.et_user_phone);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
        bt_reg = (Button) findViewById(R.id.bt_reg);

        bt_cancel.setOnClickListener(this);
        bt_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cancel:
                finish();
                break;
            case R.id.bt_reg:
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

        String pwd = et_user_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String second = et_user_pwd_second.getText().toString().trim();
        if (TextUtils.isEmpty(second)) {
            Toast.makeText(this, "second不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = et_user_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        String regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(phone);
        if (!matcher.find()) {
            Toast.makeText(this, "请正确输入手机号码", Toast.LENGTH_SHORT).show();
            et_user_phone.requestFocus();
            return;
        }


        Toast.makeText(this, "你仿佛注册成功了", Toast.LENGTH_SHORT).show();


    }
}
