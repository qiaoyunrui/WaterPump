package com.juhezi.waterpump.Callbacks;

import com.videogo.openapi.bean.EZUserInfo;

/**
 * OnSignIn
 *
 * @author: Juhezi
 * @time: 2016/5/13 20:46
 */
public interface OnSignIn {

    /**
     * 登录成功回调
     * @param mEZUserInfo
     */
    public void onSignIn(EZUserInfo mEZUserInfo);

}
