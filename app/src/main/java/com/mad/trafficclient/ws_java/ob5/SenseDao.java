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

import static android.util.Log.i;

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
            List<IndexBean> list = new ArrayList<>();
            Collections.sort(indexBeans, new Comparator<IndexBean>() {
                @Override
                public int compare(IndexBean o1, IndexBean o2) {
                    return o2.getId() - o1.getId();
                }
            });
            if (indexBeans.size() > 20) {

                for (int i = 0; i < 20; i++) {
                    list.add(indexBeans.get(i));
                }

            }
            i("SenseDao尺寸", "" + indexBeans.size());
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indexBeans;
    }
}
