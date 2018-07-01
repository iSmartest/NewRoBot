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
//        Name 赋睿智能-Date 2018/5/29-Time 9:06
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.config.Url;
import com.furuiintelligence.newrobot.mvp.moudle.MpbListEntity;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

public class MainTenanMoudelImpl implements MainTenanceRecordMoudel {
    @Override
    public void getMainTenanMoudel(MyNetWorkCallback<MpbListEntity> callback, Map<String, String> mainMap) {
        iHttp.post(Url.MPBLIST, mainMap, callback);
    }
}
