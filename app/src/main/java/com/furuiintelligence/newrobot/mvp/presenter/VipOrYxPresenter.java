package com.furuiintelligence.newrobot.mvp.presenter;
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
//        Name 赋睿智能-Date 2018/5/25-Time 8:40
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.cotract.VipOrYxCMoudelContract;
import com.furuiintelligence.newrobot.mvp.moudle.VipOrYxEntity;
import com.furuiintelligence.newrobot.mvp.moudle.model.VipOrYxMoudel;
import com.furuiintelligence.newrobot.mvp.moudle.model.VipOrYxMoudelImpl;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

public class VipOrYxPresenter implements VipOrYxCMoudelContract.VipOrYxContractPresenter {
    private VipOrYxCMoudelContract.View vipOrYxView;
    private VipOrYxMoudel vipOrYxMoudel;

    public VipOrYxPresenter(VipOrYxCMoudelContract.View vipOrYxView) {
        this.vipOrYxView = vipOrYxView;
        this.vipOrYxView.setPresenter(this);
        vipOrYxMoudel = new VipOrYxMoudelImpl();
    }

    @Override
    public void start(Map<String, String> map) {
        vipOrYxView.showCashProfitDialog();
        vipOrYxMoudel.getVipOrYxMoudel(new MyNetWorkCallback<VipOrYxEntity>() {
            @Override
            public void onSuccess(VipOrYxEntity vipOrYxEntity) {
                vipOrYxView.setRequest(vipOrYxEntity);
                vipOrYxView.dissmiCashProfitDialog();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                vipOrYxView.showErrMessage(errorMsg);
            }
        }, map);
    }

    @Override
    public void starts(Map<String, String> maps) {

    }
}
