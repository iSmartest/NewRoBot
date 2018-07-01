package com.furuiintelligence.newrobot.ui.activity.prodctsales;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Comparison_Activity extends BaseActivity {


    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.compGridView)
    GridView compGridView;


    @Override
    protected int getLauoutId() {
        return R.layout.activity_comparison_;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        title.setText(getTitle("mTitle"));
        titleBack.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.title_Back)
    public void onViewClicked() {
        finish();
    }
}
