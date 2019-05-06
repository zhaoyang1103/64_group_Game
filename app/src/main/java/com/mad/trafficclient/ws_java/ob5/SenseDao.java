package com.mad.trafficclient.ws_java.ob5;


import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.ws_java.MyHelper;
import com.mad.trafficclient.ws_java.ob1.AccountBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/6 16:48
 */
public class SenseDao {
    private Context context;
    private MyHelper myHelper;
    private Dao<IndexBean, Integer> dao;

    public SenseDao(Context context) {
        this.context = context;
        try {
            myHelper = MyHelper.getHelper(context);
            dao = myHelper.getDao(IndexBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(IndexBean bean) {
        try {
            dao.createIfNotExists(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<IndexBean> queryForAll() {
        List<IndexBean> indexBeans = new ArrayList<>();
        try {
            indexBeans = dao.queryForAll();
            Collections.sort(indexBeans, new Comparator<IndexBean>() {
                @Override
                public int compare(IndexBean o1, IndexBean o2) {
                    return o2.getId() - o1.getId();
                }
            });
            if (indexBeans.size() > 20) {
                for (int i = 20; i < indexBeans.size(); i++) {
                    indexBeans.remove(i);
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexBeans;
    }
}
