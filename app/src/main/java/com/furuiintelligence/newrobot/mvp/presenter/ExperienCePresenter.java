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
//        Name 赋睿智能-Date 2018/5/22-Time 8:37
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import com.furuiintelligence.newrobot.mvp.cotract.ExperienCeContract;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienCeEntity;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienceListEntity;
import com.furuiintelligence.newrobot.mvp.moudle.model.ExperenceMoudel;
import com.furuiintelligence.newrobot.mvp.moudle.model.ExperienCeImpl;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

public class ExperienCePresenter implements ExperienCeContract.ExperienCeContractPresenter {


    private ExperienCeContract.View exPerView;
    private ExperenceMoudel experenceMoudel;

    public ExperienCePresenter(ExperienCeContract.View exPerView) {
        this.exPerView = exPerView;
        this.exPerView.setPresenter(this);
        experenceMoudel = new ExperienCeImpl();
    }


    @Override
    public void start(Map<String, String> map) {
        exPerView
                .showCashProfitDialog();
        experenceMoudel.getExperienCeMoudel(new MyNetWorkCallback<ExperienceListEntity>() {
            @Override
            public void onSuccess(ExperienceListEntity experienCeEntity) {
                exPerView.setRequest(experienCeEntity);
                exPerView.dissmiCashProfitDialog();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

                exPerView.showErrMessage(errorCode, errorMsg);
            }
        }, map);
    }

    @Override
    public void starts(Map<String, String> maps) {
        experenceMoudel.getExperienCeMoudels(new MyNetWorkCallback<ExperienCeEntity>() {
            @Override
            public void onSuccess(ExperienCeEntity experienCeEntity) {
                exPerView.setRequests(experienCeEntity);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                exPerView.showErrMessage(errorCode, errorMsg);
            }
        }, maps);

    }
}
