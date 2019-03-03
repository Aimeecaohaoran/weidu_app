package com.example.weidu_app.home.model;

import java.util.Map;

public interface IModel {
    //加载数据
    void  getDate(ModelCallBack callBack);
    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
    //banner
    void  getBanDate(ModelBanCallBack modelBanCallBack);
    interface  ModelBanCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }

}
