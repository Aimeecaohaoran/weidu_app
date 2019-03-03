package com.example.weidu_app.home.presenter;


import com.example.weidu_app.fragment.OneFragment;
import com.example.weidu_app.home.model.IModel;
import com.example.weidu_app.home.model.MyModel;
import com.example.weidu_app.login.LoginActivity;

public class MyPresenter implements IMyPresenter {
    OneFragment oneFragment;
    private final MyModel myModel;


    public MyPresenter(OneFragment oneFragment) {
        this.oneFragment = oneFragment;
        myModel = new MyModel();
    }
    @Override
    public void getShowModelView(int page) {
        myModel.getDate(new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                oneFragment.getShowData(obj);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getBanModelView() {
        myModel.getBanDate(new IModel.ModelBanCallBack() {
            @Override
            public void onSuccess(Object obj) {
                oneFragment.getBanData(obj);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });

    }
}
