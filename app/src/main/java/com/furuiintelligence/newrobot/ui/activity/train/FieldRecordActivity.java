package com.furuiintelligence.newrobot.ui.activity.train;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 现场记录
 */
public class FieldRecordActivity extends BaseActivity {


    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_Img)
    ImageView titleImg;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_field_record;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        title.setText(getTitle("im_title"));
        titleBack.setVisibility(View.VISIBLE);
        titleImg.setVisibility(View.VISIBLE);
        titleImg.setImageResource(R.mipmap.exit);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_Back, R.id.title_Img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.title_Img:
                showToast(this, "记录导出");
                break;
        }
    }
}
