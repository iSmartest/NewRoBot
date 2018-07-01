package com.furuiintelligence.newrobot.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.moudle.SalePerfectinEntity;
import com.furuiintelligence.newrobot.mvp.net.OkHttpUtils;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.furuiintelligence.newrobot.utils.SpannableUtlis;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.app.APP.getContext;
import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.AGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.BRANDID;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IMAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IS_ORPERFECT;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NAME;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NUMBER;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PHONE_NUMBER;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.RESULTCODE_L;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.RESULTCODE_ONE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALEINDEX;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SEX;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SUCCESSINDEX;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE_VALUE_O;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE_VALUE_ONE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.WORK;
import static com.furuiintelligence.newrobot.mvp.config.Url.SALE;

/**
 * 完善资料
 */
public class PerfectinformationActivity extends BaseActivity {


    private static final String TAG = "PerfectinformationActiv";

    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.perfrct_Name)
    EditText perfrctName;
    @BindView(R.id.perfrct_Sex)
    EditText perfrctSex;
    @BindView(R.id.perfrct_Age)
    EditText perfrctAge;
    @BindView(R.id.perfect_Pos)
    EditText perfectPos;
    @BindView(R.id.perfect_Number)
    EditText perfectNumber;
    @BindView(R.id.per_ButPost)
    Button perButPost;
    @BindView(R.id.per_Logos)
    EditText perLogos;
    private String[] str = {"姓名", "性别", "年龄", "岗位", "工号", "品牌"};
    private Map<String, String> perMap = null;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_perfectinformation;
    }

    @Override
    protected void loadData() {
        new SpannableUtlis(str[0], perfrctName);
        new SpannableUtlis(str[1], perfrctSex);
        new SpannableUtlis(str[2], perfrctAge);
        new SpannableUtlis(str[3], perfectPos);
        new SpannableUtlis(str[4], perfectNumber);
        new SpannableUtlis(str[5], perLogos);
    }

    @Override
    protected void initView() {
//        getWindows(this);
        title.setText("完善资料");
        titleBack.setVisibility(View.VISIBLE);
        perMap = new HashMap<>();
        perMap.put(SALE_ID, "1");//用户ID  登录返回  需修改
        perMap.put(TYPE, "1");//1完善资料 2修改
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.per_ButPost, R.id.title_Back})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.per_ButPost:

                setPerfectin(perfrctName.getText().toString().trim()
                        , perfrctSex.getText().toString().trim()
                        , perfrctAge.getText().toString().trim()
                        , perfectPos.getText().toString().trim()
                        , perfectNumber.getText().toString().trim()
                        , perLogos.getText().toString().trim());
//                ActivityUtils.getInstance().showActivity(PerfectinformationActivity.this, MainActivity.class);
                break;
            case R.id.title_Back:
                ActivityUtils.getInstance().showActivity(PerfectinformationActivity.this, LoginActivity.class);
                break;


        }

    }

    private void setPerfectin(String perName, String perSex, String perAge, String perPos, String perNum, String perLogs) {

        if (!TextUtils.isEmpty(perName)
                && !TextUtils.isEmpty(perSex)
                && !TextUtils.isEmpty(perAge)
                && !TextUtils.isEmpty(perPos)
                && !TextUtils.isEmpty(perNum)
                && !TextUtils.isEmpty(perLogs)) {
            perMap.put(NAME, perName);
            if (perSex.equals("男")) {
                perMap.put(SEX, TYPE_VALUE_O);
            } else {
                perMap.put(SEX, TYPE_VALUE_ONE);
            }

            perMap.put(AGE, perAge);
            perMap.put(WORK, perPos);
            perMap.put(NUMBER, perNum);
            if (perLogs.equals("奥迪")) {
                perMap.put(BRANDID, TYPE_VALUE_ONE);
            }

            OkHttpUtils.getInstance().post(SALE, perMap, new MyNetWorkCallback<SalePerfectinEntity>() {
                @Override
                public void onSuccess(SalePerfectinEntity salePerfectinEntity) {

                    switch (salePerfectinEntity.getResultCode()) {
                        case RESULTCODE_L:

                            SalePerfectinEntity.DataBean.SaleBean sale = salePerfectinEntity.getData().getSale();
                            Log.e(TAG, "onSuccess: " + salePerfectinEntity.getMsg());
                            //不为空的后续操作

                            sp.put(NAME, sale.getName());
                            sp.put(SEX, String.valueOf(sale.getSex()));
                            sp.put(AGE, String.valueOf(sale.getAge()));
                            sp.put(SALE_ID, String.valueOf(sale.getId()));
                            sp.put(PHONE_NUMBER, String.valueOf(sale.getPhone()));
                            sp.put(WORK, String.valueOf(sale.getWork()));
                            sp.put(NUMBER, String.valueOf(sale.getNumber()));
                            sp.put(IMAGE, String.valueOf(sale.getImage()));
                            sp.put(IS_ORPERFECT, String.valueOf(sale.getIsOrPerfect()));
                            sp.put(SUCCESSINDEX, String.valueOf(sale.getSuccessIndex()));
                            sp.put(SALEINDEX, String.valueOf(sale.getSaleIndex()));
                            ActivityUtils.getInstance().showActivity(PerfectinformationActivity.this, MainActivity.class);
                            finish();
                            break;

                        case RESULTCODE_ONE:
                            showToast(getContext(), salePerfectinEntity.getMsg());
                            break;
                    }
                }

                @Override
                public void onError(int errorCode, String errorMsg) {
                    Log.e(TAG, "onError: " + errorMsg);

                }
            });


        } else {
            Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String isOp = (String) sp.getSharedPreference(IS_ORPERFECT, "");
        if (isOp.equals("已完善")) {
            ActivityUtils.getInstance().showActivity(PerfectinformationActivity.this, MainActivity.class);
            finish();
        }
    }
}
