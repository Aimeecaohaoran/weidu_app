package com.example.weidu_app.liebiao.model;



import com.example.weidu_app.api.UserApiService;
import com.example.weidu_app.bean.ByIdBean;
import com.example.weidu_app.bean.ErJi;
import com.example.weidu_app.bean.TopLasBean;
import com.example.weidu_app.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class LieModel implements IlieModel {


    @Override
    public void getyidata(final ModelYiCallBack modelYiCallBack) {
       UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getyiji()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<TopLasBean>() {
                    @Override
                    public void onNext(TopLasBean value) {
                        modelYiCallBack.succ(value);
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
    public void geterdata(String firstCategoryId, final ModelErCallBack modelErCallBack) {
        UserApiService userApiServicee = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiServicee.geterji(firstCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ErJi>() {
                    @Override
                    public void onNext(ErJi value) {
                        modelErCallBack.succ(value);
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
    public void getershopdata(String categoryId, final ModelErShopCallBack modelErShopCallBack) {
        UserApiService userApiServiceeshop = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiServiceeshop.geterjishop(categoryId,1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ByIdBean>() {
                    @Override
                    public void onNext(ByIdBean value) {
                        modelErShopCallBack.succ(value);
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
