package com.juhezi.waterpump.Activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.juhezi.waterpump.Adapters.VPAndTLAdapter;
import com.juhezi.waterpump.DataStructure.LoopList;
import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Fragments.BaseFragment;
import com.juhezi.waterpump.Fragments.GraphFragment;
import com.juhezi.waterpump.Fragments.PersonFragment;
import com.juhezi.waterpump.Fragments.VideoFragment;
import com.juhezi.waterpump.Other.Config;
import com.juhezi.waterpump.Services.SocketService;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private static final String TAB_NAME_1 = "流量图像";
    private static final String TAB_NAME_2 = "监控视频";
    private static final String TAB_NAME_3 = "个人中心";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private Intent mIntent;
    private Intent serviceIntent;
    private SharedPreferences mSharedPreferences;

    private List<String> tabNames;
    private List<BaseFragment> fragments;
    private List<Integer> tabIcs;

    private PersonFragment mPersonFragment;
    private GraphFragment mGraphFragment;
    private VideoFragment mVideoFragment;

    public SocketService.SocketBinder mSocketBinder;
    private boolean signState = false;   //登录状态

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mSocketBinder = (SocketService.SocketBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

//        turn2SignActivity();

        initView();

        initTablayoutAndViewPager();

        initEvent();

        startSocketService();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSocketService();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initEvent() {

    }

    private void initData() {
        mSharedPreferences = getSharedPreferences(Config.SIGN_INFO, MODE_PRIVATE);
        signState = mSharedPreferences.getBoolean(Config.SIGN_STATE, false);
        mIntent = new Intent(this, SignInAndUpActivity.class);
    }

    /**
     * 初始化Tablayout和ViewPager
     */
    private void initTablayoutAndViewPager() {
        tabNames = new ArrayList<>();
        fragments = new ArrayList<>();
        tabIcs = new ArrayList<>();
        tabNames.add(TAB_NAME_1);
        tabNames.add(TAB_NAME_2);
        tabNames.add(TAB_NAME_3);

        mVideoFragment = new VideoFragment();
        mPersonFragment = new PersonFragment();
        mGraphFragment = new GraphFragment();
        fragments.add(mGraphFragment);
        fragments.add(mVideoFragment);
        fragments.add(mPersonFragment);

        tabIcs.add(R.drawable.ic_send_black_24dp);
        tabIcs.add(R.drawable.ic_live_tv_black_24dp);
        tabIcs.add(R.drawable.ic_person_black_24dp);

        VPAndTLAdapter mVpAndTLAdapter = new VPAndTLAdapter(getSupportFragmentManager(), this, null, fragments, tabIcs);
        mViewPager.setAdapter(mVpAndTLAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * 切换到登录注册界面
     */
    private void turn2SignActivity() {
        if (!signState) { //如果还没有登录
            startActivity(mIntent);
            finish();
        }
    }

    /**
     * 开启长连接服务
     */
    private void startSocketService() {
        serviceIntent = new Intent(this, SocketService.class);
        bindService(serviceIntent,mConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 停止service
     */
    private void stopSocketService() {
        unbindService(mConnection);
    }

    public JSONArray getData() {
        if (mSocketBinder != null) {
            return mSocketBinder.getData();
        } else {
            return null;
        }
        //return mSocketBinder.getData();
    }

}
