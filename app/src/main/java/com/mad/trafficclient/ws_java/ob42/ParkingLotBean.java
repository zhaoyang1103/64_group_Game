package com.mad.trafficclient.ws_java.ob42;


import java.util.List;

/**
 * Created by Go_Fight_Now on 2019/5/9 19:39
 */
public class ParkingLotBean {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * parking_map_url : metro/metro_001.jpg
     * ROWS_DETAIL : [{"charging_reference":"每小时10元，最高每天100元","coordinate_latitude":"105","coordinate_longitude":"40","distance":"40","free_parking_number":14,"id":25,"open_type":"对外开放","parking_address":"麓西南路","parking_name":"新登停车场","payment_method":"10元/小时","total_parking_number":52}]
     */

    private String parking_map_url;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getParking_map_url() {
        return parking_map_url;
    }

    public void setParking_map_url(String parking_map_url) {
        this.parking_map_url = parking_map_url;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * charging_reference : 每小时10元，最高每天100元
         * coordinate_latitude : 105
         * coordinate_longitude : 40
         * distance : 40
         * free_parking_number : 14
         * id : 25
         * open_type : 对外开放
         * parking_address : 麓西南路
         * parking_name : 新登停车场
         * payment_method : 10元/小时
         * total_parking_number : 52
         */

        private String charging_reference;
        private String coordinate_latitude;
        private String coordinate_longitude;
        private String distance;
        private int free_parking_number;
        private int id;
        private String open_type;
        private String parking_address;
        private String parking_name;
        private String payment_method;
        private int total_parking_number;

        public String getCharging_reference() {
            return charging_reference;
        }

        public void setCharging_reference(String charging_reference) {
            this.charging_reference = charging_reference;
        }

        public String getCoordinate_latitude() {
            return coordinate_latitude;
        }

        public void setCoordinate_latitude(String coordinate_latitude) {
            this.coordinate_latitude = coordinate_latitude;
        }

        public String getCoordinate_longitude() {
            return coordinate_longitude;
        }

        public void setCoordinate_longitude(String coordinate_longitude) {
            this.coordinate_longitude = coordinate_longitude;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getFree_parking_number() {
            return free_parking_number;
        }

        public void setFree_parking_number(int free_parking_number) {
            this.free_parking_number = free_parking_number;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOpen_type() {
            return open_type;
        }

        public void setOpen_type(String open_type) {
            this.open_type = open_type;
        }

        public String getParking_address() {
            return parking_address;
        }

        public void setParking_address(String parking_address) {
            this.parking_address = parking_address;
        }

        public String getParking_name() {
            return parking_name;
        }

        public void setParking_name(String parking_name) {
            this.parking_name = parking_name;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public int getTotal_parking_number() {
            return total_parking_number;
        }

        public void setTotal_parking_number(int total_parking_number) {
            this.total_parking_number = total_parking_number;
        }
    }
}
