package com.juhezi.waterpump.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * SignInFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:57
 * <p>
 * 登录
 */
public class SignInFragment extends BaseAppFragment {

    private static final String TAG = "SignInFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle() {
        return "登录";
    }
}
