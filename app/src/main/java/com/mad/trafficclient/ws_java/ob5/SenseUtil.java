package com.mad.trafficclient.ws_java.ob5;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Go_Fight_Now on 2019/5/6 17:01
 */
public class SenseUtil {
    public static void saveYuzhi(Context context,int wendu ,int shidu ,int guang ,int co2 , int pm25 ,int status){
        SharedPreferences sharedPreferences = context.getSharedPreferences("yuzhi",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("wendu",wendu);
        editor.putInt("shidu",shidu);
        editor.putInt("guang",guang);
        editor.putInt("co2",co2);
        editor.putInt("pm25",pm25);
        editor.putInt("status",status);
        editor.commit();
    }

    public static int getYuzhi(Context context , String msg){
        SharedPreferences sharedPreferences = context.getSharedPreferences("yuzhi",Context.MODE_PRIVATE);

        if (msg.equals("wendu")){
            return sharedPreferences.getInt("wendu",32);
        } else if (msg.equals("shidu")){
            return sharedPreferences.getInt("shidu",80);
        } else if (msg.equals("guang")){
            return sharedPreferences.getInt("guang",567);
        } else if (msg.equals("co2")){
            return sharedPreferences.getInt("co2",324);
        } else if (msg.equals("pm25")){
            return sharedPreferences.getInt("pm25",254);
        } else if (msg.equals("status")){
            return sharedPreferences.getInt("status",3);
        }
        return 0;
    }

    public static void saveSwitch(Context context,boolean flag){
        SharedPreferences sharedPreferences = context.getSharedPreferences("switch",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("flag",flag);
        editor.commit();
    }

    public static boolean getSwitch(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("switch",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("flag",false);
    }
}
