package com.furuiintelligence.newrobot.ui.activity.train;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 问题设置
 */
public class ProblemMainActivity extends BaseActivity {

    private static final String TAG = "ProblemMainActivity";
    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_OK)
    TextView titleOK;
    @BindView(R.id.mProble)
    EditText mProble;
    @BindView(R.id.mAnswer)
    EditText mAnswer;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_problem_main;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        title.setText("问题设置");
        titleBack.setVisibility(View.VISIBLE);
        titleOK.setVisibility(View.VISIBLE);
        titleOK.setText("确定");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_Back, R.id.title_OK})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.title_OK:
                showToast(this, "确定");
                break;
        }
    }
}
