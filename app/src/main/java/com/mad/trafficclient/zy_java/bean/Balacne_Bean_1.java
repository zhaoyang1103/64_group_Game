package com.mad.trafficclient.zy_java.bean;

public class Balacne_Bean_1 {
    private  int carid;
    private  String state;
    private  int index;

    public Balacne_Bean_1() {
    }

    public Balacne_Bean_1(int carid, String state, int index) {
        this.carid = carid;
        this.state = state;
        this.index = index;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
