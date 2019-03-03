package com.example.weidu_app.dingdan.presenter;

import java.util.HashMap;

public interface Idingpresenter
{
    void getdpdata(HashMap<String, Object> hashMap, int addressId, double totalPrice, HashMap<String, Object> ordermap);
    void getFindding(HashMap<String, Object> hashMap,  HashMap<String, Object> data);
}
