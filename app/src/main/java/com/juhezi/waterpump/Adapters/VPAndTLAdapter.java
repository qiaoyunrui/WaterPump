package com.juhezi.waterpump.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.juhezi.waterpump.fragments.BaseFragment;

import java.util.List;

/**
 * VPAndTLAdapter
 *
 * @author: 乔云瑞
 * @time: 2016/4/8 19:22
 * <p/>
 * ViewPager和TabLayout的Adapter
 */
public class VPAndTLAdapter extends FragmentStatePagerAdapter {

    private List<String> tabNames;
    private List<BaseFragment> fragments;

    public VPAndTLAdapter(FragmentManager fm, List<String> tabNames, List<BaseFragment> fragments) {
        super(fm);
        this.tabNames = tabNames;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }


}
