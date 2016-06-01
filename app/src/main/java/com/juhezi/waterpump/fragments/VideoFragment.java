package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.juhezi.waterpump.Other.Config;
import com.juhezi.waterpump.R;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZConstants;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZUserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * VideoFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:52
 * <p/>
 * 监控界面
 */
public class VideoFragment extends BaseFragment {

    private static final String TAG = "VideoFragment";

    private List<EZCameraInfo> cameraList;
    private EZOpenSDK mEZOpenSDK;
    private View rootView;
    private SurfaceView videoSurface;
    private EZCameraInfo mEZCameraInfo;
    private EZPlayer mEZPlayer;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_SUCCESS) {

                Log.i(TAG, "start");
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_video, container, false);
        videoSurface = (SurfaceView) rootView.findViewById(R.id.video_video_sv);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        mEZOpenSDK = EZOpenSDK.getInstance();
        cameraList = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                try {
                    cameraList = mEZOpenSDK.getCameraList(0, 5);
                    Log.i(TAG, cameraList.toString());
                    mEZCameraInfo = cameraList.get(0);
                    if (mEZCameraInfo != null) {
                        mEZPlayer = mEZOpenSDK.createPlayer(getContext(), mEZCameraInfo.getCameraId());
                        mEZPlayer.setSurfaceHold(videoSurface.getHolder());
                        mEZPlayer.setHandler(mHandler);
                        mEZPlayer.startRealPlay();
                    }
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mEZPlayer != null) {
            mEZPlayer.startRealPlay();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mEZPlayer != null) {
            mEZPlayer.stopRealPlay();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mEZPlayer != null) {
            mEZPlayer.stopRealPlay();
        }
        mEZOpenSDK.releasePlayer(mEZPlayer);
    }
}
