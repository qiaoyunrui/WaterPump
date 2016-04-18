package com.juhezi.waterpump.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juhezi.waterpump.Activities.MainActivity;
import com.juhezi.waterpump.Network.HttpUtil;
import com.juhezi.waterpump.Other.Config;
import com.juhezi.waterpump.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * SignInFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:57
 * <p/>
 * 登录界面
 */
public class SignInFragment extends BaseAppFragment {

    private static final String TAG = "SignInFragment";

    private String result;

    private View rootView;
    private TextInputLayout mTILusername;
    private TextInputLayout mTILpassword;
    private FloatingActionButton mFABsignin;
    private ProgressBar mProgressBar;
    private EditText mETip;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x100) {
                mProgressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("code").equals("200")) {
                        showToast("登陆成功");
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        mSharedPreferences = getActivity().getSharedPreferences(Config.SIGN_INFO, Context.MODE_PRIVATE);
                        editor = mSharedPreferences.edit();
                        editor.putBoolean(Config.SIGN_STATE, true); //改变登录状态
                        editor.commit();
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    } else {
                        showToast("登陆失败");
                    }
                } catch (JSONException e) {
                    showToast("解析数据出错");
                    e.printStackTrace();
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_signin, container, false);
        mTILusername = (TextInputLayout) rootView.findViewById(R.id.username_signin);
        mTILpassword = (TextInputLayout) rootView.findViewById(R.id.password_signin);
        mFABsignin = (FloatingActionButton) rootView.findViewById(R.id.button_signin);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_login);
        mETip = (EditText) rootView.findViewById(R.id.ip_signin);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        configureTIL(mTILusername, 5, mFABsignin);
        configureTIL(mTILpassword, 6, mFABsignin);
        initEvent();
    }

    private void initEvent() {
        mFABsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = mTILusername.getEditText().getText().toString();
                final String password = mTILpassword.getEditText().getText().toString();
                final String ip = mETip.getText().toString();

                final String url = Config.HTTP + ip + Config.SIGN_URL;
                final String param = "username=" + username + "&password=" + password;
                Log.i(TAG, url);
                Log.i(TAG, param);
                mProgressBar.setVisibility(View.VISIBLE);   //显示进度条
                new Thread() {
                    @Override
                    public void run() {
                        result = HttpUtil.postRequest(url, param);
                        Log.i(TAG, "result is " + result);
                        handler.sendEmptyMessage(0x100);
                    }
                }.start();
            }
        });
    }

    @Override
    public String getTitle() {
        return "登录";
    }

    /**
     * 配置输入框
     */
    public static void configureTIL(final TextInputLayout textInputLayout, final int length, final View button) {

        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < length) {
                    textInputLayout.setError("长度不能小于" + length + "个字符");
                    textInputLayout.setErrorEnabled(true);
                    button.setClickable(false);
                } else {
                    textInputLayout.setErrorEnabled(false);
                    button.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void showToast(String content) {
        Toast.makeText(getActivity().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }
}
