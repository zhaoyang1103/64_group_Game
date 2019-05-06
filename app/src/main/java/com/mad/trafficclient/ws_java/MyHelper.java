package com.mad.trafficclient.ws_java;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mad.trafficclient.ws_java.ob1.AccountBean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Go_Fight_Now on 2019/5/6 10:41
 */
public class MyHelper extends OrmLiteSqliteOpenHelper {
    public MyHelper(Context context) {
        super(context, "lx2019.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, AccountBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    private static MyHelper myHelper;

    public static MyHelper getHelper(Context context) {
        if (myHelper == null) {
            synchronized (MyHelper.class) {
                if (myHelper == null) {
                    myHelper = new MyHelper(context);
                }
            }
        }
        return myHelper;
    }

    private Map<String, Dao> daos = new HashMap<>();

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String simpleName = clazz.getSimpleName();
        if (daos.containsKey(simpleName)) {
            dao = daos.get(simpleName);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(simpleName, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
