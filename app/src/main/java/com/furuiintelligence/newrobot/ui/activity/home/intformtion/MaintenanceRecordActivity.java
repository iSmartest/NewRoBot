package com.furuiintelligence.newrobot.ui.activity.home.intformtion;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.adapter.MainTenanAdapter;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.cotract.MainTenanMoudelContract;
import com.furuiintelligence.newrobot.mvp.moudle.MpbListEntity;
import com.furuiintelligence.newrobot.mvp.presenter.MainTenancePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.C_ID;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.FINAL_PAGR;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE_SIZI;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE;

public class MaintenanceRecordActivity extends BaseActivity implements MainTenanMoudelContract.View {


    private static final String TAG = "MaintenanceRecordActivi";
    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.mPtrLayout)
    PtrClassicFrameLayout mPtrLayout;
    private MainTenanMoudelContract.MainTenanPresenter mainTenanPresenter;
    private List<MpbListEntity.DataBean.MaintianListBean> mpList;
    private Map<String, String> mainMap;
    private MainTenanAdapter mainTenanAdapter;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_maintenance_record;
    }

    @Override
    protected void loadData() {



    }

    @Override
    protected void initView() {
        titleBack.setVisibility(View.VISIBLE);
        title.setText(getTitle("title"));
        mpList = new ArrayList<>();
        mainMap = new HashMap<>();
        Bundle extras = getIntent().getExtras();
        String cid = extras.getString("cid");
        mainMap.put(C_ID, cid);
        mainMap.put(PAGE, PAGE_SIZI + "");

        switch (getTitle("title")) {
            case "汽车保养":
                mainMap.put(TYPE, "1");
                break;
            case "维修记录":
                mainMap.put(TYPE, "2");
                break;
            case "购车情况":
                mainMap.put(TYPE, "3");
                break;
        }
        mainTenanPresenter = new MainTenancePresenter(this);
        mainTenanPresenter.start(mainMap);
        mainTenanAdapter = new MainTenanAdapter(this, R.layout.maintenan_list_item,mpList);
        mListView.setAdapter(mainTenanAdapter);


    }

    @OnClick(R.id.title_Back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setPresenter(MainTenanMoudelContract.MainTenanPresenter mainTenanPresenter) {
        this.mainTenanPresenter = mainTenanPresenter;
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dissmiDialog() {

    }


    @Override
    public void showErrMsg(String err) {
        if (err.equals("Failed to connect to")) {
            showToast(this, "未能连接到服务器");
        }
        Log.e(TAG, "showErrMsg: " + err);
    }

    @Override
    public void setRequst(MpbListEntity entity) {


        switch (entity.getResultCode()){
            case 0:
                if (entity.getMsg().equals("查询成功")){
                    mpList.addAll(entity.getData().getMaintianList());
                    mainTenanAdapter.notifyDataSetChanged();
                }else {
                    showToast(this,entity.getMsg());
                }

                break;
            case 1:
                showToast(this,entity.getMsg());
                break;
        }
    }
}
