package com.example.weidu_app.xiang.model;

import java.util.Map;

public interface IModel {
    //购物车
    void  getfindShopDate(Map path, ModelShopCallBack modelShopCallBack);
    interface  ModelShopCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
    //详情
    void  getQingDate(String id, ModelQingCallBack modelQingCallBack);
    interface  ModelQingCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
    //加入购物车
    void  getaddShopDate(Map path,String data, ModelAddShopCallBack modelAddShopCallBack);
    interface  ModelAddShopCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }


}
