package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Widgets.LineView;

/**
 * GraphFragment3
 *
 * @author: Juhezi
 * @time: 2016/5/11 11:47
 */
public class GraphFragment3 extends BaseFragment {

    private static final String TAG = "GraphFragment3";

    private View rootView;
    private LineView mLineView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph_3, container, false);
        mLineView = (LineView) rootView.findViewById(R.id.line_graph_3);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
