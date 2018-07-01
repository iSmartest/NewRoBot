package com.furuiintelligence.newrobot.ui.activity.home.intformtion;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.adapter.InfromationAdapter;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.cotract.CustomerContract;
import com.furuiintelligence.newrobot.mvp.moudle.InfromationEntity;
import com.furuiintelligence.newrobot.mvp.presenter.CustomerPresenter;
import com.furuiintelligence.newrobot.ui.view.IconCenterEditText;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.furuiintelligence.newrobot.utils.SpannableUtlis;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.C_TYPE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.KEY_WORD;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.RESULTCODE_L;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.RESULTCODE_ONE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE;

/**
 * 客户资料***详情
 */
public class InformationActivity extends BaseActivity implements CustomerContract.View {


    private CustomerContract.CustomerContractPresenter customerContractPresenter;
    private static final String TAG = "InformationActivity";
    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.mIconCenter)
    IconCenterEditText mIconCenter;
    @BindView(R.id.labels)
    LabelsView labels;
    @BindView(R.id.information_ListView)
    ListView informationListView;
    @BindView(R.id.infoLayouts)
    AutoLinearLayout infoLayouts;
    private ArrayList<String> mLabels = new ArrayList<>();
    private Map<String, String> cusMap;
    private InfromationAdapter infromationAdapter;
    private List<InfromationEntity.DataBean.CustomerListBean> infoList;
    private int PAGR_SIZE = 1;
    private Dialog dialog;
    private String type;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_information;
    }

    @Override
    protected void loadData() {

        new SpannableUtlis("输入条件搜索", mIconCenter);
        /**
         * 判断输入框是否为空  空则不让Labels相应点击选中事件
         */
        String trim = mIconCenter.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            labels.setSelectType(LabelsView.SelectType.NONE);
        }
        mIconCenter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /**
                 * 动态监听输入框是否为null
                 * null 则不触发点击事件
                 * 不为null则相应点击事件
                 */
                if (!TextUtils.isEmpty(s.toString())) {
                    labels.setSelectType(LabelsView.SelectType.SINGLE);
                } else {
                    labels.setSelectType(LabelsView.SelectType.NONE);
                }
            }
        });

        labels.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                if (label.getText().toString().equals("日期")) {
//                    labels.setSelectType(LabelsView.SelectType.SINGLE);
                    type = position + 1 + "";//拿到日期标签索引
                    showDateDialog(DateUtil.getDateForString("1990-01-01"));//输入框赋值


                }
            }
        });


//        Labels选中监听
        labels.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {

                if (title.getText().toString().equals("今日客户")) {
                    cusMap.put(TYPE, position + 2 + "");
                    cusMap.put(KEY_WORD, mIconCenter.getText().toString().trim());
                    infoList.clear();
                    customerContractPresenter.start(cusMap);//今日查询/根据Type
                } else {
                    cusMap.put(TYPE, position + 1 + "");
                    cusMap.put(KEY_WORD, mIconCenter.getText().toString().trim());
                    infoList.clear();
                    customerContractPresenter.start(cusMap);//往期根据 不同Type来查询*日期查询在showDateDialog
                }
            }
        });


        //ListView点击事件*传值
        informationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                InfromationEntity.DataBean.CustomerListBean listBean = new InfromationEntity.DataBean.CustomerListBean();
                listBean.setAddress(infoList.get(position).getAddress());
                listBean.setAge(infoList.get(position).getAge());
                listBean.setCarType(infoList.get(position).getCarType());
                listBean.setContent(infoList.get(position).getContent());
                listBean.setDate(infoList.get(position).getDate());
                listBean.setHobby(infoList.get(position).getHobby());
                listBean.setId(infoList.get(position).getId());
                listBean.setIdCard(infoList.get(position).getIdCard());
                listBean.setName(infoList.get(position).getName());
                listBean.setPhone(infoList.get(position).getPhone());
                listBean.setPic(infoList.get(position).getPic());
                listBean.setSex(infoList.get(position).getSex());
                listBean.setWork(infoList.get(position).getWork());
                bundle.putString("info", new Gson().toJson(listBean));
                bundle.putString("title", title.getText().toString());
                ActivityUtils.getInstance().showActivity(InformationActivity.this, bundle, EditActivity.class);

            }
        });
    }

    private void showDateDialog(List<Integer> date) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mIconCenter.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
                cusMap.put(TYPE, type);
                cusMap.put(KEY_WORD, mIconCenter.getText().toString().trim());
                infoList.clear();
                customerContractPresenter.start(cusMap);//往期根据时间查询

            }

            @Override
            public void onCancel() {

            }
        })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void initView() {
//        获取上个页面传过来的title
        title.setText(getTitle("title"));
        titleBack.setVisibility(View.VISIBLE);
        infoList = new ArrayList<>();//实例化标签对象集合
        cusMap = new HashMap<>();//实例化Map集合
        customerContractPresenter = new CustomerPresenter(this);//实力化Presenter接口类
        //适配器
        infromationAdapter = new InfromationAdapter(this, R.layout.informatin_listview_item_layout, infoList);
        informationListView.setAdapter(infromationAdapter);

//        判断当前title来加载不同数据
        if (title.getText().toString().equals("今日客户")) {
            cusMap.put(C_TYPE, "1");//测试数据 需要动态修改
            cusMap.put(PAGE, String.valueOf(PAGR_SIZE));
            mLabels.clear();
            mLabels.add("工作");
            mLabels.add("车型");
            mLabels.add("地址");
            mLabels.add("姓名");
            labels.setLabels(mLabels);
        } else {
            cusMap.put(C_TYPE, "2");//测试数据 需要动态修改
            cusMap.put(PAGE, String.valueOf(PAGR_SIZE));
            mLabels.clear();
            mLabels.add("日期");
            mLabels.add("工作");
            mLabels.add("车型");
            mLabels.add("地址");
            mLabels.add("姓名");
            labels.setLabels(mLabels);

        }

        customerContractPresenter.start(cusMap);//开启网络请求*往期或者今日客户
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

    @Override
    public void setPresenter(CustomerContract.CustomerContractPresenter customerContractPresenter) {
        this.customerContractPresenter = customerContractPresenter;
    }

    @Override
    public void showCashProfitDialog() {
        //显示加载进度条
    }

    @Override
    public void dissmiCashProfitDialog() {
        //隐藏进度条
    }

    @Override
    public void setRequest(InfromationEntity infromationEntity) {


        switch (infromationEntity.getResultCode()) {
            case RESULTCODE_L:
                if (infromationEntity.getMsg().equals("暂无数据")){
                    showToast(this, infromationEntity.getMsg());
                    informationListView.setVisibility(View.GONE);
                    mIconCenter.setVisibility(View.GONE);
                    labels.setVisibility(View.GONE);
                    infoLayouts.setVisibility(View.VISIBLE);

                }else {

                    informationListView.setVisibility(View.VISIBLE);
                    mIconCenter.setVisibility(View.VISIBLE);
                    labels.setVisibility(View.VISIBLE);
                    infoLayouts.setVisibility(View.GONE);
                    infoList.addAll(infromationEntity.getData().getCustomerList());
                    infromationAdapter.notifyDataSetChanged();
                }



                break;
            case RESULTCODE_ONE:
                showToast(this, infromationEntity.getMsg());
                informationListView.setVisibility(View.GONE);
                mIconCenter.setVisibility(View.GONE);
                labels.setVisibility(View.GONE);
                infoLayouts.setVisibility(View.VISIBLE);

                break;
        }

    }

    @Override
    public void showErrMessage(String err) {
        if (err.equals("Failed to connect to")) {
            showToast(this, "未能连接到服务器");
        }
        Log.e(TAG, "showErrMessage: " + err);
    }


}
