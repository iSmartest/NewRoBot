package com.furuiintelligence.newrobot.ui.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 启动页
 */
public class StartActivity extends BaseActivity {


    @BindView(R.id.mStart_Tx)
    TextView mStartTx;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        mStartTx.setAnimation(animation);
        animation.setDuration(3000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ActivityUtils.getInstance().showActivity(StartActivity.this, LoginActivity.class);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
