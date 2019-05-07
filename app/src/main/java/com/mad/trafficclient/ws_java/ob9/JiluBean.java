package com.mad.trafficclient.ws_java.ob9;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Go_Fight_Now on 2019/5/7 10:26
 */
@DatabaseTable(tableName = "ob9_table")
public class JiluBean {
    @DatabaseField(columnName = "id" , generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "chepai")
    private String chepai;
    @DatabaseField(columnName = "money")
    private Integer money;
    @DatabaseField(columnName = "balance")
    private Integer balance;
    @DatabaseField(columnName = "user")
    private String user;
    @DatabaseField(columnName = "time")
    private String time;

    @Override
    public String toString() {
        return "JiluBean{" +
                "id=" + id +
                ", chepai='" + chepai + '\'' +
                ", money=" + money +
                ", balance=" + balance +
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

    public String getChepai() {
        return chepai;
    }

    public void setChepai(String chepai) {
        this.chepai = chepai;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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

    public JiluBean(String chepai, Integer money, Integer balance, String user, String time) {
        this.chepai = chepai;
        this.money = money;
        this.balance = balance;
        this.user = user;
        this.time = time;
    }

    public JiluBean(Integer id, String chepai, Integer money, Integer balance, String user, String time) {
        this.id = id;
        this.chepai = chepai;
        this.money = money;
        this.balance = balance;
        this.user = user;
        this.time = time;
    }

    public JiluBean() {
    }
}
