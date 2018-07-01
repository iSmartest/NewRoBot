package com.furuiintelligence.newrobot.ui.activity.home.intformtion;


import android.os.Bundle;
import android.os.VibrationEffect;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.donkingliang.labels.LabelsView;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.adapter.GalleryAdapter;
import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.cotract.VipOrYxCMoudelContract;
import com.furuiintelligence.newrobot.mvp.moudle.VipOrYxEntity;
import com.furuiintelligence.newrobot.mvp.presenter.VipOrYxPresenter;
import com.furuiintelligence.newrobot.ui.activity.MainActivity;
import com.furuiintelligence.newrobot.ui.view.CopyOfMyRecyclerView;
import com.furuiintelligence.newrobot.ui.view.GlideCircleTransform;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.app.APP.getContext;
import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE_SIZI;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE_VALUE_ONE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE_VALUE_TWO;

/**
 * Vip // 预成交 页面
 */
public class VIPActivity extends BaseActivity implements VipOrYxCMoudelContract.View {

    private static final String TAG = "VIPActivity";
    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_Layout)
    RelativeLayout titleLayout;
    @BindView(R.id.vip_icon)
    ImageView vipIcon;
    @BindView(R.id.vip_Name)
    TextView vipName;
    @BindView(R.id.vip_Write)
    ImageView vipWrite;
    @BindView(R.id.vipNames)
    TextView vipNames;
    @BindView(R.id.vipSex)
    TextView vipSex;
    @BindView(R.id.vipAge)
    TextView vipAge;
    @BindView(R.id.vipAddsees)
    TextView vipAddsees;
    @BindView(R.id.vipPhone)
    TextView vipPhone;
    @BindView(R.id.vipWork)
    TextView vipWork;
    @BindView(R.id.vipId)
    TextView vipId;
    @BindView(R.id.vip_Add)
    TextView vipAdd;

    @BindView(R.id.vip_LabelView)
    LabelsView vipLabelView;
    @BindView(R.id.mCopyRecyclerView)
    CopyOfMyRecyclerView mCopyRecyclerView;
    private ArrayList<String> lab = new ArrayList<>();
    private List<VipOrYxEntity.DataBean.CustListBean> vipList = new ArrayList<>();
    private GalleryAdapter galleryAdapter;
    private Map<String, String> vipMap = new HashMap<>();
    private VipOrYxCMoudelContract.VipOrYxContractPresenter vipOrYxContractPresenter;
    private int pos = 0;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void loadData() {
        lab.add("汽车保养");
        lab.add("汽车服务");
        lab.add("维修记录");
        lab.add("购车情况");
        lab.add("汽车推荐");
        lab.add("生日提醒");
        lab.add("汽车保险");
        vipLabelView.setLabels(lab);
        vipMap.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
        vipMap.put(PAGE, PAGE_SIZI + "");
        vipOrYxContractPresenter = new VipOrYxPresenter(this);

        switch (title.getText().toString()) {
            case "VIP客户":
                vipMap.put(TYPE, TYPE_VALUE_ONE);
                vipOrYxContractPresenter.start(vipMap);
                Log.e(TAG, "loadData: ");
                break;
            case "预成交客户":
                vipMap.put(TYPE, TYPE_VALUE_TWO);
                vipOrYxContractPresenter.start(vipMap);
                Log.e(TAG, "loadData: 1");
                break;
        }

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mCopyRecyclerView.setLayoutManager(linearLayoutManager);
        galleryAdapter = new GalleryAdapter(this, vipList);

        mCopyRecyclerView.setAdapter(galleryAdapter);
        galleryAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {


            @Override
            public void onItemClick(View view, int position) {
                pos = position;
                vipName.setText(vipList.get(position).getName());
                vipNames.setText("姓名：" + vipList.get(position).getName());
                vipAddsees.setText("地址：" + vipList.get(position).getAddress());
                vipAge.setText("年龄：" + vipList.get(position).getAge());
                vipPhone.setText("手机：" + vipList.get(position).getPhone());

                vipWork.setText("职业：" + vipList.get(position).getWork());
                vipId.setText("身份证：" + vipList.get(position).getIdCard());
                Glide.with(APP.context)
                        .load(vipList.get(position).getPic())
                        .transform(new GlideCircleTransform(APP.context))
                        .into(vipIcon);
                switch (vipList.get(position).getSex()) {
                    case 1:
                        vipSex.setText("性别：男");
                        break;
                    case 2:
                        vipSex.setText("性别：女");
                        break;


                }
            }
        });
//        mCopyRecyclerView.setOnItemScrollChangeListener(new CopyOfMyRecyclerView.OnItemScrollChangeListener() {
//            @Override
//            public void onChange(View view, int position) {
//
//                position= position+1;
//                Log.e(TAG, "onChange: "+position);
//
//            }
//        });

        vipLabelView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                Bundle bundle = new Bundle();
                switch (label.getText().toString()) {
                    case "汽车保养":
                        Log.e(TAG, "onLabelClick: " + label.getText().toString());
                        bundle.putString("title", label.getText().toString());
                        bundle.putString("cid", vipList.get(pos).getId() + "");
                        ActivityUtils.getInstance().showActivity(VIPActivity.this, bundle, MaintenanceRecordActivity.class);
                        break;
                    case "汽车服务":
                        showToast(VIPActivity.this, "暂无权限");
                        break;
                    case "维修记录":
                        bundle.putString("title", label.getText().toString());
                        bundle.putString("cid", vipList.get(pos).getId() + "");
                        ActivityUtils.getInstance().showActivity(VIPActivity.this, bundle, MaintenanceRecordActivity.class);

                        break;
                    case "购车情况":
                        bundle.putString("title", label.getText().toString());
                        bundle.putString("cid", vipList.get(pos).getId() + "");
                        ActivityUtils.getInstance().showActivity(VIPActivity.this, bundle, MaintenanceRecordActivity.class);

                        break;
                    case "汽车推荐":
                        showToast(VIPActivity.this, "暂无权限");
                        break;
                    case "生日提醒":
                        showToast(VIPActivity.this, "暂无权限");
                        break;
                    case "汽车保险":
                        showToast(VIPActivity.this, "暂无权限");
                        break;
                }
            }
        });

    }

    @Override
    protected void initView() {
        title.setText(getTitle("titles"));
        titleBack.setVisibility(View.VISIBLE);
        titleLayout.setBackgroundColor(getResources().getColor(R.color.colorTransParent));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_Back, R.id.vip_Write, R.id.vip_Add})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.vip_Write:
                //编辑页面

                VipOrYxEntity.DataBean.CustListBean custListBean = new VipOrYxEntity.DataBean.CustListBean();
                if (vipList.get(pos).getAddress().equals("")) {
                    showToast(this, "当前数据为空");
                    return;
                }
                custListBean.setAddress(vipList.get(pos).getAddress());
                custListBean.setAge(vipList.get(pos).getAge());
                custListBean.setId(vipList.get(pos).getId());
                custListBean.setIdCard(vipList.get(pos).getIdCard());
                custListBean.setName(vipList.get(pos).getName());
                custListBean.setPhone(vipList.get(pos).getPhone());
                custListBean.setPic(vipList.get(pos).getPic());
                custListBean.setSex(vipList.get(pos).getSex());
                custListBean.setWork(vipList.get(pos).getWork());
                bundle.putString("title", title.getText().toString());
                bundle.putString("json", new Gson().toJson(custListBean));
                ActivityUtils.getInstance().showActivity(this, bundle, EditActivity.class);
                break;
            case R.id.vip_Add:
                ActivityUtils.getInstance().showActivity(this, AddVIPActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(VipOrYxCMoudelContract.VipOrYxContractPresenter vipOrYxContractPresenter) {
        this.vipOrYxContractPresenter = vipOrYxContractPresenter;
    }

    @Override
    public void showCashProfitDialog() {

    }

    @Override
    public void dissmiCashProfitDialog() {

    }

    @Override
    public void setRequest(VipOrYxEntity vipOrYxEntity) {

        switch (vipOrYxEntity.getResultCode()){
            case 0:
                List<VipOrYxEntity.DataBean.CustListBean> listBeans = vipOrYxEntity.getData().getCustList();
                Log.e(TAG, "setRequest: "+listBeans.size());
        vipList.addAll(listBeans);
        galleryAdapter.notifyDataSetChanged();
                break;
            case 1:
                showToast(this,vipOrYxEntity.getMsg());
                break;
        }

    }

    @Override
    public void showErrMessage(String err) {
        if (err.equals("Failed to connect to /192.168.1.11:8081")) {
            showToast(getContext(), "未能连接到服务器");
        }
        Log.e(TAG, "showErrMessage: " + err);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vipOrYxContractPresenter.start(vipMap);
    }
}
