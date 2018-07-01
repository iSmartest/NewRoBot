package com.furuiintelligence.newrobot.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.ui.activity.home.intformtion.InformationActivity;
import com.furuiintelligence.newrobot.ui.activity.home.intformtion.VIPActivity;
import com.furuiintelligence.newrobot.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 客户资料
 */
public class CustomerActivity extends BaseActivity {

    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.customer_Todays)
    TextView customerTodays;
    @BindView(R.id.customer_Past)
    TextView customerPast;
    @BindView(R.id.customer_VIP)
    TextView customerVIP;
    @BindView(R.id.customer_Per)
    TextView customerPer;
    private ActivityUtils instance;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void loadData() {
        instance = ActivityUtils.getInstance();


    }

    @Override
    protected void initView() {
        title.setText(R.string.Customer);
        titleBack.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_Back, R.id.customer_Todays, R.id.customer_Past, R.id.customer_VIP, R.id.customer_Per})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.customer_Todays:
                //今日客户
                bundle.putString("title", customerTodays.getText().toString());
                instance.showActivity(this, bundle, InformationActivity.class);
                break;
            case R.id.customer_Past:
                //往期客户
                bundle.putString("title", customerPast.getText().toString());
                instance.showActivity(this, bundle, InformationActivity.class);
                break;
            case R.id.customer_VIP:
                //VIP
                bundle.putString("titles", customerVIP.getText().toString());
                instance.showActivity(this, bundle, VIPActivity.class);
                break;
            case R.id.customer_Per:
                //预成交
                bundle.putString("titles", customerPer.getText().toString());
                instance.showActivity(this, bundle, VIPActivity.class);
                break;
        }
    }
}
