package com.mad.trafficclient.ws_java.ob11;


/**
 * Created by Go_Fight_Now on 2019/5/7 12:06
 */
public class LightBean {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * RedTime : 3
     * GreenTime : 5
     * YellowTime : 4
     */
    private Integer id;
    private String RedTime;
    private String GreenTime;
    private String YellowTime;

    @Override
    public String toString() {
        return "LightBean{" +
                "id=" + id +
                ", RedTime='" + RedTime + '\'' +
                ", GreenTime='" + GreenTime + '\'' +
                ", YellowTime='" + YellowTime + '\'' +
                '}';
    }

    public LightBean(Integer id, String redTime, String greenTime, String yellowTime) {
        this.id = id;
        RedTime = redTime;
        GreenTime = greenTime;
        YellowTime = yellowTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
