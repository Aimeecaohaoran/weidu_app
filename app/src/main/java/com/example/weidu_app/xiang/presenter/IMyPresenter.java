package com.example.weidu_app.xiang.presenter;

import java.util.Map;

public interface IMyPresenter {
    void getXiangModelView(String id);
    void getAddShopModelView(Map path,String data);
    void getShopModelView(Map path);
    void getShopModelViews(Map path);
}
