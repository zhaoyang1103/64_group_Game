package com.mad.trafficclient.st_java.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.MainActivity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.st_java.bean.YHDLZCBean;
import com.mad.trafficclient.st_java.dao.YHDLZCDao;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YHDLZCActivity extends Activity implements View.OnClickListener {
    private ImageView iv_setting;
    private EditText et_user_name;
    private EditText et_password;
    private TextView tv_new_user_name;
    private TextView tv_find_password;
    private Button btn_login;
    private RequestQueue requestQueue;
    private SharedPreferences yhdlzc;
    private YHDLZCDao yhdlzcDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_yhdlzc);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(YHDLZCActivity.this);
                View inflate = View.inflate(YHDLZCActivity.this, R.layout.dialog_yhdlzc, null);
                final DialogViewHolder dialogViewHolder = new DialogViewHolder(inflate);
                dialogViewHolder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialogViewHolder.btn_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Editable text = dialogViewHolder.et_ip.getText();
                        Editable text1 = dialogViewHolder.et_port.getText();
                        if (text.toString().equals("") || text1.toString().equals("")) {
                            Toast.makeText(YHDLZCActivity.this, "请正确输入IP和端口号", Toast.LENGTH_SHORT).show();
                        } else {
                            Util.saveSetting(text.toString(), text1.toString(), YHDLZCActivity.this);
                            Toast.makeText(YHDLZCActivity.this, text.toString() + ":" + text1.toString(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.setContentView(inflate);
                dialog.show();
            }
        });
        tv_new_user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YHDLZCActivity.this, YHDLZC_ZCActivity.class));
            }
        });
        tv_find_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YHDLZCActivity.this, YHDLZC_ZHMMActivity.class));
            }
        });
    }

    private void initData() {
        requestQueue = Volley.newRequestQueue(YHDLZCActivity.this);
        yhdlzc = getSharedPreferences("yhdlzc", MODE_PRIVATE);
        yhdlzcDao = new YHDLZCDao(YHDLZCActivity.this);
        if (yhdlzc.getBoolean("State", false)) {
            et_user_name.setText(yhdlzc.getString("UserName", ""));
            et_password.setText(yhdlzc.getString("UserPwd", ""));
            submit();
        }
    }

    private void initView() {
        iv_setting = (ImageView) findViewById(R.id.iv_setting);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_new_user_name = (TextView) findViewById(R.id.tv_new_user_name);
        tv_find_password = (TextView) findViewById(R.id.tv_find_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        final String name = et_user_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        final String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", name);
            jsonObject.put("UserPwd", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://47.94.218.85:8080/api/v2/user_login", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (jsonObject.optString("RESULT").equals("S")) {
                    yhdlzc.edit().putString("UserName", name).putString("UserPwd", password).putBoolean("State", true).commit();
                    Util.setUserName(YHDLZCActivity.this, name);
                    startActivity(new Intent(YHDLZCActivity.this, MainActivity.class));
                    finish();
                } else if (loginByLocaltion(name, password)) {
                    yhdlzc.edit().putString("UserName", name).putString("UserPwd", password).putBoolean("State", true).commit();
                    startActivity(new Intent(YHDLZCActivity.this, MainActivity.class));
                    finish();
                } else {
                    yhdlzc.edit().putBoolean("State", false).commit();
                    Toast.makeText(YHDLZCActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                yhdlzc.edit().putBoolean("State", false).commit();
                Toast.makeText(YHDLZCActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private boolean loginByLocaltion(String name, String password) {
        ArrayList<YHDLZCBean> yhdlzcBeans = yhdlzcDao.queryAll();
        for (int i = 0; i < yhdlzcBeans.size(); i++) {
            YHDLZCBean yhdlzcBean = yhdlzcBeans.get(i);
            if (name.equals(yhdlzcBean.getUserName())) {
                if (password.equals(yhdlzcBean.getUserPwd())) {
                    return true;
                }
            }
            break;
        }
        return false;
    }

    public class DialogViewHolder {
        public View rootView;
        public EditText et_ip;
        public EditText et_port;
        public Button btn_cancel;
        public Button btn_sure;

        public DialogViewHolder(View rootView) {
            this.rootView = rootView;
            this.et_ip = (EditText) rootView.findViewById(R.id.et_ip);
            this.et_port = (EditText) rootView.findViewById(R.id.et_port);
            this.btn_cancel = (Button) rootView.findViewById(R.id.btn_cancel);
            this.btn_sure = (Button) rootView.findViewById(R.id.btn_sure);
        }

    }
}
