package com.mad.trafficclient.st_java.fragment;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.UrlBean;
import com.mad.trafficclient.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeedbackFra extends Fragment implements View.OnClickListener {
    private ImageView imageView_Sliding;
    private TextView tv_title;
    private TextView tv_other;
    private EditText title;
    private EditText feedback;
    private EditText phone;
    private Button submit;
    private Context context;
    private RequestQueue requestQueue;
    private UrlBean urlBean;
    private String userName;

    public interface Open {
        void open();
    }

    public Open open;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_feedback, container, false);
        open = (Open) getContext();
        initView(view);

        initData();
        initListener();
        return view;
    }

    private void initListener() {
        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.open();
            }
        });
        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("MyOpinionFra").replace(R.id.maincontent, new MyOpinionFra()).commit();
            }
        });
    }

    private void initData() {
        tv_title.setText("意见反馈");
        tv_other.setText("我的意见");
        context = getContext();
        urlBean = Util.loadSetting(context);
        userName = Util.getUserName(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_other = (TextView) view.findViewById(R.id.tv_other);
        title = (EditText) view.findViewById(R.id.title);
        feedback = (EditText) view.findViewById(R.id.feedback);
        phone = (EditText) view.findViewById(R.id.phone);
        submit = (Button) view.findViewById(R.id.submit);

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                //http://localhost:8080/api/v2/set_suggestion
                submit();

                break;
        }
    }

    private void submit() {
        // validate
        String titleString = title.getText().toString().trim();
        if (TextUtils.isEmpty(titleString)) {
            Toast.makeText(getContext(), "标题", Toast.LENGTH_SHORT).show();
            return;
        }

        String feedbackString = feedback.getText().toString().trim();
        if (TextUtils.isEmpty(feedbackString)) {
            Toast.makeText(getContext(), "请输入您的意见", Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneString = phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            Toast.makeText(getContext(), "手机", Toast.LENGTH_SHORT).show();
            return;
        }
        String regex = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(phoneString);
        if (!matcher.find()) {
            Toast.makeText(context, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            phone.requestFocus();
            return;
        } else {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("title", titleString);
                jsonObject.put("content", feedbackString);
                jsonObject.put("phone_number", phoneString);
                jsonObject.put("UserName", userName);
            } catch (JSONException e) {

            }
            requestQueue.add(new JsonObjectRequest(Request.Method.POST, "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/api/v2/set_suggestion", jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        Toast.makeText(context, jsonObject.optString("ERRMSG"), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }));
        }

        // TODO validate success, do something


    }
}
