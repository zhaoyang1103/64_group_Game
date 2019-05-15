package com.mad.trafficclient.ws_java.ob63;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/11 12:33
 */
public class Shopping_Util {
    public static void saveList(Context context, List<Shopping_Bean> list) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("list", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String listStr = gson.toJson(list);
        Log.i("Go_Fight_Now 提醒您", "gson.toJson(list)" + ":" + listStr);
        editor.putString("listStr",listStr);
        editor.commit();
    }

    public static String getList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("list", Context.MODE_PRIVATE);
        return sharedPreferences.getString("listStr", "[]");
    }

}
