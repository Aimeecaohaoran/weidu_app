package com.example.weidu_app.xiang.presenter;

import com.example.weidu_app.fragment.ThreeFragment;
import com.example.weidu_app.xiang.XiangActivity;
import com.example.weidu_app.xiang.model.IModel;
import com.example.weidu_app.xiang.model.MyModel;

import java.util.Map;

public class MyPresenter implements IMyPresenter {
    private final MyModel myModel;
    XiangActivity xiangActivity;
    ThreeFragment threeFragment;
    public MyPresenter(XiangActivity xiangActivity) {
        this.xiangActivity = xiangActivity;
        myModel = new MyModel();
    }

    public MyPresenter(ThreeFragment threeFragment) {
        this.threeFragment = threeFragment;
        myModel = new MyModel();
    }

    @Override
    public void getXiangModelView(String id) {
        myModel.getQingDate(id, new IModel.ModelQingCallBack() {
            @Override
            public void onSuccess(Object obj) {
                xiangActivity.getXiangData(obj);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }


    @Override
    public void getAddShopModelView(Map path,String data) {
        myModel.getaddShopDate(path,data, new IModel.ModelAddShopCallBack() {
            @Override
            public void onSuccess(Object obj) {
                xiangActivity.getAddShopData(obj);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getShopModelView(Map path) {
        myModel.getfindShopDate(path,new IModel.ModelShopCallBack() {
            @Override
            public void onSuccess(Object obj) {
                threeFragment.getShopData(obj);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getShopModelViews(Map path) {
        myModel.getfindShopDate(path,new IModel.ModelShopCallBack() {
            @Override
            public void onSuccess(Object obj) {
                xiangActivity.getShopDatas(obj);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

}
