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
//        Name 赋睿智能-Date 2018/5/9-Time 11:51
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.base.BasePresenter;
import com.furuiintelligence.newrobot.mvp.base.BaseView;
import com.furuiintelligence.newrobot.mvp.moudle.InfromationEntity;

public interface CustomerContract {

    interface View extends BaseView<CustomerContractPresenter> {
        void showCashProfitDialog();

        void dissmiCashProfitDialog();

        void setRequest(InfromationEntity infromationEntity);

        void showErrMessage(String err);

    }

    interface CustomerContractPresenter extends BasePresenter {

    }


}
