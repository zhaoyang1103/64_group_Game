package com.mad.trafficclient.st_java.bean;

import java.util.List;

public class Get_weather {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * ROWS_DETAIL : [{"temperature":"18~25","weather":"小雨","WData":"2019-05-05"},{"temperature":"15~20","weather":"大雨","WData":"2019-05-06"},{"temperature":"11~13","weather":"小雨","WData":"2019-05-07"},{"temperature":"13~29","weather":"中雨","WData":"2019-05-08"},{"temperature":"13~23","weather":"小雨","WData":"2019-05-09"},{"temperature":"19~29","weather":"大雨","WData":"2019-05-10"},{"temperature":"10~27","weather":"中雨","WData":"2019-05-11"}]
     * WCurrent : 19
     */

    private String ERRMSG;
    private String RESULT;
    private int WCurrent;
    private List<ROWSDETAILBean> ROWS_DETAIL;

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

    public int getWCurrent() {
        return WCurrent;
    }

    public void setWCurrent(int WCurrent) {
        this.WCurrent = WCurrent;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * temperature : 18~25
         * weather : 小雨
         * WData : 2019-05-05
         */

        private String temperature;
        private String weather;
        private String WData;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWData() {
            return WData;
        }

        public void setWData(String WData) {
            this.WData = WData;
        }
    }
}
