package com.furuiintelligence.newrobot.mvp.net;

/**
 * Created by xingge on 2017/9/27.
 */

public class HttpFactroy {
    public static IHttp create() {
        return OkHttpUtils.getInstance();
    }
//    public static IHttp retrofit(){
//        return RetrofitUtils.newInstence();
//    }


}
