package com.juhezi.waterpump.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;

import com.juhezi.waterpump.R;
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
    private List<Integer> tabIcs;
    private SpannableString spannableString;
    private Context mContext;


    public VPAndTLAdapter(FragmentManager fm, Context mContext, List<String> tabNames, List<BaseFragment> fragments, List<Integer> tabIcs) {
        super(fm);
        this.mContext = mContext;
        this.fragments = fragments;
        this.tabNames = tabNames;
        this.tabIcs = tabIcs;
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
        if (tabNames == null) {     //不设置文字
            spannableString = new SpannableString(" ");
        } else {
            spannableString = new SpannableString(" " + tabNames.get(position));
        }
        if (tabIcs != null) {    //设置图标
            spannableString.setSpan(new ImageSpan(mContext, tabIcs.get(position)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

}
