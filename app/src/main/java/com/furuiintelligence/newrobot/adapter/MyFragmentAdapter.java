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
//        Name 赋睿智能-Date 2018/5/22-Time 9:56
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienceListEntity;
import com.furuiintelligence.newrobot.ui.view.GlideCircleTransform;
import com.furuiintelligence.newrobot.utils.DateUtils;

import java.util.List;

import static com.furuiintelligence.newrobot.mvp.config.Url.HTTP;

public class MyFragmentAdapter extends AbsAdapter<ExperienceListEntity.DataBean.ContentListBean> {

    Context context;
    private static final String TAG = "MyFragmentAdapter";

    public MyFragmentAdapter(Context context, int layoutId, List<ExperienceListEntity.DataBean.ContentListBean> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    public void bindResponse(ViewHolder holder, ExperienceListEntity.DataBean.ContentListBean data) {

        Glide.with(context).load(HTTP + data.getImage()).transform(new GlideCircleTransform(context))
                .into((ImageView) holder.getView(R.id.my_List_Item_Img));

        ((TextView) holder.getView(R.id.my_List_Item_Content)).setText(data.getContent());
        ((TextView) holder.getView(R.id.my_List_Item_Date)).setText(DateUtils.transferLongToDate(Long.parseLong(data.getData() + "")));

    }
}
