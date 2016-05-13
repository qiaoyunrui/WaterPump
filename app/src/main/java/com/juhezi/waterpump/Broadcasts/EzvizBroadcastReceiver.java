package com.juhezi.waterpump.Broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.juhezi.waterpump.CallBacks.SignCallBack;
import com.videogo.constant.Constant;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZAccessToken;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZUserInfo;
import com.videogo.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * EzvizBroadcastReceiver
 *
 * @author: Juhezi
 * @time: 2016/5/12 9:56
 */
public class EzvizBroadcastReceiver extends BroadcastReceiver {

    private final static String TAG = "EzvizBroadcastReceiver";

    private EZOpenSDK mEzoPenSDK;
    private List<EZCameraInfo> cameraList = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String message = intent.getStringExtra("message_extra");
        if (action.equals(Constant.OAUTH_SUCCESS_ACTION)) {
            //保存token及token超时时间
            mEzoPenSDK = EZOpenSDK.getInstance();
            if (mEzoPenSDK != null) {
                EZAccessToken token = mEzoPenSDK.getEZAccessToken();
                mEzoPenSDK.setAccessToken(token.getAccessToken());
            }
        }
    }
}


