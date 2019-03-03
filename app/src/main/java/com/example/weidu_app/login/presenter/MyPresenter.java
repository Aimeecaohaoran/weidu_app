package com.example.weidu_app.login.presenter;

import android.util.Log;


import com.example.weidu_app.fragment.OneFragment;
import com.example.weidu_app.login.LoginActivity;
import com.example.weidu_app.login.model.IModel;
import com.example.weidu_app.login.model.MyModel;

import java.util.Map;

public class MyPresenter implements IMyPresenter {

    LoginActivity loginActivity;
    private final MyModel myModel;


    public MyPresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        myModel = new MyModel();
    }


    @Override
    public void getBanLoginView(String phone, String pwd) {
      myModel.getLoginDate(phone, pwd, new IModel.ModelLoginCallBack() {
          @Override
          public void onSuccess(Object obj) {
              loginActivity.getLoginData(obj);
          }

          @Override
          public void onFail(Throwable e) {

          }
      });
    }
}
