package com.mad.trafficclient.ws_java.ob32;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Util;

import org.json.JSONObject;

/**
 * Created by Go_Fight_Now on 2019/5/7 19:01
 */
public class SubwayDetails extends Fragment{
    private ImageView imageView_Sliding;
    private TextView tv_title;
    private ImageView details_image;
    private static SubwayDetails fragment;
    private Context context;
    private Matrix matrix;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ob32_details, container, false);
        initView(view);
        context = getContext();
        tv_title.setText(fragment.getArguments().getString("title"));
        matrix = new Matrix();
        setListener();
        setImage();
        return view;
    }

    public static SubwayDetails newInstance(Bundle args) {
        fragment = new SubwayDetails();
        fragment.setArguments(args);
        return fragment;
    }

    public void setImage(){
        String URL = "http://" + Util.loadSetting(context).getUrl() + ":" + Util.loadSetting(context).getPort() + "/api/image/" + fragment.getArguments().getString("map");
        Log.i("Go_Fight_Now 提醒您", "URL" + ":" + URL);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                details_image.setImageBitmap(bitmap);
                matrix = details_image.getImageMatrix();
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "图片获取失败", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void setListener(){
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                matrix.preTranslate(-distanceX/2f,-distanceY/2f);
                details_image.setImageMatrix(matrix);
                details_image.invalidate();
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });

        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scaleFactor = detector.getScaleFactor();
                float focusX = detector.getFocusX();
                float focusY = detector.getFocusY();
                if (scaleFactor >1){
                    scaleFactor = 1.05f;
                } else {
                    scaleFactor = 0.95f;
                }
                matrix.preScale(scaleFactor,scaleFactor,focusX,focusY);
                details_image.setImageMatrix(matrix);
                details_image.invalidate();
                return super.onScale(detector);
            }
        });
    }

    private void initView(View view) {
        imageView_Sliding = (ImageView) view.findViewById(R.id.imageView_Sliding);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        details_image = (ImageView) view.findViewById(R.id.details_image);

        imageView_Sliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        details_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                details_image.setScaleType(ImageView.ScaleType.MATRIX);
                gestureDetector.onTouchEvent(event);
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }


}
