package com.mad.trafficclient.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.MainActivity;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.LoadingDialog;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity {

    private UrlBean urlBean;
    private String urlHttp;
    private String urlPort = "8080";

    EditText etUserName, etUserPwd;
    Button btLogin, btReg, btNetSetting;
    private CheckBox jzpwdCB;
    private CheckBox autologCB;
    private SharedPreferences st_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        initLiserter();
    }

    private void initData() {
        st_sp = getSharedPreferences("st_sp", MODE_PRIVATE);
        if (st_sp.getBoolean("jzpwdCB", false)) {
            jzpwdCB.setChecked(true);
            etUserName.setText(st_sp.getString("etUserName", ""));
            etUserPwd.setText(st_sp.getString("etUserPwd", ""));
        }
        if (st_sp.getBoolean("autologCB", false)) {
            autologCB.setChecked(true);
            login();

        }
    }

    private void initView() {
        // TODO Auto-generated method stub
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etUserPwd = (EditText) findViewById(R.id.et_user_pwd);
        btLogin = (Button) findViewById(R.id.bt_login);
        btReg = (Button) findViewById(R.id.bt_reg);
        btNetSetting = (Button) findViewById(R.id.bt_net_setting);

        urlBean = Util.loadSetting(LoginActivity.this);

        jzpwdCB = (CheckBox) findViewById(R.id.jzpwdCB);
        autologCB = (CheckBox) findViewById(R.id.autologCB);
    }

    private void initLiserter() {
        // TODO Auto-generated method stub
        btReg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(LoginActivity.this,
                        RegActivity.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                login();
            }
        });

        btNetSetting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog urlSettingDialog = new Dialog(LoginActivity.this);
                urlSettingDialog.show();
                urlSettingDialog.setTitle("Setting");
                urlSettingDialog.getWindow().setContentView(R.layout.login_setting);
                final EditText edit_urlHttp = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_url);
                final EditText edit_urlPort = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_port);

                edit_urlHttp.setText(urlBean.getUrl());
                edit_urlPort.setText(urlBean.getPort());

                edit_urlPort.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String string = s.toString();
                        if (!string.equals("")) {
                            if (Integer.parseInt(string) < 1 || Integer.parseInt(string) > 10000) {
                                s.clear();
                            }
                        }
                    }
                });
                Button save = (Button) urlSettingDialog.getWindow().findViewById(R.id.save);
                Button cancel = (Button) urlSettingDialog.getWindow().findViewById(R.id.cancel);
                save.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        urlHttp = edit_urlHttp.getText().toString();
                        urlPort = edit_urlPort.getText().toString();

                        if (urlHttp == null || urlHttp.equals("")) {
                            Toast.makeText(LoginActivity.this, R.string.error_ip, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this, urlHttp + "", Toast.LENGTH_SHORT).show();
                            Util.saveSetting(urlHttp, urlPort, LoginActivity.this);
                            urlBean = Util.loadSetting(LoginActivity.this);
                            urlSettingDialog.dismiss();
                        }
                    }
                });
                cancel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        urlSettingDialog.dismiss();
                    }
                });
                urlSettingDialog.show();
            }
        });

        jzpwdCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                st_sp.edit().putBoolean("jzpwdCB", isChecked).commit();

            }
        });
        autologCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                st_sp.edit().putBoolean("autologCB", isChecked).commit();
            }
        });
    }

    private void login() {
        final String userName = etUserName.getText().toString();
        final String userPwd = etUserPwd.getText().toString();

        LoadingDialog.showDialog(LoginActivity.this);
        JSONObject params = new JSONObject();
        try {
            params.put("UserName", userName);
            params.put("UserPwd", userPwd);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("TAG", params.toString());


        String strUrl = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/user_login";


        Log.d("TAG", strUrl);

        RequestQueue mQueue = Volley.newRequestQueue(LoginActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, strUrl, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stu
                Log.d("TAG", response.toString());
                LoadingDialog.disDialog();
                if (response.optString("RESULT").equals("S")) {
                    st_sp.edit().putString("etUserName", userName).putString("etUserPwd", userPwd).commit();
                    Util.setUserName(LoginActivity.this, userName.trim());
                    Util.setUserRole(LoginActivity.this, response.optString("UserRole"));
                    Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (response.optString("RESULT").equals("F")) {
                    Toast.makeText(getApplicationContext(), response.optString("ERRMSG"), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                LoadingDialog.disDialog();
                Log.d("TAG volley error", error.toString());
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        mQueue.add(jsonObjectRequest);
    }

}
