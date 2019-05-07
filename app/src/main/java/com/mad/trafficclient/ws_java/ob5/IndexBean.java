package com.mad.trafficclient.ws_java.ob5;


import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Go_Fight_Now on 2019/5/6 18:00
 */
@DatabaseTable(tableName = "ob5_table")
public class IndexBean {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * pm2.5 : 8
     * co2 : 5600
     * LightIntensity : 1596
     * humidity : 17
     * temperature : 14
     */
    @DatabaseField(columnName = "id",generatedId = true)
    private Integer id;
    @SerializedName("pm2.5")
    @DatabaseField(columnName = "pm25")
    private int _$Pm25316; // FIXME check this code
    @DatabaseField(columnName = "co2")
    private int co2;
    @DatabaseField(columnName = "LightIntensity")
    private int LightIntensity;
    @DatabaseField(columnName = "humidity")
    private int humidity;
    @DatabaseField(columnName = "temperature")
    private int temperature;
    @DatabaseField(columnName = "time")
    private String time;
    @DatabaseField(columnName = "status")
    private int status;


    public IndexBean() {
    }

    @Override
    public String toString() {
        return "IndexBean{" +
                "id=" + id +
                ", _$Pm25316=" + _$Pm25316 +
                ", co2=" + co2 +
                ", LightIntensity=" + LightIntensity +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", time=" + time +
                ", status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int get_$Pm25316() {
        return _$Pm25316;
    }

    public void set_$Pm25316(int _$Pm25316) {
        this._$Pm25316 = _$Pm25316;
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
