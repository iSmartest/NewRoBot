package com.furuiintelligence.newrobot.ui.activity.prodctsales;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.mvp.config.Url.VIDEO_URL;

public class Sales_Training_Activity extends BaseActivity {


    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.nice_video_player)
    NiceVideoPlayer niceVideoPlayer;


    @Override
    protected int getLauoutId() {
        return R.layout.activity_sales__training_;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        title.setText(getTitle("mTitle"));
//        if (title.getText().toString().equals("视频资料")) {
//            //视频数据适配器
//        } else {
//            //文档数据适配器
//        }
        niceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // or NiceVideoPlayer.TYPE_NATIVE
        niceVideoPlayer.setUp(VIDEO_URL, null);

        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("测试视频");
//        controller.setImage(mImageUrl);
        niceVideoPlayer.setController(controller);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }
    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
}
