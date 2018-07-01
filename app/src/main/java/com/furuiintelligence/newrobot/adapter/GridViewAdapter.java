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
//        Name 赋睿智能-Date 2018/6/6-Time 17:23
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.moudle.GridViewBean;

import java.util.List;

public class GridViewAdapter extends AbsAdapter<GridViewBean> {

    Context context;
    public GridViewAdapter(Context context, int layoutId, List<GridViewBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void bindResponse(ViewHolder holder, GridViewBean data) {
        ImageView view = (ImageView) holder.getView(R.id.home_item_img);

        Glide.with(APP.getInstans()).load(data.getImg()).error(R.mipmap.ic_launcher).into(view);
        ((TextView)holder.getView(R.id.home_item_name)).setText(data.getName());
    }
}
