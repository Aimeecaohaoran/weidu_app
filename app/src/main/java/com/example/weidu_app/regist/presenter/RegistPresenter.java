package com.example.weidu_app.regist.presenter;


import com.example.weidu_app.regist.RegActivity;
import com.example.weidu_app.regist.model.IRegistModel;
import com.example.weidu_app.regist.model.RegistModel;

public class RegistPresenter implements IRegistPresenter{
    RegActivity regActivity;
    private final RegistModel registModel;

    public RegistPresenter(RegActivity regActivity) {
        this.regActivity = regActivity;
        registModel = new RegistModel();
    }



    @Override
    public void registPre(String phone, String pwd) {
        registModel.regist(phone, pwd, new IRegistModel.IRegistCallBack() {
            @Override
            public void onStatus(Object data) {
                regActivity.showMsg(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }
    //防止内存泄漏
    public void destory(){
        if (regActivity!=null){
            regActivity=null;
        }
    }
}