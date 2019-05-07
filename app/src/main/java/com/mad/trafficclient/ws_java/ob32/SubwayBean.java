package com.mad.trafficclient.ws_java.ob32;


import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/7 18:58
 */
public class SubwayBean {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * ROWS_DETAIL : [{"end_place":"四惠东","end_place_end_time":"23:30:00","end_place_start_time":"05:05:00","id":0,"img":"metro/metro_001.jpg","metro_code":"1号线","start_place":"苹果园","start_place_end_time":"23:30:00","start_place_start_time":"05:10:00"}]
     */

    private List<ROWSDETAILBean> ROWS_DETAIL;

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * end_place : 四惠东
         * end_place_end_time : 23:30:00
         * end_place_start_time : 05:05:00
         * id : 0
         * img : metro/metro_001.jpg
         * metro_code : 1号线
         * start_place : 苹果园
         * start_place_end_time : 23:30:00
         * start_place_start_time : 05:10:00
         */

        private String end_place;
        private String end_place_end_time;
        private String end_place_start_time;
        private int id;
        private String img;
        private String metro_code;
        private String start_place;
        private String start_place_end_time;
        private String start_place_start_time;

        public String getEnd_place() {
            return end_place;
        }

        public void setEnd_place(String end_place) {
            this.end_place = end_place;
        }

        public String getEnd_place_end_time() {
            return end_place_end_time;
        }

        public void setEnd_place_end_time(String end_place_end_time) {
            this.end_place_end_time = end_place_end_time;
        }

        public String getEnd_place_start_time() {
            return end_place_start_time;
        }

        public void setEnd_place_start_time(String end_place_start_time) {
            this.end_place_start_time = end_place_start_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getMetro_code() {
            return metro_code;
        }

        public void setMetro_code(String metro_code) {
            this.metro_code = metro_code;
        }

        public String getStart_place() {
            return start_place;
        }

        public void setStart_place(String start_place) {
            this.start_place = start_place;
        }

        public String getStart_place_end_time() {
            return start_place_end_time;
        }

        public void setStart_place_end_time(String start_place_end_time) {
            this.start_place_end_time = start_place_end_time;
        }

        public String getStart_place_start_time() {
            return start_place_start_time;
        }

        public void setStart_place_start_time(String start_place_start_time) {
            this.start_place_start_time = start_place_start_time;
        }
    }
}
