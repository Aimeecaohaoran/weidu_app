package com.example.weidu_app.myadd.presenter;


import com.example.weidu_app.myadd.CityAddActivity;
import com.example.weidu_app.myadd.CityListActivity;
import com.example.weidu_app.myadd.model.DingModel;
import com.example.weidu_app.myadd.model.Idingmodel;

import java.util.HashMap;
import java.util.Map;

public class DingPresenter implements Idingpresenter
{
    CityListActivity cityListActivity;
    CityAddActivity cityAddActivity;
    private final DingModel dingModel;

    public DingPresenter( CityListActivity cityListActivity) {
        this.cityListActivity = cityListActivity;
        dingModel = new DingModel();
    }

    public DingPresenter(CityAddActivity cityAddActivity) {
        this.cityAddActivity = cityAddActivity;
        dingModel = new DingModel();
    }

    @Override
    public void getmyaddress(Map<String, String> path) {
        dingModel.getdmyressdata(path, new Idingmodel.ModelMyAddressCallBack() {
            @Override
            public void Onsuccess(Object object) {
                cityListActivity.getmyaddress(object);
            }
        });
    }


    @Override
    public void getaddaddressaddada(Map<String, String> path, Map<String, String> data) {
        dingModel.getdaddressdata(path, data, new Idingmodel.ModelAddAddressCallBack() {
            @Override
            public void Onsuccess(Object object) {
                cityAddActivity.getaddaddress(object);
            }
        });
    }
}
