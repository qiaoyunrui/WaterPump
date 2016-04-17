package com.juhezi.waterpump.Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HttpUtil
 *
 * @author: 乔云瑞
 * @time: 2016/4/17 15:48
 * <p>
 * Http请求工具类
 */
public class HttpUtil {

    /**
     * @param path   URL
     * @param params 请求的参数
     * @return 网页返回的数据
     */
    public static String postRequest(String path, String params) {
        String result = "";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);
            OutputStream out = conn.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            if (conn.getResponseCode() == 200) {
                //获取响应的输入流对象
                InputStream is = conn.getInputStream();
//              //创建字符串输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                //定义读取的长度
                int len = 0;
                //定义缓冲区
                byte buffer[] = new byte[1024];
                //定义缓存区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    //根据读取长度写入os对象中
                    message.write(buffer, 0, len);
                }
                //释放资源
                is.close();
                message.close();
                //返回字符串
                result = new String(message.toByteArray());

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
