package com.mad.trafficclient.ws_java.ob1;


import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.ws_java.MyHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/6 15:48
 */
public class AccountDao {
    private Context context;
    private MyHelper myHelper;
    private Dao<AccountBean,Integer> dao;

    public AccountDao(Context context) {
        this.context = context;
        try {
            myHelper = MyHelper.getHelper(context);
            dao = myHelper.getDao(AccountBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(AccountBean bean){
        try {
            dao.createIfNotExists(bean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AccountBean> queryForAll(){
        List<AccountBean> accountBeans = new ArrayList<>();
        try {
            accountBeans = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountBeans;
    }
}
