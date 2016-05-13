package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Widgets.LineView;

/**
 * GraphFragment1
 *
 * @author: Juhezi
 * @time: 2016/5/11 11:37
 */
public class GraphFragment1 extends BaseFragment {

    private static final String TAG = "GraphFragment1";

    private View rootView;
    private LineView mLineView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph_1, container, false);
        mLineView = (LineView) rootView.findViewById(R.id.line_graph_1);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
