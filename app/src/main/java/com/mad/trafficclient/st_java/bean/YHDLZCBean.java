package com.mad.trafficclient.st_java.bean;

public class YHDLZCBean {
    private int id;
    private String UserName;
    private String UserPwd;
    private String Email;

    public YHDLZCBean(int id, String userName, String userPwd, String email) {
        this.id = id;
        UserName = userName;
        UserPwd = userPwd;
        Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
