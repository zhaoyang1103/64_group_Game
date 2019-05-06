package com.mad.trafficclient.zy_java.bean;

public class LightManageBean {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * RedTime : 3
     * GreenTime : 5
     * YellowTime : 4
     */

    private String ERRMSG;
    private String RESULT;
    private int road;
    private int RedTime;
    private int GreenTime;
    private int YellowTime;

    public LightManageBean() {
    }

    public LightManageBean(int road, int redTime, int greenTime, int yellowTime) {
        this.road = road;
        RedTime = redTime;
        GreenTime = greenTime;
        YellowTime = yellowTime;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

    public int getRedTime() {
        return RedTime;
    }

    public void setRedTime(int redTime) {
        RedTime = redTime;
    }

    public int getGreenTime() {
        return GreenTime;
    }

    public void setGreenTime(int greenTime) {
        GreenTime = greenTime;
    }

    public int getYellowTime() {
        return YellowTime;
    }

    public void setYellowTime(int yellowTime) {
        YellowTime = yellowTime;
    }
}
