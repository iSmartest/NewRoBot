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
//        Name 赋睿智能-Date 2018/5/9-Time 14:13
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.moudle.InfromationEntity;
import com.furuiintelligence.newrobot.utils.DateUtils;

import java.util.List;

public class InfromationAdapter extends AbsAdapter<InfromationEntity.DataBean.CustomerListBean> {

    private static final String TAG = "InfromationAdapter";

    public InfromationAdapter(Context context, int layoutId, List<InfromationEntity.DataBean.CustomerListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void bindResponse(ViewHolder holder, InfromationEntity.DataBean.CustomerListBean data) {

        ((TextView) holder.getView(R.id.infrom_item_Name)).setText(data.getName());
        ((TextView) holder.getView(R.id.infrom_item_Time)).setText(DateUtils.transferLongToDate(data.getDate()));
        ((TextView) holder.getView(R.id.infrom_item_Context)).setText(data.getContent());
    }
}
