package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Widgets.LineView;

/**
 * GraphFragment4
 *
 * @author: Juhezi
 * @time: 2016/5/11 11:52
 */
public class GraphFragment4 extends BaseFragment {

    private static final String TAG = "GraphFragment4";

    private View rootView;
    private LineView mLineView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph_4, container, false);
        mLineView = (LineView) rootView.findViewById(R.id.line_graph_4);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
