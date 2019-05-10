package com.mad.trafficclient.st_java.bean;

public class MyOpinion {
    private String title;
    private String opinion;
    private String phone;
    private String submit_time;
    private String state = "未受理";
    private String call_back_content = "---";
    private String call_back_time = "---";

    public MyOpinion(String title, String opinion, String phone) {
        this.title = title;
        this.opinion = opinion;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCall_back_content() {
        return call_back_content;
    }

    public void setCall_back_content(String call_back_content) {
        this.call_back_content = call_back_content;
    }

    public String getCall_back_time() {
        return call_back_time;
    }

    public void setCall_back_time(String call_back_time) {
        this.call_back_time = call_back_time;
    }
}
