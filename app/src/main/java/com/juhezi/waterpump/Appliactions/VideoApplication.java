package com.juhezi.waterpump.Appliactions;

import android.app.Application;

import com.juhezi.waterpump.Other.Config;
import com.videogo.openapi.EZOpenSDK;

/**
 * VideoApplication
 *
 * @author: Juhezi
 * @time: 2016/5/12 9:47
 */
public class VideoApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化SDK
        EZOpenSDK.initLib(this, Config.APPKEY,null);
    }
}
