package com.example.weidu_app.QuanZi.model;



import com.example.weidu_app.api.UserApiService;
import com.example.weidu_app.bean.QuanBean;
import com.example.weidu_app.bean.RegBean;
import com.example.weidu_app.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class QuanModel implements IQuanModel {
    @Override
    public void getQuanDate(int page,int count ,final ModelQuanCallBack modelQuanCallBack) {
        UserApiService userApiService3 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService3.getQuan(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QuanBean>() {
                    @Override
                    public void onNext(QuanBean value) {
                        modelQuanCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getdianzan(Map path, Map pase, final IQuanCallBack iQuanCallBack) {
       UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getDian(path,pase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RegBean>() {
                    @Override
                    public void onNext(RegBean value) {
                        iQuanCallBack.succ(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getqiaodianzan(Map path, Map pase, final IQuanQuXiaoCallBack iQuanQuXiaoCallBack) {
        UserApiService userApiService1 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService1.getXiaoDian(path,pase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RegBean>() {
                    @Override
                    public void onNext(RegBean value) {
                        iQuanQuXiaoCallBack.succ(value);
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
