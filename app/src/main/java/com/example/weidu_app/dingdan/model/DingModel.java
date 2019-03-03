package com.example.weidu_app.dingdan.model;


import com.example.weidu_app.api.UserApiService;
import com.example.weidu_app.bean.DingDanBean;
import com.example.weidu_app.dingdan.bean.DingBean;
import com.example.weidu_app.dingdan.bean.OrderBean;
import com.example.weidu_app.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DingModel implements Idingmodel
{
    @Override
    public void getdmdata(HashMap<String, Object> hashMap, int addressId, double totalPrice, HashMap<String, Object> ordermap, final DingCallBack dingCallBack) {
        UserApiService userApi = RetrofitUtils.getInstance().create(UserApiService.class);
        userApi.getDingdan(hashMap,addressId,totalPrice,ordermap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DingDanBean>() {
                    @Override
                    public void onNext(DingDanBean value) {
                        dingCallBack.Onsuccess(value);
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
    public void getfinding(HashMap<String, Object> hashMap, HashMap<String, Object> data, final FindDingCallBack findDingCallBack) {
        UserApiService userApi = RetrofitUtils.getInstance().create(UserApiService.class);
        userApi.getfindding(hashMap,data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<OrderBean>() {
                    @Override
                    public void onNext(OrderBean value) {
                        findDingCallBack.Onsuccess(value);
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
