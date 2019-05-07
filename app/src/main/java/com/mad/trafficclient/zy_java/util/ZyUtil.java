package com.mad.trafficclient.zy_java.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
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
}
