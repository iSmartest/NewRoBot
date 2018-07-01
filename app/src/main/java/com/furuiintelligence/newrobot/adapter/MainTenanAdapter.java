package com.furuiintelligence.newrobot.adapter;
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
//        Name 赋睿智能-Date 2018/5/29-Time 10:05
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.content.Context;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.moudle.MpbListEntity;
import com.furuiintelligence.newrobot.utils.DateUtils;

import java.util.List;

public class MainTenanAdapter extends AbsAdapter<MpbListEntity.DataBean.MaintianListBean> {
    public MainTenanAdapter(Context context, int layoutId, List<MpbListEntity.DataBean.MaintianListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void bindResponse(ViewHolder holder, MpbListEntity.DataBean.MaintianListBean data) {

        ((TextView) holder.getView(R.id.main_item_Time)).setText("维修时间：\t\t" + DateUtils.transferLongToDate(Long.parseLong(data.getDate()+"")));
        ((TextView) holder.getView(R.id.main_item_Project)).setText("维修项目：\t\t" + data.getItem());
        ((TextView) holder.getView(R.id.main_item_pir)).setText("维修价格：\t\t" + (String) data.getPrice()+ "￥");


    }
}
