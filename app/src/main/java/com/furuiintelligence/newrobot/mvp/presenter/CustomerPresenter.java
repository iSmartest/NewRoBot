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
//        Name 赋睿智能-Date 2018/5/9-Time 11:53
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.util.Log;

import com.furuiintelligence.newrobot.mvp.cotract.CustomerContract;
import com.furuiintelligence.newrobot.mvp.moudle.InfromationEntity;
import com.furuiintelligence.newrobot.mvp.moudle.model.CustomerImpl;
import com.furuiintelligence.newrobot.mvp.moudle.model.CustomerMoudle;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.Map;

public class CustomerPresenter implements CustomerContract.CustomerContractPresenter {


    private static final String TAG = "CustomerPresenter";
    CustomerMoudle customerMoudle;
    CustomerContract.View contractView;

    public CustomerPresenter(CustomerContract.View contractView) {

        this.contractView = contractView;
        this.contractView.setPresenter(this);
        customerMoudle = new CustomerImpl();
    }

    @Override
    public void start(Map<String, String> map) {
        contractView.showCashProfitDialog();
        customerMoudle.getCustomerMoudle(new MyNetWorkCallback<InfromationEntity>() {
            @Override
            public void onSuccess(InfromationEntity infromationEntity) {
                Log.e(TAG, "onSuccess: ----------------------------------------");
                contractView.setRequest(infromationEntity);
                contractView.dissmiCashProfitDialog();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                contractView.showErrMessage(errorMsg);
                Log.e(TAG, "onError: +++++++++++++++++++++++++++");
            }
        }, map);
    }

    @Override
    public void starts(Map<String, String> maps) {

    }
}
