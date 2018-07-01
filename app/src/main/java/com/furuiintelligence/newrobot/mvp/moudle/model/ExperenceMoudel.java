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
//        Name 赋睿智能-Date 2018/5/22-Time 8:33
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.base.BaseModel;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienCeEntity;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienceListEntity;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

public interface ExperenceMoudel extends BaseModel {

    void getExperienCeMoudel(MyNetWorkCallback<ExperienceListEntity> callback, Map<String, String> map);

    void getExperienCeMoudels(MyNetWorkCallback<ExperienCeEntity> callback, Map<String, String> eMap);
}
