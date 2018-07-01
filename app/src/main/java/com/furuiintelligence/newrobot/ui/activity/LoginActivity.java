package com.furuiintelligence.newrobot.ui.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.net.OkHttpUtils;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;
import com.furuiintelligence.newrobot.ui.activity.home.ProductSales_Activity;
import com.furuiintelligence.newrobot.ui.activity.train.ProblemMainActivity;
import com.furuiintelligence.newrobot.ui.view.Input_monitoring;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.furuiintelligence.newrobot.utils.SpannableUtlis;
import com.furuiintelligence.newrobot.utils.TimeCount;
import com.furuiintelligence.newrobot.utils.Validator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.app.APP.getWindows;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.CODE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PHONE_NUMBER;
import static com.furuiintelligence.newrobot.mvp.config.Url.LOGIN_;
import static com.furuiintelligence.newrobot.mvp.config.Url.SMS;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity {


    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_Phone)
    EditText loginPhone;
    @BindView(R.id.login_SMS)
    EditText loginSMS;
    @BindView(R.id.but_Login_SMS)
    Button butLoginSMS;
    @BindView(R.id.login_Post)
    Button loginPost;

    private TimeCount time;
    private Map<String, String> smsMap = new HashMap<>();
    private Map<String, String> loginMap = new HashMap<>();

    @Override
    protected int getLauoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void loadData() {
        getWindows(this);
        loginPhone.addTextChangedListener(new Input_monitoring(loginPhone, 11, butLoginSMS));
        loginSMS.addTextChangedListener(new Input_monitoring(loginPhone, 6, loginPost));
        time = new TimeCount(butLoginSMS, 60000, 1000);
    }

    @Override
    protected void initView() {

        new SpannableUtlis("请输入手机号码", loginPhone);
        new SpannableUtlis("请输入验证码", loginSMS);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.but_Login_SMS, R.id.login_Post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_Login_SMS:
                getPhone(loginPhone.getText().toString().trim());

                break;
            case R.id.login_Post:
//                                getSMS(loginSMS.getText().toString().trim());
                ActivityUtils.getInstance().showActivity(LoginActivity.this, MainActivity.class);

                break;

        }
    }

    //登录按钮执行
    private void getSMS(String sms) {
        if (!TextUtils.isEmpty(sms)) {
//            boolean password = Validator.isPassword(sms);
//            if (password!=true){
//                Toast.makeText(this, R.string.sms_verification, Toast.LENGTH_SHORT).show();
//                return;
//            }else {
            //后续操作
//                ActivityUtils.getInstance().showActivity(LoginActivity.this, MainActiv);
            loginMap.put(PHONE_NUMBER, loginPhone.getText().toString());
            loginMap.put(CODE, sms);
            OkHttpUtils.getInstance().post(LOGIN_, loginMap, new MyNetWorkCallback<Object>() {
                @Override
                public void onSuccess(Object o) {
                    Log.e(TAG, "onSuccess: " + o.toString());
                }

                @Override
                public void onError(int errorCode, String errorMsg) {

                }
            });


        }
//        }else {
//            Toast.makeText(this, R.string.sms_null, Toast.LENGTH_SHORT).show();
//            return;
//        }
    }

    //发送验证码执行
    private void getPhone(String ph) {
        /**判断是否为空 是否正确*/
        if (!TextUtils.isEmpty(ph)) {
            boolean mobile = Validator.isMobile(ph);
            if (mobile != true) {
                Toast.makeText(this, R.string.phone_validation, Toast.LENGTH_SHORT).show();
                return;
            } else {
                //后续操作
                time.start();
                smsMap.put(PHONE_NUMBER, ph);
                OkHttpUtils.getInstance().post(SMS, smsMap, new MyNetWorkCallback<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.e(TAG, "onSuccess: " + o.toString());
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {

                    }
                });
            }
        } else {
            Toast.makeText(this, R.string.phone_null, Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


        //判断是否登录过  没有就登录  如果登录过 就直接跳转完善资料页面


//        String preference = (String) sp.getSharedPreference(IS_ORPERFECT, "");
//        if (TextUtils.isEmpty(preference)){
//            ActivityUtils.getInstance().showActivity(this, PerfectinformationActivity.class);
//        }else {
//            ActivityUtils.getInstance().showActivity(this, MainActivity.class);
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
