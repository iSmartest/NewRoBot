package com.furuiintelligence.newrobot.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.app.APP;


/**
 * Name 赋睿智能
 * Date 2018/4/26
 * Time 11:36
 */

public class SpannableUtlis  {

    String str;
    EditText editText;

    public SpannableUtlis(String str, EditText editText) {
        this.str = str;
        this.editText = editText;
        SpannableString name =  new SpannableString(str);
        AbsoluteSizeSpan names = new AbsoluteSizeSpan(12, true);
        editText.setHintTextColor(APP.context.getResources().getColor(R.color.gray));
        name.setSpan(names, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(name));

    }

}
