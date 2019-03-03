package com.example.weidu_app.myadd.model;

import java.util.HashMap;
import java.util.Map;

public interface Idingmodel
{
    void getdmyressdata(Map<String, String> path, ModelMyAddressCallBack modelMyAddressCallBack);
    interface ModelMyAddressCallBack
    {
        void Onsuccess(Object object);
    }

        void getdaddressdata(Map<String, String> path, Map<String, String> data, ModelAddAddressCallBack modelAddAddressCallBack);
        interface ModelAddAddressCallBack
        {
            void Onsuccess(Object object);
        }
}
