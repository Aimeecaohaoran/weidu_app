package com.example.weidu_app.liebiao.model;

public interface IlieModel {
    void getyidata(ModelYiCallBack modelYiCallBack);
    //定义接口
    interface ModelYiCallBack{
        void succ(Object data);
        void onFailed();
    }
    void geterdata(String firstCategoryId, ModelErCallBack modelErCallBack);
    //定义接口
    interface ModelErCallBack{
        void succ(Object data);
        void onFailed();
    }
    void getershopdata(String categoryId, ModelErShopCallBack modelErShopCallBack);
    //定义接口
    interface ModelErShopCallBack{
        void succ(Object data);
        void onFailed();
    }
}
