package com.furuiintelligence.newrobot.mvp.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;


import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.ui.view.SelfDialog;
import com.furuiintelligence.newrobot.ui.view.ShowDialog;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.furuiintelligence.newrobot.utils.SharedPreferencesHelper;
import com.furuiintelligence.newrobot.utils.StatusBar;

import java.util.Locale;

import butterknife.ButterKnife;


/**
 * 所有Activity都继承此Activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private BaseFragment lastFragment;


    /**
     * 之前显示的内容
     */
    private static String oldMsg;
    /**
     * Toast对象
     */
    private static Toast toast = null;
    /**
     * 第一次时间
     */
    private static long oneTime = 0;
    /**
     * 第二次时间
     */
    private static long twoTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APP.context = this;
        setContentView(getLauoutId());
        ButterKnife.bind(this);
        StatusBar.fullScreen(this);//沉浸式状态栏
        ActivityUtils.getInstance().addAcitivity(this);//初始化Activity工具类
        initView();//实例化
        loadData();//加载数据


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLauoutId();

    protected abstract void loadData();

    protected abstract void initView();

    /**
     * 显示Toast  String
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = message;
                toast.setText(message);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    /**
     * 现实Toast int
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, int message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();

            if (String.valueOf(message).equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = String.valueOf(message);
                toast.setText(message);
                toast.show();
            }
        }
        oneTime = twoTime;
    }


    protected String getTitle(String s) {
        //获取title
        return getIntent().getExtras().getString(s).toString();
    }

    protected String getJson(String key) {
        //获取title
        return getIntent().getExtras().getString(key).toString();
    }

    public static void getDialog(final Context context) {
        ShowDialog showDialog = new ShowDialog(context);
        showDialog.setYesOnclickListener("接待", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                showToast(context, "已接待");
            }
        });
        showDialog.setNoOnclickListener("工作", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                showToast(context, "正在工作中");
            }
        });

    }

    /**
     * 切换Fragment的方法
     *
     * @param fragmentClass 要跳转的Fragment
     * @param containId     容器ID
     * @param isHidden      是否隐藏
     * @param bundle        参数
     * @param isBack        是否添加到回退栈
     * @return
     */
    public BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, int containId, boolean isHidden, Bundle bundle, boolean isBack) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //获取Fragment的类名，用类名当做Tag
        String fragmentName = fragmentClass.getSimpleName();
        //根据tag来查找当前Fragment，如果不为null 就代表当前Fragment已经被加载过至少一次
        BaseFragment currentFragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
        if (currentFragment == null) {
            //如果Fragment为null 就创建Fragment对象，添加到FragmentManager中
            try {
                //通过Java动态代理创建的对象
                currentFragment = fragmentClass.newInstance();
                //添加到FragmentManager中
                transaction.add(containId, currentFragment, fragmentName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (isHidden) {
            //隐藏上一个Fragment
            if (lastFragment != null)
                transaction.hide(lastFragment);
            //显示当前Fragment
            transaction.show(currentFragment);
        } else {
            //替换上一个Fragment
            transaction.replace(containId, currentFragment, fragmentName);
        }
        //传递参数
        if (bundle != null) {
            currentFragment.setBundle(bundle);
        }

        if (isBack) {
            transaction.addToBackStack(fragmentName);
        }

        transaction.commit();

        lastFragment = currentFragment;

        return lastFragment;

    }


}
