package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.juhezi.waterpump.R;
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

    private EZUserInfo mEzUserInfo;
    private View rootView;
    private Button signInEzvizButton;
    private Button signOutEzvizButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person, container, false);
        signInEzvizButton = (Button) rootView.findViewById(R.id.signInEzvizButton);
        signOutEzvizButton = (Button) rootView.findViewById(R.id.signOutEzvizButton);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initEvent();
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
            }
        });
    }

}
