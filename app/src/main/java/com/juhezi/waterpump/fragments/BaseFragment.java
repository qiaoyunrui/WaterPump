package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.juhezi.waterpump.Callbacks.Callback;
import com.juhezi.waterpump.Callbacks.OnSignInListener;

/**
 * BaseFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:20
 */
public class BaseFragment extends Fragment implements Callback {

    private final static String TAG = "BaseFragment";

    private OnSignInListener mOnSignInListener;  //登录监听器

    public void setOnSignInListener(OnSignInListener onSignInListener) {
        this.mOnSignInListener = onSignInListener;
    }

    @Override
    public void handleBundle(Bundle bundle) {
    }
}
