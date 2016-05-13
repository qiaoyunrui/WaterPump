package com.juhezi.waterpump.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.juhezi.waterpump.DataStructure.Node;
import com.juhezi.waterpump.Other.Config;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * SocketService
 * <p/>
 * 与服务器进行长连接
 *
 * @author: Juhezi
 * @time: 2016/5/13 11:31
 */
public class SocketService extends Service {

    private static final String TAG = "SocketService";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 每次服务启动的时候调用
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}
