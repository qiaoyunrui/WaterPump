package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.R;

/**
 * GraphFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:51
 * <p/>
 * 曲线界面
 */
public class GraphFragment extends BaseFragment {

    private static final String TAG = "GraphFragment";

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
