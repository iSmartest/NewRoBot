package com.furuiintelligence.newrobot.mvp.base;


import com.furuiintelligence.newrobot.mvp.net.HttpFactroy;
import com.furuiintelligence.newrobot.mvp.net.IHttp;

public interface BaseModel {
    public static IHttp iHttp = HttpFactroy.create();

}
