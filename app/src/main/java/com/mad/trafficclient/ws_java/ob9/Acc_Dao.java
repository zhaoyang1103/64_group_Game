package com.mad.trafficclient.ws_java.ob9;


import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.ws_java.MyHelper;
import com.mad.trafficclient.ws_java.ob1.AccountBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/7 10:29
 */
public class Acc_Dao {
    private Context context;
    private MyHelper myHelper;
    private Dao<JiluBean,Integer> dao;

    public Acc_Dao(Context context) {
        this.context = context;
        try {
            myHelper = MyHelper.getHelper(context);
            dao = myHelper.getDao(JiluBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(JiluBean bean){
        try {
            dao.createIfNotExists(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JiluBean> queryForAll(){
        List<JiluBean> jiluBeans = new ArrayList<>();
        try {
            jiluBeans = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jiluBeans;
    }
}
