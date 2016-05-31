package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.Callbacks.Callback;
import com.juhezi.waterpump.DataStructure.Node;
import com.juhezi.waterpump.Other.Config;
import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Widgets.LineView;

/**
 * GraphFragment1
 *
 * @author: Juhezi
 * @time: 2016/5/11 11:37
 */
public class GraphFragment1 extends BaseFragment implements Callback{

    private static final String TAG = "GraphFragment1";

    private View rootView;
    private LineView mLineView;
    private Node node;

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
        initLineView();
    }

    private void initLineView() {

        mLineView.setLoopListNumOfValues(4);
        mLineView.setNumOfXPoints(6);
        mLineView.pushName("水管流量一");
        mLineView.pushName("水管流量二");
        mLineView.pushName("水管流量三");
        mLineView.pushName("水管流量四");

    }

    @Override
    public void handleBundle(Bundle bundle) {
        super.handleBundle(bundle);
        if(bundle != null) {
            node = (Node) bundle.getSerializable(Config.NODE_BUNDLE_KEY);
            node.deleteX(0);
        }
        if(mLineView != null) {
            mLineView.pushNode(node);
        }

    }
}
