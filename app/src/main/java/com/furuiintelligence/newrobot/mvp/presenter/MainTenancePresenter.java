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
//        Name 赋睿智能-Date 2018/5/29-Time 9:13
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.widget.Toast;

import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.cotract.MainTenanMoudelContract;
import com.furuiintelligence.newrobot.mvp.moudle.MpbListEntity;
import com.furuiintelligence.newrobot.mvp.moudle.model.MainTenanMoudelImpl;
import com.furuiintelligence.newrobot.mvp.moudle.model.MainTenanceRecordMoudel;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

public class MainTenancePresenter implements MainTenanMoudelContract.MainTenanPresenter {


    private MainTenanMoudelContract.View mainView;
    private MainTenanceRecordMoudel moudel;

    public MainTenancePresenter(MainTenanMoudelContract.View mainView) {
        this.mainView = mainView;
        this.mainView.setPresenter(this);
        moudel = new MainTenanMoudelImpl();
    }

    @Override
    public void start(Map<String, String> map) {
        mainView.showDialog();
        moudel.getMainTenanMoudel(new MyNetWorkCallback<MpbListEntity>() {
            @Override
            public void onSuccess(MpbListEntity mpbListEntity) {
                    mainView.setRequst(mpbListEntity);
                    mainView.dissmiDialog();

            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }
        }, map);
    }

    @Override
    public void starts(Map<String, String> maps) {

    }
}
