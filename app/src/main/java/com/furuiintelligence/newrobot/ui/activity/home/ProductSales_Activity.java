package com.furuiintelligence.newrobot.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.ui.activity.prodctsales.A_list_Of_Products_Activity;
import com.furuiintelligence.newrobot.ui.activity.prodctsales.Comparison_Activity;
import com.furuiintelligence.newrobot.ui.activity.prodctsales.Sales_Training_Activity;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 产品销售
 */
public class ProductSales_Activity extends BaseActivity {


    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.audi_Layout)
    AutoLinearLayout audiLayout;
    @BindView(R.id.mAudi_Layout)
    AutoLinearLayout mAudiLayout;
    @BindView(R.id.mAudi_Layouts)
    AutoLinearLayout audiLayouts;
    @BindView(R.id.mWord_Layout)
    AutoLinearLayout mWordLayout;
    @BindView(R.id.mVido_Layout)
    AutoLinearLayout mVidoLayout;
    @BindView(R.id.mAudi)
    ImageView mAudi;
    private Bundle bundle;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_product_sales_;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        title.setText(R.string.product_sales);
        titleBack.setVisibility(View.VISIBLE);
        bundle = new Bundle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_Back, R.id.audi_Layout, R.id.mAudi_Layout, R.id.mAudi_Layouts, R.id.mWord_Layout, R.id.mVido_Layout, R.id.mAudi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.audi_Layout:
                bundle.putString("mTitle", "一汽大众");
                ActivityUtils.getInstance().showActivity(this, bundle, A_list_Of_Products_Activity.class);
                break;
            case R.id.mAudi_Layout:
                bundle.putString("mTitle", "进口奥迪");
                ActivityUtils.getInstance().showActivity(this, bundle, A_list_Of_Products_Activity.class);
                break;
            case R.id.mAudi_Layouts:
                bundle.putString("mTitle", "AudiSport");
                ActivityUtils.getInstance().showActivity(this, bundle, A_list_Of_Products_Activity.class);
                break;
            case R.id.mWord_Layout:
                bundle.putString("mTitle", "培训文档");
                ActivityUtils.getInstance().showActivity(this, bundle, Sales_Training_Activity.class);
                break;
            case R.id.mVido_Layout:
                bundle.putString("mTitle", "培训视频");
                ActivityUtils.getInstance().showActivity(this, bundle, Sales_Training_Activity.class);
                break;
            case R.id.mAudi:
                bundle.putString("mTitle", "精品比较");
                ActivityUtils.getInstance().showActivity(this, bundle, Comparison_Activity.class);
                break;
        }
    }

}
