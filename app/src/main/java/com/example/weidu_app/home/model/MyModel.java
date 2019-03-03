package com.example.weidu_app.home.model;


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
    public void getDate(final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getcommodity(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean value) {
                        callBack.onSuccess(value);
                        System.out.println("数据"+value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e);


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getBanDate(final ModelBanCallBack modelBanCallBack) {
        UserApiService userApiService1 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService1.getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBeanTwo>() {
                    @Override
                    public void onNext(NewsBeanTwo value) {
                    modelBanCallBack.onSuccess(value);
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
