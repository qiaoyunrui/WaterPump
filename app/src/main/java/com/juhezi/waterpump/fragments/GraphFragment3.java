package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.DataStructure.Node;
import com.juhezi.waterpump.Other.Config;
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
    private Node node;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph_3, container, false);
        mLineView = (LineView) rootView.findViewById(R.id.line_graph_3);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initLineView();
    }

    private void initLineView() {

        mLineView.setLoopListNumOfValues(4);
        mLineView.setNumOfXPoints(6);
        mLineView.pushName("管网水池压力一");
        mLineView.pushName("管网水池压力二");
        mLineView.pushName("管网水池压力三");
        mLineView.pushName("管网水池压力四");

    }

    @Override
    public void handleBundle(Bundle bundle) {
        super.handleBundle(bundle);
        if(bundle != null) {
            node = (Node) bundle.getSerializable(Config.NODE_BUNDLE_KEY);
            node.deleteX(3);
        }
        if(mLineView != null) {
            mLineView.pushNode(node);
        }
    }
}
