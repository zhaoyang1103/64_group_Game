package com.mad.trafficclient.st_java.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.mad.trafficclient.st_java.bean.YHDLZCBean;

import java.util.ArrayList;

public class YHDLZCDao extends YHDLZCHelper {

    private final YHDLZCHelper yhdlzcHelper;
    private SQLiteDatabase readableDatabase;

    public YHDLZCDao(@Nullable Context context) {
        super(context);
        yhdlzcHelper = new YHDLZCHelper(context);
        readableDatabase = yhdlzcHelper.getReadableDatabase();
    }

    public void addItem(YHDLZCBean yhdlzcBean) {
        ContentValues values = new ContentValues();
        values.put("UserName", yhdlzcBean.getUserName());
        values.put("UserPwd", yhdlzcBean.getUserPwd());
        values.put("Email", yhdlzcBean.getEmail());
        readableDatabase.insert("UserInfo", null, values);
    }

    public ArrayList<YHDLZCBean> queryAll() {
        ArrayList<YHDLZCBean> yhdlzcBeans = new ArrayList<>();
        Cursor userInfo = readableDatabase.query("UserInfo", null, null, null, null, null, null);
        while (userInfo.moveToNext()) {
            yhdlzcBeans.add(new YHDLZCBean(userInfo.getInt(0), userInfo.getString(1), userInfo.getString(2), userInfo.getString(3)));
        }
        return yhdlzcBeans;
    }
}
