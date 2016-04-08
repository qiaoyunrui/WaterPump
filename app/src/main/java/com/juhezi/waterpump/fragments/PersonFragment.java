package com.juhezi.waterpump.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.R;

/**
 * PersonFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:52
 *
 * 个人中心
 *
 */
public class PersonFragment extends BaseFragment {

    private static final String TAG = "PersonFragment";

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person, container, false);
        return rootView;
    }

}
