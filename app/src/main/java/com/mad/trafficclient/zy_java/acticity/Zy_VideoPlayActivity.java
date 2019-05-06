package com.mad.trafficclient.zy_java.acticity;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.mad.trafficclient.R;

public class Zy_VideoPlayActivity extends Activity {

    private VideoView video_play;
    private ImageView image_show;
    private Matrix matrix;
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zy_videoplay_activity);
        initView();
        initListener();
    }

    private void initListener() {
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                matrix.preTranslate(-distanceX / 2f, -distanceY / 2f);
                image_show.setImageMatrix(matrix);
                image_show.invalidate();
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scaleFactor = detector.getScaleFactor();
                float focusX = detector.getFocusX();
                float focusY = detector.getFocusY();
                if (scaleFactor > 1) {
                    scaleFactor = 1.05f;
                } else {
                    scaleFactor = 0.95f;
                }
                matrix.preScale(scaleFactor, scaleFactor, focusX, focusY);
                image_show.setImageMatrix(matrix);
                image_show.invalidate();
                return super.onScale(detector);
            }
        });
    }

    private void initView() {
        video_play = (VideoView) findViewById(R.id.video_play);
        image_show = (ImageView) findViewById(R.id.image_show);
        String[] videoints = new String[]{"car1", "car2", "car3", "car4", "traffic", "bwm"};

        int[] imageints = new int[]{R.drawable.weizhang1, R.drawable.weizhang2, R.drawable.weizhang3, R.drawable.weizhang4};

        if (getIntent().getBooleanExtra("video", false)) {

            image_show.setVisibility(View.GONE);
            video_play.setVisibility(View.VISIBLE);
            int postion = getIntent().getIntExtra("videoPosition", 0);
            video_play.setVideoPath("android.resource://" + getPackageName() + "/raw/" + videoints[postion]);
            video_play.start();


        } else {
            image_show.setVisibility(View.VISIBLE);
            video_play.setVisibility(View.GONE);
            int postion = getIntent().getIntExtra("videoPosition", 0);
            image_show.setImageResource(imageints[postion]);
            matrix = image_show.getImageMatrix();
        }

        image_show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                image_show.setScaleType(ImageView.ScaleType.MATRIX);
                gestureDetector.onTouchEvent(event);
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
