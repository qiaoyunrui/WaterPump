package com.juhezi.waterpump.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.juhezi.waterpump.Adapters.VPAndTLAdapter;
import com.juhezi.waterpump.R;
import com.juhezi.waterpump.fragments.BaseFragment;
import com.juhezi.waterpump.fragments.GraphFragment;
import com.juhezi.waterpump.fragments.PersonFragment;
import com.juhezi.waterpump.fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private static final String TAB_NAME_1 = "流量图像";
    private static final String TAB_NAME_2 = "监控视频";
    private static final String TAB_NAME_3 = "个人中心";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> tabNames;
    private List<BaseFragment> fragments;

    private PersonFragment mPersonFragment;
    private GraphFragment mGraphFragment;
    private VideoFragment mVideoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    /**
     * 初始化Tablayout和ViewPager
     */
    private void initTablayoutAndViewPager() {
        tabNames = new ArrayList<>();
        fragments = new ArrayList<>();
        tabNames.add(TAB_NAME_1);
        tabNames.add(TAB_NAME_2);
        tabNames.add(TAB_NAME_3);


        /*for (String tabName : tabNames) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabName).setIcon(getResources().getDrawable(R.mipmap.ic_launcher)));
        }*/

        mVideoFragment = new VideoFragment();
        mPersonFragment = new PersonFragment();
        mGraphFragment = new GraphFragment();
        fragments.add(mGraphFragment);
        fragments.add(mVideoFragment);
        fragments.add(mPersonFragment);

        VPAndTLAdapter mVpAndTLAdapter = new VPAndTLAdapter(getSupportFragmentManager(), tabNames, fragments);
        mViewPager.setAdapter(mVpAndTLAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
