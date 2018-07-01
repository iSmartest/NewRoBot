package com.furuiintelligence.newrobot.mvp.cotract;
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
//        Name 赋睿智能-Date 2018/5/29-Time 9:07
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.base.BasePresenter;
import com.furuiintelligence.newrobot.mvp.base.BaseView;
import com.furuiintelligence.newrobot.mvp.moudle.MpbListEntity;

public interface MainTenanMoudelContract {


    interface View extends BaseView<MainTenanPresenter> {
        void showDialog();

        void dissmiDialog();

        void showErrMsg(String err);

        void setRequst(MpbListEntity entity);
    }


    interface MainTenanPresenter extends BasePresenter {

    }

}
