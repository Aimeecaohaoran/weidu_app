package com.example.weidu_app.regist.model;



import com.example.weidu_app.api.UserApiService;
import com.example.weidu_app.bean.RegBean;
import com.example.weidu_app.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class RegistModel implements IRegistModel{
    private UserApiService userApiService;

    @Override
    public void regist(String phone, String pwd, final IRegistCallBack callBack) {
        userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getReg(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RegBean>() {
                    @Override
                    public void onNext(RegBean value) {
                        callBack.onStatus(value);
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
