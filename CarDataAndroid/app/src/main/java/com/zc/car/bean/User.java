package com.zc.car.bean;

public class User {
    private String name;
    private String pwd;
    private String pwd2;


    public User() {}


    public User(String name, String pwd, String pwd2) {
        this.name = name;
        this.pwd = pwd;
        this.pwd2 = pwd2;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPwd() {
        return pwd;
    }


    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getPwd2() {
        return pwd2;
    }


    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }
}
