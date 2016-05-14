package com.juhezi.waterpump.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juhezi.waterpump.Broadcasts.EzvizBroadcastReceiver;
import com.juhezi.waterpump.R;
import com.videogo.constant.Constant;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZUserInfo;


/**
 * PersonFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:52
 * <p/>
 * 个人中心
 */
public class PersonFragment extends BaseFragment {

    private static final String TAG = "PersonFragment";

    private View rootView;
    private Button signInEzvizButton;
    private Button signOutEzvizButton;
    private ImageView avatarImageView;
    private TextView pickNameTextView;

    private String pickName;
    private String avatarUrl;

    private EZUserInfo mEZUserInfo;
    private EZOpenSDK mEZOpenSDK;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x120) {
                pickNameTextView.setText(pickName);
                Glide.with(getContext()).load(avatarUrl).crossFade().into(avatarImageView);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person, container, false);
        signInEzvizButton = (Button) rootView.findViewById(R.id.signInEzvizButton);
        signOutEzvizButton = (Button) rootView.findViewById(R.id.signOutEzvizButton);
        avatarImageView = (ImageView) rootView.findViewById(R.id.person_avatar_iv);
        pickNameTextView = (TextView) rootView.findViewById(R.id.person_pickname_tv);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initEvent();
        initData();
    }

    private void initEvent() {
        signInEzvizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EZOpenSDK.getInstance().openLoginPage();
            }
        });
        signOutEzvizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EZOpenSDK.getInstance().logout();
                Snackbar.make(rootView, "登出成功", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 加载界面
     */
    private void initData() {
        new Thread() {
            @Override
            public void run() {
                Log.i(TAG, "start");
                mEZOpenSDK = EZOpenSDK.getInstance();
                try {
                    mEZUserInfo = mEZOpenSDK.getUserInfo();
                    pickName = mEZUserInfo.getNickname();
                    avatarUrl = mEZUserInfo.getAvatarUrl();
                    Log.i(TAG, mEZUserInfo.toString());
                } catch (BaseException e) {
                    Log.i(TAG, e.getErrorCode() + " " + e.getMessage());
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(0x120);
            }
        }.start();
    }

}
