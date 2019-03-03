package com.example.weidu_app.bean;

import java.io.Serializable;

public class Citybean implements Serializable {
    private int cityid;

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public Citybean(int cityid) {

        this.cityid = cityid;
    }
}
