package com.mad.trafficclient.ws_java.ob9;


import com.mad.trafficclient.R;

import java.lang.reflect.Field;

/**
 * Created by Go_Fight_Now on 2019/5/6 20:56
 */
public class Acc_Util {
    public static int getImage(String chebiao){
        try {
            Field field = R.drawable.class.getField(chebiao);
            return field.getInt(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
