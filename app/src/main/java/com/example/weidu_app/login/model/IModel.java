package com.example.weidu_app.login.model;

import java.util.Map;

public interface IModel {

    //登录
    void  getLoginDate(String phone, String pwd, ModelLoginCallBack modelLoginCallBack);
    interface  ModelLoginCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }


}
