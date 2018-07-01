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
//        Name 赋睿智能-Date 2018/5/24-Time 13:10
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.cotract.HomeModelContract;
import com.furuiintelligence.newrobot.mvp.moudle.HomeEntity;
import com.furuiintelligence.newrobot.mvp.moudle.model.HomeMoudel;
import com.furuiintelligence.newrobot.mvp.moudle.model.HomeMoudelImpl;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

public class HomePresenter implements HomeModelContract.HomeContractPresenter {

    private HomeModelContract.View homeView;
    private HomeMoudel homeMoudel;

    public HomePresenter(HomeModelContract.View homeView) {
        this.homeView = homeView;
        this.homeView.setPresenter(this);
        homeMoudel = new HomeMoudelImpl();
    }

    @Override
    public void start(Map<String, String> map) {
        homeView.showCashProfitDialog();
        homeMoudel.getHomeMoudel(new MyNetWorkCallback<HomeEntity>() {
            @Override
            public void onSuccess(HomeEntity homeEntity) {

                homeView.dissmiCashProfitDialog();
                homeView.setRequest(homeEntity);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                homeView.showErrMessage(errorMsg);
            }
        }, map);
    }

    @Override
    public void starts(Map<String, String> maps) {

    }
}
