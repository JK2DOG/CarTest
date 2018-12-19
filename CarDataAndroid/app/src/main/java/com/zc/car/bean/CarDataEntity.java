package com.zc.car.bean;

public class CarDataEntity {

    public static final int CAR = 1;
    public static final int TRUCK_5 = 2;
    public static final int TRUCK_10 = 3;
    public static final int TIPPER = 4;
    public static final int ARTICULATED = 5;

    private int id;

    private int type;

    private String driver;

    private String rego;

    private String s_time;

    private String f_breake;

    private String s_breake;

    private String e_time;


    public CarDataEntity() {}


    public CarDataEntity(int type, String driver, String rego, String s_time, String f_breake, String s_breake, String e_time) {
        this.type = type;
        this.driver = driver;
        this.rego = rego;
        this.s_time = s_time;
        this.f_breake = f_breake;
        this.s_breake = s_breake;
        this.e_time = e_time;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getType() {
        return type;
    }


    public static String getTypeStr(int i) {
        String type_str = null;
        switch (i) {
            case CAR:
                type_str = "Car";
                break;
            case TRUCK_5:
                type_str = "5t Truck";
                break;
            case TRUCK_10:
                type_str = "10t Truck";
                break;
            case TIPPER:
                type_str = "Tipper";
                break;
            case ARTICULATED:
                type_str = "Articulated";
                break;
        }
        return type_str;
    }


    public void setType(int type) {
        this.type = type;
    }


    public String getDriver() {
        return driver;
    }


    public void setDriver(String driver) {
        this.driver = driver;
    }


    public String getRego() {
        return rego;
    }


    public void setRego(String rego) {
        this.rego = rego;
    }


    public String getS_time() {
        return s_time;
    }


    public void setS_time(String s_time) {
        this.s_time = s_time;
    }


    public String getF_breake() {
        return f_breake;
    }


    public void setF_breake(String f_breake) {
        this.f_breake = f_breake;
    }


    public String getS_breake() {
        return s_breake;
    }


    public void setS_breake(String s_breake) {
        this.s_breake = s_breake;
    }


    public String getE_time() {
        return e_time;
    }


    public void setE_time(String e_time) {
        this.e_time = e_time;
    }
}
