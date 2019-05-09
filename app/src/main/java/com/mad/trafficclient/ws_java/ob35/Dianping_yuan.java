package com.mad.trafficclient.ws_java.ob35;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mad.trafficclient.R;

/**
 * Created by Go_Fight_Now on 2019/5/9 10:47
 */
public class Dianping_yuan extends LinearLayout {
    public Dianping_yuan(Context context) {
        super(context);
    }

    public Dianping_yuan(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showxing(Context context, int a) {
        removeAllViews();
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(context);

            if (i < a) {
                imageView.setImageResource(R.drawable.ws_yuan_on);
            } else {
                imageView.setImageResource(R.drawable.yuan_off);
            }

            addView(imageView);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            imageView.setLayoutParams(layoutParams);

        }


    }
}
