package com.example.weidu_app.dingdan.presenter;


import com.example.weidu_app.dingFragment.AllListFragment;
import com.example.weidu_app.dingdan.model.DingModel;
import com.example.weidu_app.dingdan.model.Idingmodel;

import java.util.HashMap;

public class DingPresenter implements Idingpresenter
{
    AllListFragment allListFragment;
    private final DingModel dingModel;

    public DingPresenter( AllListFragment allListFragment) {
        this.allListFragment = allListFragment;
        dingModel = new DingModel();
    }

    @Override
    public void getdpdata(HashMap<String, Object> hashMap, int addressId, double totalPrice, HashMap<String, Object> ordermap)
    {
        dingModel.getdmdata(hashMap, addressId, totalPrice, ordermap, new Idingmodel.DingCallBack() {
            @Override
            public void Onsuccess(Object object) {


            }
        });


    }

    @Override
    public void getFindding(HashMap<String, Object> hashMap, HashMap<String, Object> data) {
        dingModel.getfinding(hashMap, data, new Idingmodel.FindDingCallBack() {
            @Override
            public void Onsuccess(Object object) {
                allListFragment.getfingding(object);
            }
        });
    }
}
