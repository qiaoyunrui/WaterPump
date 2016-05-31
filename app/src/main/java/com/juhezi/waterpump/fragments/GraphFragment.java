package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.waterpump.Activities.MainActivity;
import com.juhezi.waterpump.Adapters.VPAndTLAdapter;
import com.juhezi.waterpump.DataStructure.LoopList;
import com.juhezi.waterpump.DataStructure.Node;
import com.juhezi.waterpump.Network.SocketUtil;
import com.juhezi.waterpump.Other.Config;
import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Widgets.LineView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private static final String TAB_NAME_1 = "分水房流量";
    private static final String TAB_NAME_2 = "管网水池流量";
    private static final String TAB_NAME_3 = "管网水池压力";
    private static final String TAB_NAME_4 = "管网水池液位";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> tabName;
    private List<BaseFragment> fragments;

    private GraphFragment1 mGraphFragment1;
    private GraphFragment2 mGraphFragment2;
    private GraphFragment3 mGraphFragment3;
    private GraphFragment4 mGraphFragment4;
    //————————————————————————————————————————————

    private View rootView;

    private Timer mTimer;
    private Node node;
    private Bundle bundle;
    private JSONArray mJsonArray;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabLayoutGraph);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPagerGraph);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        initTablayoutAndViewPager();

        timer();

    }


    /**
     * 初始化Tablayout和ViewPager
     */
    private void initTablayoutAndViewPager() {
        tabName = new ArrayList<>();
        fragments = new ArrayList<>();
        tabName.add(TAB_NAME_1);
        tabName.add(TAB_NAME_2);
        tabName.add(TAB_NAME_3);
        tabName.add(TAB_NAME_4);

        mGraphFragment1 = new GraphFragment1();
        mGraphFragment2 = new GraphFragment2();
        mGraphFragment3 = new GraphFragment3();
        mGraphFragment4 = new GraphFragment4();

        fragments.add(mGraphFragment1);
        fragments.add(mGraphFragment2);
        fragments.add(mGraphFragment3);
        fragments.add(mGraphFragment4);

        VPAndTLAdapter mVpAndTLAdapter = new VPAndTLAdapter(
                getActivity().getSupportFragmentManager(),
                getContext(),
                tabName,
                fragments,
                null);
        mViewPager.setAdapter(mVpAndTLAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    private void timer() {
        mTimer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                bundle = new Bundle();
                /*long seed = System.currentTimeMillis();
                for (int i = 0; i < 4; i++) {
                    node.addValue(new Random(seed + i * i * 10).nextDouble() * 100);
                    seed = new Random(seed).nextLong() * 1000;
                }*/
//                Node node = new Node(new Date().getSeconds(), result);
                mJsonArray = ((MainActivity)getActivity()).getData();   //获取到JSON数据
                node = new Node(new Date().getSeconds());
                if(mJsonArray != null) {
                    for(int i = 0;i < 15;i++) {
                        try {
                            node.addValue(mJsonArray.getInt(i));    //向数据节点添加数据
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                bundle.putSerializable(Config.NODE_BUNDLE_KEY, node);
                for(BaseFragment fragment : fragments) {
                        fragment.handleBundle(bundle);
                }
            }
        };
        mTimer.schedule(task, 0, Config.PERIOD);
    }
}
