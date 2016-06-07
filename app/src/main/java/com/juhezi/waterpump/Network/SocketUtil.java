package com.juhezi.waterpump.Network;

import android.util.Log;

import com.juhezi.waterpump.Other.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * SocketUtil
 *
 * @author: 乔云瑞
 * @time: 2016/4/19 19:23
 */
public class SocketUtil {

    private static final String TAG = "SocketUtil";

    /**
     * 创建socket客户端，并接收数据
     */
    public static JSONArray socketClient() {
        JSONArray mJsonArray = null;
        try {
            Socket mSocket = new Socket(Config.DEFAULT_PORT, Config.PORT);
            mSocket.setSoTimeout(5000);    //5s超时
            BufferedReader mBufferedReader = new BufferedReader(
                    new InputStreamReader(
                            mSocket.getInputStream()));
            String line = mBufferedReader.readLine();   //获取一行数据
            Log.i(TAG, ">>>" + line);
            mJsonArray = new JSONArray(line);    //将其解析为一个JSON对象,一会进行修改
            mBufferedReader.close();
            mSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mJsonArray;
    }

}
