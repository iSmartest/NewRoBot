package com.furuiintelligence.newrobot.ui.activity;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.ui.fm.HomeFragment;
import com.furuiintelligence.newrobot.ui.fm.MyFragment;
import com.furuiintelligence.newrobot.ui.fm.TrainFragment;
import com.furuiintelligence.newrobot.ui.view.SelfDialog;
import com.furuiintelligence.newrobot.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 主页面
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.iv_main_home)
    RadioButton mHome;
    @BindView(R.id.iv_main_train)
    RadioButton mTrain;
    @BindView(R.id.iv_main_stop)
    RadioButton mStop;
    @BindView(R.id.iv_main_mine)
    RadioButton mMy;
    private HomeFragment homeFragmen;
    //记录用户首次点击返回键的时间
    private long firstTime = 0;
    ColorStateList csl1,csl2,csl3;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        checkPermission();
        homeFragmen = (HomeFragment) changeFragment(HomeFragment.class,R.id.linear_main_layout_content,true,null,true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_main_home, R.id.iv_main_train, R.id.iv_main_stop, R.id.iv_main_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_home:
                changeFragment(HomeFragment.class,R.id.linear_main_layout_content,true,null,false);
                break;
            case R.id.iv_main_train:
                changeFragment(TrainFragment.class,R.id.linear_main_layout_content,true,null,false);
                break;
            case R.id.iv_main_stop:
                showDialog(this);
                break;
            case R.id.iv_main_mine:
                changeFragment(MyFragment.class,R.id.linear_main_layout_content,true,null,false);
                break;
        }
    }

    public void showDialog(final Context context) {
        setBackgroundAlpha(0.5f);
        final SelfDialog selfDialog = new SelfDialog(context);
        selfDialog.setTitle("是否紧急停止？");
        selfDialog.setYesOnclickListener("是", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
                setBackgroundAlpha(1.0f);
                mStop.setChecked(true);
            }
        });
        selfDialog.setNoOnclickListener("否", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
                setBackgroundAlpha(1.0f);
                mStop.setChecked(false);
            }
        });
        selfDialog.show();
    }

    //popuwindow设置透明度
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                showToast(this, R.string.tos_Sign_out);
                firstTime = secondTime;
                return true;
            } else {
                ActivityUtils.getInstance().closeSelf();
            }
        }

        return super.onKeyUp(keyCode, event);
    }



    private void checkPermission() {
        //判断Android版本   获取需要的权限
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionStrs = new ArrayList<>();
            int hasWriteSdcardPermission =
                    ContextCompat.checkSelfPermission(
                            MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteSdcardPermission != PackageManager.PERMISSION_GRANTED) {
                permissionStrs.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                permissionStrs.add(Manifest.permission.CALL_PRIVILEGED);
                permissionStrs.add(Manifest.permission.MODIFY_AUDIO_SETTINGS);
                permissionStrs.add(Manifest.permission.READ_CONTACTS);
                permissionStrs.add(Manifest.permission.RECORD_AUDIO);
                permissionStrs.add(Manifest.permission.VIBRATE);
                permissionStrs.add(Manifest.permission.CAMERA);

            }

            String[] stringArray = permissionStrs.toArray(new String[0]);
            if (permissionStrs.size() > 0) {
                requestPermissions(stringArray,
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }

        }
    }
}
