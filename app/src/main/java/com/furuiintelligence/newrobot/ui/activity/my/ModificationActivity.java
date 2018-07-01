package com.furuiintelligence.newrobot.ui.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.config.Url;
import com.furuiintelligence.newrobot.mvp.moudle.InfromationEntity;
import com.furuiintelligence.newrobot.mvp.moudle.SalePerfectinEntity;
import com.furuiintelligence.newrobot.mvp.net.OkHttpUtils;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.app.APP.getContext;
import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.AGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IMAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IS_ORPERFECT;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NAME;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NUMBER;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PHONE_NUMBER;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALEINDEX;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SEX;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SUCCESSINDEX;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE_VALUE_O;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE_VALUE_ONE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.TYPE_VALUE_TWO;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.WORK;
import static com.furuiintelligence.newrobot.mvp.config.Url.SALE;

/**
 * 修改个人资料
 */
public class ModificationActivity extends BaseActivity {


    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_OK)
    TextView titleOK;
    @BindView(R.id.modif_Name)
    EditText modifName;
    @BindView(R.id.modif_Sex)
    EditText modifSex;
    @BindView(R.id.modif_Age)
    EditText modifAge;
    @BindView(R.id.modif_Work)
    EditText modifWork;
    @BindView(R.id.modif_Number)
    EditText modifNumber;
    private Map<String, String> modifMap;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_modification;
    }

    @Override
    protected void loadData() {

        modifMap = new HashMap<>();
        modifMap.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
        modifMap.put(TYPE, TYPE_VALUE_TWO);

    }

    @Override
    protected void initView() {
        titleBack.setVisibility(View.VISIBLE);
        titleOK.setVisibility(View.VISIBLE);
        title.setText("修改资料");
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
                //确定修改

                getEditTextString(modifName.getText().toString()
                        , modifSex.getText().toString()
                        , modifAge.getText().toString()
                        , modifWork.getText().toString()
                        , modifNumber.getText().toString());
                break;
        }
    }

    private void getEditTextString(String name, String sex, String agr, String work, String num) {
        if (TextUtils.isEmpty(name)
                || TextUtils.isEmpty(sex)
                || TextUtils.isEmpty(agr)
                || TextUtils.isEmpty(work)
                || TextUtils.isEmpty(num)) {
            showToast(this, "请填写完整资料");
            return;
        } else {
            modifMap.put(NAME, name);
            if (sex.equals("男")) {
                modifMap.put(SEX, TYPE_VALUE_O);
            } else {
                modifMap.put(SEX, TYPE_VALUE_ONE);
            }

            modifMap.put(AGE, agr);
            modifMap.put(WORK, work);
            modifMap.put(NUMBER, num);
            OkHttpUtils.getInstance().post(SALE, modifMap, new MyNetWorkCallback<SalePerfectinEntity>() {
                @Override
                public void onSuccess(SalePerfectinEntity salePerfectinEntity) {

                    SalePerfectinEntity.DataBean.SaleBean sale = salePerfectinEntity.getData().getSale();
                    sp.put(NAME, sale.getName());
                    sp.put(SEX, String.valueOf(sale.getSex()));
                    sp.put(AGE, String.valueOf(sale.getAge()));
                    sp.put(SALE_ID, String.valueOf(sale.getId()));
                    sp.put(PHONE_NUMBER, sale.getPhone());
                    sp.put(WORK, sale.getWork());
                    sp.put(NUMBER, String.valueOf(sale.getNumber()));
                    sp.put(IMAGE, sale.getImage());
                    sp.put(IS_ORPERFECT, String.valueOf(sale.getIsOrPerfect()));
                    sp.put(SUCCESSINDEX, String.valueOf(sale.getSuccessIndex()));
                    sp.put(SALEINDEX, String.valueOf(sale.getSaleIndex()));
                }

                @Override
                public void onError(int errorCode, String errorMsg) {
                    if (errorMsg.equals("Failed to connect to")) {
                        showToast(getContext(), "未能连接到服务器");
                    }
                }
            });
        }
    }
}
