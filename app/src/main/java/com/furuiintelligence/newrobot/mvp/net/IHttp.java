package com.furuiintelligence.newrobot.mvp.net;


import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;


/**
 * Created by xingge on 2017/9/27.
 */

public interface IHttp {
    <T> void get(String url, Map<String, String> params, MyNetWorkCallback<T> callback);

    <T> void post(String url, Map<String, String> params, MyNetWorkCallback<T> callback);

    void upload();

    void download();

    void loadImage();

}
