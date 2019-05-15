package com.mad.trafficclient.st_java.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class YHDLZCHelper extends SQLiteOpenHelper {
    public YHDLZCHelper(@Nullable Context context) {
        super(context, "YHDLZCDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table UserInfo(id integer primary key autoincrement," +
                "UserName varchar(20)," +
                "UserPwd varchar(20)," +
                "Email varchar(20)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
