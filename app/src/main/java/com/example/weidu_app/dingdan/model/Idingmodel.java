package com.example.weidu_app.dingdan.model;

import java.util.HashMap;

public interface Idingmodel
{
    void getdmdata(HashMap<String, Object> hashMap, int addressId, double totalPrice, HashMap<String, Object> ordermap, DingCallBack dingCallBack);
    interface DingCallBack
    {
        void Onsuccess(Object object);
    }
    void getfinding(HashMap<String, Object> hashMap,  HashMap<String, Object> data, FindDingCallBack findDingCallBack);
    interface FindDingCallBack
    {
        void Onsuccess(Object object);
    }
}
