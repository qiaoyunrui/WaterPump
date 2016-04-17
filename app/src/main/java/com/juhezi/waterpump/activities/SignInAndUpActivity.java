package com.juhezi.waterpump.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.juhezi.waterpump.R;
import com.juhezi.waterpump.Fragments.BaseAppFragment;
import com.juhezi.waterpump.Fragments.SignInFragment;

/**
 * SignInAndUpActivity
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:57
 * <p/>
 * 提供注册登录的Activity
 */
public class SignInAndUpActivity extends BaseActivity {

    private static final String TAG = "SignInAndUpActivity";
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private BaseAppFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        initData();

        initToolbar();

        initView();

        initEvent();

    }

    void initToolbar() {
    }

    void initData() {

    }

    void initView() {
        mFrameLayout = (FrameLayout) findViewById(R.id.fragment);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
    }

    void initEvent() {
        fragment = new SignInFragment();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(fragment.getTitle());
        getFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
    }


}
