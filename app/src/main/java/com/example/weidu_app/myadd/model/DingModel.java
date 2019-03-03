package com.example.weidu_app.myadd.model;


import com.example.weidu_app.api.UserApiService;
import com.example.weidu_app.bean.DingDanBean;
import com.example.weidu_app.bean.SyncBean;
import com.example.weidu_app.myadd.AddressListBean;
import com.example.weidu_app.myadd.ShouHuoBean;
import com.example.weidu_app.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DingModel implements Idingmodel
{
    @Override
    public void getdmyressdata(Map<String, String> path, final ModelMyAddressCallBack modelMyAddressCallBack) {
        UserApiService userApi = RetrofitUtils.getInstance().create(UserApiService.class);
        userApi.getmyaddress(path)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableObserver<AddressListBean>() {
                    @Override
                    public void onNext(AddressListBean value) {
                        modelMyAddressCallBack.Onsuccess(value);
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
    public void getdaddressdata(Map<String, String> path, Map<String, String> data, final ModelAddAddressCallBack modelAddAddressCallBack) {
        UserApiService userApi = RetrofitUtils.getInstance().create(UserApiService.class);
        userApi.getAddaddress(path,data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new DisposableObserver<SyncBean>() {
                    @Override
                    public void onNext(SyncBean value) {
                        modelAddAddressCallBack.Onsuccess(value);
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
