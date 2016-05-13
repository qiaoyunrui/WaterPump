package com.juhezi.waterpump.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.juhezi.waterpump.Callbacks.Callback;
import com.juhezi.waterpump.DataStructure.Node;
import com.juhezi.waterpump.Other.Config;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * BaseFragment
 *
 * @author: 乔云瑞
 * @time: 2016/4/7 21:20
 */
public class BaseFragment extends Fragment implements Callback{

    private final static String TAG = "BaseFragment";

    @Override
    public void handleBundle(Bundle bundle) {}
}
