package com.example.weidu_app.liebiao.presenter;

public interface ILiePresenter {
    void getYiPre();
    void getErPre(String firstCategoryId);
    void getErShopPre(String categoryId, int page, int cout);
}
