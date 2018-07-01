package com.furuiintelligence.newrobot.mvp.moudle.model;
//                    _ooOoo_
//                   o8888888o
//                   88" . "88
//                   (| -_- |)
//                   O\  =  /O
//                ____/`---'\____
//              .'  \\|     |//  `.
//             /  \\|||  :  |||//  \
//            /  _||||| -:- |||||-  \
//            |   | \\\  -  /// |   |
//            | \_|  ''\---/''  |   |
//            \  .-\__  `-`  ___/-. /
//          ___`. .'  /--.--\  `. . ___
//       ."" '<  `.___\_<|>_/___.'  >' "".
//      | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//      \  \ `-.   \_ __\ /__ _/   .-` /  /
// ======`-.____`-.___\_____/___.-`____.-'======
//                    `=---='
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//          佛祖保佑     永不宕机      永无BUG
//        Name 赋睿智能-Date 2018/5/9-Time 11:46
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.util.Log;

import com.furuiintelligence.newrobot.mvp.moudle.InfromationEntity;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;


import java.util.Map;

import static com.furuiintelligence.newrobot.mvp.config.Url.CUSTOMER;


public class CustomerImpl implements CustomerMoudle {
    private static final String TAG = "CustomerImpl";

    @Override
    public void getCustomerMoudle(MyNetWorkCallback<InfromationEntity> callback, Map<String, String> map) {


        iHttp.post(CUSTOMER, map, callback);

    }
}
