package com.mad.trafficclient.ws_java.ob23;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Go_Fight_Now on 2019/5/7 18:38
 */
public class BalanceUtil {
    public static void saveBalanceYuzhi(Context context , int yuzhi){
        SharedPreferences sharedPreferences = context.getSharedPreferences("balanceYuzhi",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("yuzhi",yuzhi);
        editor.commit();
    }

    public static int getBalanceYuzhi(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("balanceYuzhi",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("yuzhi",50);
    }
}
