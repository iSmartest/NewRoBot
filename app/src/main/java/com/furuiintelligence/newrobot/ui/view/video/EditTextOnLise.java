package com.furuiintelligence.newrobot.ui.view.video;
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
//        Name 赋睿智能-Date 2018/6/5-Time 16:40
//                   站在顶峰,看世界
//                   跌落谷底,思人生

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class EditTextOnLise implements TextWatcher {

    EditText id;
    EditText id2;
    Button butId;

    public EditTextOnLise(EditText id, EditText id2, Button butId) {
        this.id = id;
        this.id2 = id2;
        this.butId = butId;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String s1 = id.getText().toString();
        String s2 = id2.getText().toString();
        if (TextUtils.isEmpty(s1) && TextUtils.isEmpty(s2)) {

            /**
             * 如果s1异或s2都为空 则按钮灰色不可点击
             *
             */

        } else if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) {
            /**
             * 判断s1与s2是否有一个为空 如果有一个为空 按钮灰色不可点击
             *
             */
        } else {
            /**
             * 如果都不为空  按钮红色 可点击
             */
        }
    }
}
