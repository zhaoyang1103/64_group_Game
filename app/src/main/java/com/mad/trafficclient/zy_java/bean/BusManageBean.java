package com.mad.trafficclient.zy_java.bean;

import java.util.List;

/**
 * Created by 昭阳 on 2019/5/6.
 */
public class BusManageBean {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * ROWS_DETAIL : [{"BusId":1,"Distance":1413},{"BusId":2,"Distance":9370},{"BusId":3,"Distance":8838}]
     */

    private String ERRMSG;
    private String RESULT;
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

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * BusId : 1
         * Distance : 1413
         */
        private int BusId;
        private int Distance;
        private int time;
        private int person;


        public ROWSDETAILBean() {
        }

        public ROWSDETAILBean(int busId, int distance, int time, int person) {
            BusId = busId;
            Distance = distance;
            this.time = time;
            this.person = person;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getPerson() {
            return person;
        }

        public void setPerson(int person) {
            this.person = person;
        }

        public int getBusId() {
            return BusId;
        }

        public void setBusId(int BusId) {
            this.BusId = BusId;
        }

        public int getDistance() {
            return Distance;
        }

        public void setDistance(int Distance) {
            this.Distance = Distance;
        }
    }
}
