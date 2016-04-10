package com.juhezi.waterpump.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.juhezi.waterpump.R;
import com.juhezi.waterpump.fragments.SignInFragment;

/**
 * SignInAndUpActivity
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:57
 * <p>
 * 提供注册登录的Activity
 */
public class SignInAndUpActivity extends BaseActivity {

    private static final String TAG = "SignInAndUpActivity";
    private Toolbar mToolbar;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_sign);
        initData();
        initToolbar();
        initView();
        initEvent();
    }

    void initToolbar() {
        getSupportActionBar().setTitle("登录");
    }

    void initData() {
    }

    void initView() {
        mView = findViewById(R.id.fragment);
    }

    void initEvent() {

    }


}
