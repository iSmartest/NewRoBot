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
//        Name 赋睿智能-Date 2018/5/22-Time 8:34
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.config.Url;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienCeEntity;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienceListEntity;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.HashMap;
import java.util.Map;

import static com.furuiintelligence.newrobot.mvp.config.Url.EXPERIENCELIST;
import static com.furuiintelligence.newrobot.mvp.config.Url.SALES_MANS;

public class ExperienCeImpl implements ExperenceMoudel {
    @Override
    public void getExperienCeMoudel(MyNetWorkCallback<ExperienceListEntity> callback, Map<String, String> map) {

        iHttp.post(EXPERIENCELIST, map, callback);
    }

    @Override
    public void getExperienCeMoudels(MyNetWorkCallback<ExperienCeEntity> callback, Map<String, String> eMap) {
        iHttp.post(SALES_MANS, eMap, callback);
    }
}
