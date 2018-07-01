package com.furuiintelligence.newrobot.ui.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.ui.activity.MainActivity;
import com.furuiintelligence.newrobot.ui.activity.StartActivity;
import com.furuiintelligence.newrobot.ui.activity.train.ProblemMainActivity;
import com.furuiintelligence.newrobot.utils.ActivityUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 设置页面
 */
public class SeetingsActivity extends BaseActivity {


    private static final String TAG = "SeetingsActivity";

    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.problem_Setting)
    TextView problemSetting;
    @BindView(R.id.language_Setting)
    CheckBox languageSetting;
    @BindView(R.id.standard)
    RadioButton standard;
    @BindView(R.id.english)
    RadioButton english;
    @BindView(R.id.dialect)
    RadioButton dialect;
    @BindView(R.id.seetingsLayout)
    RadioGroup seetingsLayout;
    @BindView(R.id.demo_Setting)
    CheckBox demoSetting;
    @BindView(R.id.personal_Style)
    RadioButton personalStyle;
    @BindView(R.id.standard_Style)
    RadioButton standardStyle;
    @BindView(R.id.seetings_Layouts)
    RadioGroup seetingsLayouts;
    @BindView(R.id.text_Setting)
    CheckBox textSetting;
    @BindView(R.id.englishi)
    RadioButton englishi;
    @BindView(R.id.chinese)
    RadioButton chinese;
    @BindView(R.id.mSeetings_Layouts)
    RadioGroup mSeetingsLayouts;
    @BindView(R.id.mVeesion_Update)
    TextView mVeesionUpdate;
    private ActivityUtils instance;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_seetings;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        titleBack.setVisibility(View.VISIBLE);
        title.setText(R.string._settings);
        instance = ActivityUtils.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_Back, R.id.problem_Setting, R.id.language_Setting, R.id.standard,
            R.id.english, R.id.dialect, R.id.demo_Setting, R.id.personal_Style,
            R.id.standard_Style, R.id.text_Setting, R.id.englishi, R.id.chinese,
            R.id.mVeesion_Update})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.problem_Setting:
                //问题设置 --待定
                ActivityUtils.getInstance().showActivity(this, ProblemMainActivity.class);
                break;
            case R.id.language_Setting:
                showToast(this, languageSetting.getText().toString());
                isSelect(languageSetting.isChecked(), seetingsLayout);
                break;
            case R.id.standard:
                showToast(this, standard.getText().toString());
                //标准
                break;
            case R.id.english:
                showToast(this, english.getText().toString());
                //英语
                break;
            case R.id.dialect:
                showToast(this, dialect.getText().toString());
                //方言
                break;
            case R.id.demo_Setting:
                showToast(this, demoSetting.getText().toString());
                //演示设置
                isSelect(demoSetting.isChecked(), seetingsLayouts);
                break;
            case R.id.personal_Style:
                showToast(this, personalStyle.getText().toString());
                //个人风格
                break;
            case R.id.standard_Style:
                showToast(this, standardStyle.getText().toString());
                //标准风格
                break;
            case R.id.text_Setting:
                showToast(this, textSetting.getText().toString());
                //文字设置
                isSelect(textSetting.isChecked(), mSeetingsLayouts);
                break;
            case R.id.englishi:
                showToast(this, englishi.getText().toString());

                //英语
                break;
            case R.id.chinese:
                showToast(this, chinese.getText().toString());

                //中文
                break;

            case R.id.mVeesion_Update:
                showToast(this, mVeesionUpdate.getText().toString());
                //版本更新
                break;
        }
    }


    private void isSelect(boolean ble, RadioGroup rg) {
        if (ble) {
            //选中
            rg.setVisibility(View.VISIBLE);
        } else {
            //未选择中
            rg.setVisibility(View.GONE);
        }
    }


}
