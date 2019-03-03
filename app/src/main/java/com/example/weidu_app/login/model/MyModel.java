package com.example.weidu_app.login.model;


import com.example.weidu_app.api.UserApiService;
import com.example.weidu_app.bean.FindShop;
import com.example.weidu_app.bean.HomeBean;
import com.example.weidu_app.bean.LoginBean;
import com.example.weidu_app.bean.NewsBeanTwo;
import com.example.weidu_app.bean.QuanBean;
import com.example.weidu_app.bean.ShopResultBean;
import com.example.weidu_app.bean.SouBean;
import com.example.weidu_app.bean.XqBean;
import com.example.weidu_app.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MyModel implements IModel {
    @Override
    public void getLoginDate(String phone, String pwd, final ModelLoginCallBack modelLoginCallBack) {
        UserApiService userApiService2 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService2.getLogin(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean value) {
                        modelLoginCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
