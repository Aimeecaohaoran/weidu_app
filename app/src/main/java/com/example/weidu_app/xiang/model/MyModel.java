package com.example.weidu_app.xiang.model;



import com.example.weidu_app.api.UserApiService;
import com.example.weidu_app.bean.FindShop;
import com.example.weidu_app.bean.ShopResultBean;
import com.example.weidu_app.bean.Shopping;
import com.example.weidu_app.bean.ShowShoppingBean;
import com.example.weidu_app.bean.SyncBean;
import com.example.weidu_app.bean.XqBean;
import com.example.weidu_app.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MyModel implements IModel {

    @Override
    public void getfindShopDate(Map path, final ModelShopCallBack modelShopCallBack) {
        UserApiService userApiService4 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService4.getShop(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Shopping>() {
                    @Override
                    public void onNext(Shopping value) {
                        modelShopCallBack.onSuccess(value);
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
    public void getQingDate(String id, final ModelQingCallBack modelQingCallBack) {
        UserApiService userApiService3 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService3.getXiang(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<XqBean>() {
                    @Override
                    public void onNext(XqBean value) {
                        modelQingCallBack.onSuccess(value);
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
    public void getaddShopDate(Map path,String data, final ModelAddShopCallBack modelAddShopCallBack) {
        UserApiService userApiService4 = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService4.getAddShop(path,data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SyncBean>() {
                    @Override
                    public void onNext(SyncBean value) {
                        modelAddShopCallBack.onSuccess(value);
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
