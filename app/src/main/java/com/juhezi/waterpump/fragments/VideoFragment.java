package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.juhezi.waterpump.Other.Config;
import com.juhezi.waterpump.R;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZUserInfo;

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

    private EZUserInfo mEZUserInfo;
    private EZOpenSDK mEZOpenSDK;
    private View rootView;

    private Button videoButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_video, container, false);
        videoButton = (Button) rootView.findViewById(R.id.videoButton);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initEvent();
    }

    private void initEvent() {
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
