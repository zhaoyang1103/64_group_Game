package com.mad.trafficclient.ws_java.ob1;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Go_Fight_Now on 2019/5/6 11:12
 */
@DatabaseTable(tableName = "ob1_account_table")
public class AccountBean {
    @DatabaseField(columnName = "id",generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "carid")
    private int carid;
    @DatabaseField(columnName = "balanc")
    private int balanc;
    @DatabaseField(columnName = "user")
    private String user;
    @DatabaseField(columnName = "time")
    private String time;

    public AccountBean() {
    }

    public AccountBean(Integer id, int carid, int balanc, String user, String time) {
        this.id = id;
        this.carid = carid;
        this.balanc = balanc;
        this.user = user;
        this.time = time;
    }

    public AccountBean(int carid, int balanc, String user, String time) {
        this.carid = carid;
        this.balanc = balanc;
        this.user = user;
        this.time = time;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "id=" + id +
                ", carid=" + carid +
                ", balanc=" + balanc +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public int getBalanc() {
        return balanc;
    }

    public void setBalanc(int balanc) {
        this.balanc = balanc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
