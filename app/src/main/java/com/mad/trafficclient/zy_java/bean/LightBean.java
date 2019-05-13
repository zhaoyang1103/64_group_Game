package com.mad.trafficclient.zy_java.bean;

import com.mad.trafficclient.R;

public class LightBean {
    public LightBean() {
    }

    public LightBean(int redTime, int greenTime, int yellowTime) {
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
    private int RedTime;
    private int GreenTime;
    private int YellowTime;
    private String change_time_heng;
    private int image_heng;
    private String change_time_zong;
    private int image_zong;

    public String getChange_time_heng() {
        return change_time_heng;
    }

    public void setChange_time(String change_time_heng) {
        this.change_time_heng = change_time_heng;
        if (change_time_heng.substring(0, 1).equals("红")) {
            setImage_heng(R.drawable.hongdeng);
        } else if (change_time_heng.substring(0, 1).equals("绿")) {
            setImage_heng(R.drawable.lvdeng);
        } else if (change_time_heng.substring(0, 1).equals("黄")) {
            setImage_heng(R.drawable.huangdeng);
        }
    }

    public void setChange_time_heng(String change_time_heng) {
        this.change_time_heng = change_time_heng;
    }

    public int getImage_heng() {
        return image_heng;
    }

    private void setImage_heng(int image_heng) {
        this.image_heng = image_heng;
    }

    public String getChange_time_zong() {
        return change_time_zong;
    }

    public void setChange_time_zong(String change_time_zong) {
        this.change_time_zong = change_time_zong;
        if (change_time_zong.substring(0, 1).equals("红")) {
            setImage_zong(R.drawable.hongdeng);
        } else if (change_time_zong.substring(0, 1).equals("绿")) {
            setImage_zong(R.drawable.lvdeng);
        } else if (change_time_zong.substring(0, 1).equals("黄")) {
            setImage_zong(R.drawable.huangdeng);
        }


    }

    public int getImage_zong() {
        return image_zong;
    }

    private void setImage_zong(int image_zong) {
        this.image_zong = image_zong;
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

    public int getRedTime() {
        return RedTime;
    }

    public void setRedTime(int RedTime) {
        this.RedTime = RedTime;
    }

    public int getGreenTime() {
        return GreenTime;
    }

    public void setGreenTime(int GreenTime) {
        this.GreenTime = GreenTime;
    }

    public int getYellowTime() {
        return YellowTime;
    }

    public void setYellowTime(int YellowTime) {
        this.YellowTime = YellowTime;
    }
}
