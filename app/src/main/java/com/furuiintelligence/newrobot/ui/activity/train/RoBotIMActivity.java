package com.furuiintelligence.newrobot.ui.activity.train;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.ui.view.audiobutton.AudioRecorder;
import com.furuiintelligence.newrobot.ui.view.audiobutton.RecordButton;
import com.furuiintelligence.newrobot.ui.view.video.EditTextOnLise;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 机器人训练室
 */
public class RoBotIMActivity extends BaseActivity implements RecordButton.RecordListener {


    private static final String TAG = "RoBotIMActivity";
    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.mButon)
    RecordButton mButon;
    @BindView(R.id.mEdit)
    EditText mEdit;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_ro_bot_im;
    }

    @Override
    protected void loadData() {

        mEdit.addTextChangedListener(new EditTextOnLise(mEdit, mEdit, mButon));

    }

    @Override
    protected void initView() {
        mEdit.setVisibility(View.GONE);
        mButon.setVisibility(View.VISIBLE);
        mButon.setListener(this);
        mButon.setRecordStrategy(new AudioRecorder());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.title_Back)
    public void onViewClicked() {
    }

    @Override
    public void RecordEnd(File path) {

        Log.e(TAG, "RecordEnd: " + path);
    }
}
