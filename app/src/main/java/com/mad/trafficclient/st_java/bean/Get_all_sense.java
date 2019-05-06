package com.mad.trafficclient.st_java.bean;

import com.google.gson.annotations.SerializedName;

public class Get_all_sense {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * pm2.5 : 14
     * co2 : 2526
     * LightIntensity : 1391
     * humidity : 35
     * temperature : 15
     */

    private String ERRMSG;
    private String RESULT;
    @SerializedName("pm2.5")
    private int _$Pm25126; // FIXME check this code
    private int co2;
    private int LightIntensity;
    private int humidity;
    private int temperature;

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

    public int get_$Pm25126() {
        return _$Pm25126;
    }

    public void set_$Pm25126(int _$Pm25126) {
        this._$Pm25126 = _$Pm25126;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int LightIntensity) {
        this.LightIntensity = LightIntensity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
