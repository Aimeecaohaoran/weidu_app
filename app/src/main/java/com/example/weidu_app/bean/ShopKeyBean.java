package com.example.weidu_app.bean;

import java.io.Serializable;

public class ShopKeyBean implements Serializable {
    private String id;

    public ShopKeyBean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
