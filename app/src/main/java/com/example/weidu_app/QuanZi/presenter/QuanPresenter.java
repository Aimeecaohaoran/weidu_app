package com.example.weidu_app.QuanZi.presenter;



import com.example.weidu_app.QuanZi.model.IQuanModel;
import com.example.weidu_app.QuanZi.model.QuanModel;
import com.example.weidu_app.fragment.TwoFragment;

import java.util.Map;

public class QuanPresenter implements IQuanPresenter {
    TwoFragment twoFragment;
    private final QuanModel registModel;

    public QuanPresenter(TwoFragment twoFragment) {
        this.twoFragment = twoFragment;
        registModel = new QuanModel();
    }
    @Override
    public void getQuanModelView(int page, int count) {
        registModel.getQuanDate(page, count, new IQuanModel.ModelQuanCallBack() {
            @Override
            public void onSuccess(Object obj) {
                twoFragment.getQuanData(obj);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });

    }

    @Override
    public void getmodeldianzan(Map path, Map pase) {
        registModel.getdianzan(path, pase, new IQuanModel.IQuanCallBack() {
            @Override
            public void succ(Object data) {
                twoFragment.getDianzanView(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void getmodelquxiaodianzan(Map path, Map pase) {
        registModel.getqiaodianzan(path, pase, new IQuanModel.IQuanQuXiaoCallBack() {
            @Override
            public void succ(Object data) {
                twoFragment.getQuxiaoDianzanView(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}