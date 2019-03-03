package com.example.weidu_app.QuanZi.model;

import java.util.Map;

public interface IQuanModel {
    //圈子
    void  getQuanDate( int page,int count ,ModelQuanCallBack modelQuanCallBack);
    interface  ModelQuanCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
    void getdianzan(Map path, Map pase, IQuanCallBack iQuanCallBack);
    //定义接口
    interface IQuanCallBack{
        void succ(Object data);
        void onFailed();
    }
    void getqiaodianzan(Map path, Map pase, IQuanQuXiaoCallBack iQuanQuXiaoCallBack);
    //定义接口
    interface IQuanQuXiaoCallBack{
        void succ(Object data);
        void onFailed();
    }
}
