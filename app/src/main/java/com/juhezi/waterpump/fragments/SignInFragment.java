package com.juhezi.waterpump.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.R;

/**
 * SignInFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:57
 * <p/>
 * 登录
 */
public class SignInFragment extends BaseAppFragment {

    private static final String TAG = "SignInFragment";

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_signin, container, false);
        return rootView;
    }

    @Override
    public String getTitle() {
        return "登录";
    }
}
