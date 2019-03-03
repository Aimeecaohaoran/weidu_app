package com.example.weidu_app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetStateUtil {

        /**
         * 获取手机网络状态
         * -1 :无网络
         *  0 :  蜂窝网络;
         * 1  : wifi网络
         * @return
         * @param context 上下文对象
         */
        public static int getNetType(Context context) {
            int mState = -1;// 代表无网络
            //获取android系统提供的服务, 转换成链接管理类,这个类专门处理链接相关的东西
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //NetworkInfo封装了网络链接的信息
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if(activeNetworkInfo ==null){
                return mState;
            }
            int type = activeNetworkInfo.getType();

            if (type == ConnectivityManager.TYPE_WIFI) {//代表现在是wifi网络
                mState = 1;
            }else if(type == ConnectivityManager.TYPE_MOBILE ){
                mState = 0;
            }
            return mState;
        }
    }
