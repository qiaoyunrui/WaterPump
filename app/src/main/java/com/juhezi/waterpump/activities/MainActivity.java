package com.juhezi.waterpump.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.juhezi.waterpump.Adapters.VPAndTLAdapter;
import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Fragments.BaseFragment;
import com.juhezi.waterpump.Fragments.GraphFragment;
import com.juhezi.waterpump.Fragments.PersonFragment;
import com.juhezi.waterpump.Fragments.VideoFragment;
import com.juhezi.waterpump.Other.Config;

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
    private SharedPreferences mSharedPreferences;

    private List<String> tabNames;
    private List<BaseFragment> fragments;
    private List<Integer> tabIcs;

    private PersonFragment mPersonFragment;
    private GraphFragment mGraphFragment;
    private VideoFragment mVideoFragment;

    private boolean signState = false;   //登录状态


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        turn2SignActivity();

        initView();

        initTablayoutAndViewPager();

        initEvent();

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

        for (int i = 0; i < 3; i++) {
            tabIcs.add(R.mipmap.ic_launcher);
        }
        VPAndTLAdapter mVpAndTLAdapter = new VPAndTLAdapter(getSupportFragmentManager(), this, tabNames, fragments, tabIcs);
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

}
