package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.DataStructure.LoopList;
import com.juhezi.waterpump.DataStructure.Node;
import com.juhezi.waterpump.Network.SocketUtil;
import com.juhezi.waterpump.Other.Config;
import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Widgets.LineView;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    private LineView mLineView;

    private Timer mTimer;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x101) {
                Bundle bundle = msg.getData();
                Node node = (Node) bundle.getSerializable(Config.NODE_BUNDLE_KEY);
                mLineView.pushNode(node);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        mLineView = (LineView) rootView.findViewById(R.id.line_graph);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        timer();
    }

    private void timer() {

        mTimer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int result = SocketUtil.socketClient();
                Bundle bundle = new Bundle();
//                Node node = new Node(new Date().getSeconds(), new Random(System.currentTimeMillis()).nextDouble() * LineView.MAX_VALUE);
                Node node = new Node(new Date().getSeconds(), result);
                bundle.putSerializable(Config.NODE_BUNDLE_KEY, node);
                Message msg = new Message();
                msg.what = 0x101;
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
        };
        mTimer.schedule(task, 0, LineView.PERIOD);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
