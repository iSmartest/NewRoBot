package com.furuiintelligence.newrobot.ui.fm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.base.BaseFragment;
import com.furuiintelligence.newrobot.ui.activity.my.SeetingsActivity;
import com.furuiintelligence.newrobot.ui.activity.train.FieldRecordActivity;
import com.furuiintelligence.newrobot.ui.activity.train.RoBotIMActivity;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Name 赋睿智能
 * Date 2018/5/2
 * Time 16:36
 * 机器人页面
 */

public class TrainFragment extends BaseFragment {

    private static final String TAG = "TrainFragment";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_Img)
    ImageView titleImg;
    @BindView(R.id.fileid_Record)
    AutoLinearLayout fileidRecord;
    @BindView(R.id.traiNing)
    AutoLinearLayout traiNing;
    @BindView(R.id.mainTenance)
    AutoLinearLayout mainTenance;
    @BindView(R.id.insuyance_Claims)
    AutoLinearLayout insuyanceClaims;
    Unbinder unbinder;


    @Override
    protected int getLayoutId() {
        return R.layout.train_fragment;
    }

    @Override
    protected void initView(View view) {

        title.setText(R.string.myRobot);
        titleImg.setVisibility(View.VISIBLE);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title_Img, R.id.fileid_Record, R.id.traiNing, R.id.mainTenance, R.id.insuyance_Claims})
    public void onViewClicked(View view) {
        Bundle b = new Bundle();
        switch (view.getId()) {
            case R.id.title_Img:
                ActivityUtils.getInstance().showActivity(APP.context, b, SeetingsActivity.class);
                break;
            case R.id.fileid_Record:
                b.putString("im_title", "现场记录");
                ActivityUtils.getInstance().showActivity(APP.context, b, FieldRecordActivity.class);

                break;
            case R.id.traiNing:
                b.putString("im_title", "销售训练室");
                ActivityUtils.getInstance().showActivity(APP.context, b, RoBotIMActivity.class);
                break;
            case R.id.mainTenance:
                b.putString("im_title", "维修保养室");
                ActivityUtils.getInstance().showActivity(APP.context, b, RoBotIMActivity.class);
                break;
            case R.id.insuyance_Claims:
                b.putString("im_title", "保险理赔室");
                ActivityUtils.getInstance().showActivity(APP.context, b, RoBotIMActivity.class);
                break;
        }
    }


//
//    @OnClick(R.id.train_title_img)
//    public void onViewClicked() {
//        ActivityUtils.getInstance().showActivity(getActivity(), SeetingsActivity.class);
//    }
}
