package com.mad.trafficclient.zy_java.bean;

public class LightBean {
    public LightBean() {
    }

    public LightBean(String redTime, String greenTime, String yellowTime) {
        RedTime = redTime;
        GreenTime = greenTime;
        YellowTime = yellowTime;
    }
    /**
     * ERRMSG : 成功
     * RESULT : S
     * RedTime : 2
     * GreenTime : 2
     * YellowTime : 2
     */

    private String ERRMSG;
    private String RESULT;
    private String RedTime;
    private String GreenTime;
    private String YellowTime;

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

    public String getRedTime() {
        return RedTime;
    }

    public void setRedTime(String RedTime) {
        this.RedTime = RedTime;
    }

    public String getGreenTime() {
        return GreenTime;
    }

    public void setGreenTime(String GreenTime) {
        this.GreenTime = GreenTime;
    }

    public String getYellowTime() {
        return YellowTime;
    }

    public void setYellowTime(String YellowTime) {
        this.YellowTime = YellowTime;
    }
}
