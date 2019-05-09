package com.mad.trafficclient.st_java.bean;

public class YHFLBean {
    private int icon;

    private boolean icon_state;

    private String username;
    private String pname;
    private String ptel;

    private String time;
    private String tv_guanliyuan = "一般管理员";
    private String tv_shoucang = "已收藏";
    private String btn_shoucang;
    private String btn_shanchu;

    private boolean btn_shoucang_state = false;
    private boolean btn_shanchu_state = false;
    private boolean iv_back_st = false;

    public YHFLBean(int icon, boolean icon_state, String username, String pname, String ptel, String time, String tv_guanliyuan, String tv_shoucang, String btn_shoucang, String btn_shanchu, boolean btn_shoucang_state, boolean btn_shanchu_state) {
        this.icon = icon;
        this.icon_state = icon_state;
        this.username = username;
        this.pname = pname;
        this.ptel = ptel;
        this.time = time;
        this.tv_guanliyuan = tv_guanliyuan;
        this.tv_shoucang = tv_shoucang;
        this.btn_shoucang = btn_shoucang;
        this.btn_shanchu = btn_shanchu;
        this.btn_shoucang_state = btn_shoucang_state;
        this.btn_shanchu_state = btn_shanchu_state;
    }

    public YHFLBean(boolean icon_state, boolean btn_shoucang_state, boolean btn_shanchu_state) {
        this.icon_state = icon_state;
        this.btn_shoucang_state = btn_shoucang_state;
        this.btn_shanchu_state = btn_shanchu_state;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isIcon_state() {
        return icon_state;
    }

    public void setIcon_state(boolean icon_state) {
        this.icon_state = icon_state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPtel() {
        return ptel;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTv_guanliyuan() {
        return tv_guanliyuan;
    }

    public void setTv_guanliyuan(String tv_guanliyuan) {
        this.tv_guanliyuan = tv_guanliyuan;
    }

    public String getTv_shoucang() {
        return tv_shoucang;
    }

    public void setTv_shoucang(String tv_shoucang) {
        this.tv_shoucang = tv_shoucang;
    }

    public String getBtn_shoucang() {
        return btn_shoucang;
    }

    public void setBtn_shoucang(String btn_shoucang) {
        this.btn_shoucang = btn_shoucang;
    }

    public String getBtn_shanchu() {
        return btn_shanchu;
    }

    public void setBtn_shanchu(String btn_shanchu) {
        this.btn_shanchu = btn_shanchu;
    }

    public boolean isBtn_shoucang_state() {
        return btn_shoucang_state;
    }

    public void setBtn_shoucang_state(boolean btn_shoucang_state) {
        this.btn_shoucang_state = btn_shoucang_state;
    }

    public boolean isBtn_shanchu_state() {
        return btn_shanchu_state;
    }

    public void setBtn_shanchu_state(boolean btn_shanchu_state) {
        this.btn_shanchu_state = btn_shanchu_state;
    }

}
