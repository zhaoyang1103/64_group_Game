package com.mad.trafficclient.ws_java.ob63;


import android.widget.ImageView;

/**
 * Created by Go_Fight_Now on 2019/5/10 21:21
 */
public class Shopping_Bean {
    private Integer id;
    private String image;
    private String name;
    private String introduction;
    private Integer number;
    private Integer balance;

    public Shopping_Bean(Integer id, String image, String name, String introduction, Integer number, Integer balance) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.introduction = introduction;
        this.number = number;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Shopping_Bean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
