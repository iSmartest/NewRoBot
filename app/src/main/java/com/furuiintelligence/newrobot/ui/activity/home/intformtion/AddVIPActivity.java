package com.furuiintelligence.newrobot.ui.activity.home.intformtion;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.config.Url;
import com.furuiintelligence.newrobot.mvp.net.OkHttpUtils;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.app.APP.context;
import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_ADDRESS;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_AGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_CONTENT;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_IDCARD;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_INCOME;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_NAME;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_PHONE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_SEX;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_TRADE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.M_WORK;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;

/**
 * 客户页面
 */
public class AddVIPActivity extends BaseActivity {

    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_OK)
    TextView titleOK;
    @BindView(R.id.addName)
    EditText addName;
    @BindView(R.id.addAge)
    EditText addAge;
    @BindView(R.id.addSex)
    EditText addSex;
    @BindView(R.id.addPhone)
    EditText addPhone;
    @BindView(R.id.addId)
    EditText addId;
    @BindView(R.id.addWork)
    EditText addWork;
    @BindView(R.id.addIndustry)
    EditText addIndustry;
    @BindView(R.id.addIncome)
    EditText addIncome;
    @BindView(R.id.addRess)
    EditText addRess;
    @BindView(R.id.addIntention)
    EditText addIntention;

    private Map<String, String> addMap = null;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_add_vip;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        title.setText("添加客户");
        titleBack.setVisibility(View.VISIBLE);
        titleOK.setText("确定");
        titleOK.setVisibility(View.VISIBLE);
        addMap = new HashMap<>();


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
                break;
            case R.id.title_OK:

                /**
                 * 获取输入框内容并去除空格
                 */
                getAdd(addName.getText().toString().trim()//姓名
                        , addAge.getText().toString().trim()//年龄
                        , addSex.getText().toString().trim()//性别
                        , addPhone.getText().toString().trim()//电话
                        , addId.getText().toString().trim()//身份证
                        , addWork.getText().toString().trim()//工作
                        , addIndustry.getText().toString().trim()//行业
                        , addIncome.getText().toString().trim()//收入
                        , addRess.getText().toString().trim()//地址
                        , addIntention.getText().toString().trim()//购车意向
                );

                break;
        }
    }

    private void getAdd(String name, String age, String sex, String phone, String idCrad
            , String work, String ind, String income, String address, String in) {

        if (!TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(age)
                && !TextUtils.isEmpty(sex)
                && !TextUtils.isEmpty(phone)
                && !TextUtils.isEmpty(idCrad)
                && !TextUtils.isEmpty(work)
                && !TextUtils.isEmpty(ind)
                && !TextUtils.isEmpty(income)
                && !TextUtils.isEmpty(address)
                && !TextUtils.isEmpty(in)) {
            //输入框都不为空 添加进map集合，对应字段
            addMap.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
            addMap.put(M_NAME, name);
            addMap.put(M_AGE, age);
            if (sex.equals("男")) {
                addMap.put(M_SEX, "1");
            } else {
                addMap.put(M_SEX, "2");
            }

            addMap.put(M_PHONE, phone);
            addMap.put(M_IDCARD, idCrad);
            addMap.put(M_WORK, work);
            addMap.put(M_TRADE, ind);
            addMap.put(M_INCOME, income);
            addMap.put(M_ADDRESS, address);
            addMap.put(M_CONTENT, in);
            strar(addMap);//开启网络请求

        } else {
            showToast(this, "内容不可为空");
        }
    }

    private void strar(Map<String, String> map) {
        OkHttpUtils.getInstance().post(Url.ADD_CUSTOMER, map, new MyNetWorkCallback<AddBean>() {

            @Override
            public void onSuccess(AddBean addBean) {
                showToast(context, addBean.getMsg());//拿到数据
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                //报错信息
            }
        });
    }

    class AddBean {

        /**
         * data : {}
         * resultCode : 0
         * msg : 添加成功
         */

        private DataBean data;
        private int resultCode;
        private String msg;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public class DataBean {
        }
    }
}
