package com.furuiintelligence.newrobot.app;


//                    _ooOoo_
//                   o8888888o
//                   88" . "88
//                   (| -_- |)
//                   O\  =  /O
//                ____/`---'\____
//              .'  \\|     |//  `.
//             /  \\|||  :  |||//  \
//            /  _||||| -:- |||||-  \
//            |   | \\\  -  /// |   |
//            | \_|  ''\---/''  |   |
//            \  .-\__  `-`  ___/-. /
//          ___`. .'  /--.--\  `. . ___
//       ."" '<  `.___\_<|>_/___.'  >' "".
//      | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//      \  \ `-.   \_ __\ /__ _/   .-` /  /
// ======`-.____`-.___\_____/___.-`____.-'======
//                    `=---='
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//          佛祖保佑     永不宕机      永无BUG
//                      大姨夫 2018/4/24
//                站在顶峰,看世界
//                跌落谷底,思人生

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.view.View;

import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.utils.SharedPreferencesHelper;
import com.zhy.autolayout.config.AutoLayoutConifg;

import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.DATA;


public class APP extends Application {
    private static final String TAG = "APP";
    public static BaseActivity context = null;
    public static Context mContext;
    public static SharedPreferencesHelper sp;
    public static APP instans;


    @Override
    public void onCreate() {
        super.onCreate();

        instans=this;
        //初始化屏幕适配
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        mContext = getApplicationContext();
//        初始化本地数据库
        sp = new SharedPreferencesHelper(this, DATA);


    }


    @Override//分包  防止65535方法限制
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    /**
     * 获取全局的Context
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }
    public static APP getInstans(){
        return instans;
    }

    public static void getWindows(Activity con) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //实现状态栏图标和文字颜色为暗色
            con.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        con.getWindow().getDecorView().findViewById(android.R.id.content)
                .setPadding(0, 0, 0, 0);
    }
}
