package com.mad.trafficclient.ws_java.ob9;


/**
 * Created by Go_Fight_Now on 2019/5/6 20:49
 */
public class Acc_Bean {
    private int id;
    private String chebiao;
    private String chepai;
    private String chezhu;
    private int balance;

    @Override
    public String toString() {
        return "Acc_Bean{" +
                "id=" + id +
                ", chebiao='" + chebiao + '\'' +
                ", chepai='" + chepai + '\'' +
                ", chezhu='" + chezhu + '\'' +
                ", balance=" + balance +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChebiao() {
        return chebiao;
    }

    public void setChebiao(String chebiao) {
        this.chebiao = chebiao;
    }

    public String getChepai() {
        return chepai;
    }

    public void setChepai(String chepai) {
        this.chepai = chepai;
    }

    public String getChezhu() {
        return chezhu;
    }

    public void setChezhu(String chezhu) {
        this.chezhu = chezhu;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Acc_Bean(int id, String chebiao, String chepai, String chezhu, int balance) {
        this.id = id;
        this.chebiao = chebiao;
        this.chepai = chepai;
        this.chezhu = chezhu;
        this.balance = balance;
    }
}
