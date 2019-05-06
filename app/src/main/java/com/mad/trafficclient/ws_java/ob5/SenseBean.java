package com.mad.trafficclient.ws_java.ob5;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Go_Fight_Now on 2019/5/6 16:45
 */
public class SenseBean {
    private String name;
    private Integer zhi;
    private Integer yuzhi;

    @Override
    public String toString() {
        return "SenseBean{" +
                ", name='" + name + '\'' +
                ", zhi=" + zhi +
                ", yuzhi=" + yuzhi +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZhi() {
        return zhi;
    }

    public void setZhi(Integer zhi) {
        this.zhi = zhi;
    }

    public Integer getYuzhi() {
        return yuzhi;
    }

    public void setYuzhi(Integer yuzhi) {
        this.yuzhi = yuzhi;
    }

    public SenseBean(String name, Integer zhi, Integer yuzhi) {
        this.name = name;
        this.zhi = zhi;
        this.yuzhi = yuzhi;
    }
}
