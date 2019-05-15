package com.mad.trafficclient.zy_java.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.CalendarView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ZyUtil {
    public static void saveCarNumber(Context context, String carnumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("carnumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(carnumber, carnumber);
        edit.commit();

    }

    public static List<String> getCarnumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("carnumber", Context.MODE_PRIVATE);
        Map<String, ?> all = sharedPreferences.getAll();
        List<String> list = new ArrayList<>();
        for (String s : all.keySet()) {
            list.add(s);
        }
        return list;
    }

    public static void deleteCarnumber(Context context, String carunmber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("carnumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(carunmber);
        edit.commit();
    }

    public static void saveList(Context context, UtilBean bean) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("l_test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String list = sharedPreferences.getString("list", "[]");
        List<UtilBean> data = new Gson().fromJson(list, new TypeToken<List<UtilBean>>() {
        }.getType());
        data.add(bean);
        String s = new Gson().toJson(data);
        editor.putString("list", s);
        editor.commit();
    }

    public static List<UtilBean> getList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("l_test", Context.MODE_PRIVATE);
        String list = sharedPreferences.getString("list", "[]");
        List<UtilBean> data = new Gson().fromJson(list, new TypeToken<List<UtilBean>>() {
        }.getType());
        return data;
    }

    public static void deteleUser(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("l_test", Context.MODE_PRIVATE);
        String list = sharedPreferences.getString("list", "[]");
        List<UtilBean> data = new Gson().fromJson(list, new TypeToken<List<UtilBean>>() {
        }.getType());
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName().equals(name)) {
                data.remove(i);
            }
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list", new Gson().toJson(data));
        editor.commit();
    }


}
