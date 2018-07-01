package com.furuiintelligence.newrobot.ui.activity.my;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.adapter.adpter.CommonAdapter;
import com.furuiintelligence.newrobot.adapter.adpter.ViewHolder;
import com.furuiintelligence.newrobot.mvp.base.BaseActivity;
import com.furuiintelligence.newrobot.mvp.config.Url;
import com.furuiintelligence.newrobot.mvp.cotract.ExperienCeContract;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienCeEntity;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienceListEntity;
import com.furuiintelligence.newrobot.mvp.net.OkHttpUtils;
import com.furuiintelligence.newrobot.mvp.net.callback.MyNetWorkCallback;
import com.furuiintelligence.newrobot.mvp.presenter.ExperienCePresenter;
import com.furuiintelligence.newrobot.ui.activity.MainActivity;
import com.furuiintelligence.newrobot.ui.view.GlideCircleTransform;
import com.furuiintelligence.newrobot.ui.view.swipeLayout.SwipeLayout;
import com.furuiintelligence.newrobot.ui.view.swipeLayout.SwipeLayoutManager;
import com.furuiintelligence.newrobot.utils.DateUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.furuiintelligence.newrobot.app.APP.getContext;
import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.EXPERIENCE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IMAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NAME;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE_SIZI;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PHONE_NUMBER;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;
import static com.furuiintelligence.newrobot.mvp.config.Url.DELET_SALE_MANS;
import static com.furuiintelligence.newrobot.mvp.config.Url.HTTP;

public class MyExperienCeActivity extends BaseActivity implements ExperienCeContract.View {


    private static final String TAG = "MyExperienCeActivity";

    @BindView(R.id.title_Back)
    ImageView titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_OK)
    TextView titleOK;
    @BindView(R.id.swipeListView)
    ListView swipeListView;
    ExperienCeContract.ExperienCeContractPresenter experienCeContractPresenter;
    private Map<String, String> myMaps = new HashMap<>();
    private List<ExperienCeEntity.DataBean.ExperienceListBean> myLists;
    private Adapter adapter;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> exMap = new HashMap<>();
    private EditText et_search;

    @Override
    protected int getLauoutId() {
        return R.layout.activity_my_experien_ce;
    }

    @Override
    protected void loadData() {
        myLists = new ArrayList<>();
        adapter = new Adapter(this, myLists, R.layout.item_listview);
        swipeListView.setAdapter(adapter);
        myMaps.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
        myMaps.put(PAGE, PAGE_SIZI + "");
        map.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
        experienCeContractPresenter = new ExperienCePresenter(this);
        experienCeContractPresenter.starts(myMaps);


// 侧滑打来的时候滑动没有想到什么好的办法解决，只能这样了。
        swipeListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // 如果listView跟随手机拖动，关闭已经打开的SwipeLayout
                    SwipeLayoutManager.getInstance().closeOpenInstance();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });


    }

    @Override
    protected void initView() {
        title.setText("我的心得");
        titleBack.setVisibility(View.VISIBLE);
        titleOK.setVisibility(View.VISIBLE);
        titleOK.setText("编辑");


    }


    @OnClick({R.id.title_Back, R.id.title_OK})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_Back:
                finish();
                break;
            case R.id.title_OK:
                //添加心得？
                search();

                break;
        }
    }

    public void search() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.add_edittext_dialog_layout, null);
        dialog.setTitle("请输入心得");
        dialog.setView(layout);
        et_search = (EditText) layout.findViewById(R.id.add_EditText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String searchC = et_search.getText().toString();
                exMap.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
                exMap.put(EXPERIENCE, searchC);
                OkHttpUtils.getInstance().post(Url.ADD_EXPERLIST, exMap, new MyNetWorkCallback<Object>() {
                    @Override
                    public void onSuccess(Object o) {

                        Log.e(TAG, "onSuccess: " + o.toString());//添加后获取结果
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {

                    }
                });

            }
        });

        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        dialog.show();
    }

    @Override
    public void setPresenter(ExperienCeContract.ExperienCeContractPresenter experienCeContractPresenter) {
        this.experienCeContractPresenter = experienCeContractPresenter;
    }

    @Override
    public void showCashProfitDialog() {

    }

    @Override
    public void dissmiCashProfitDialog() {

    }

    @Override
    public void setRequest(ExperienceListEntity exp) {
        //所有销售员
    }

    @Override
    public void setRequests(ExperienCeEntity exps) {
//我的心得
        myLists.addAll(exps.getData().getExperienceList());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMessage(int code, String err) {
        if (err.equals("Failed to connect to")) {
            showToast(getContext(), "未能连接到服务器");
        }
    }


    class Adapter extends CommonAdapter<ExperienCeEntity.DataBean.ExperienceListBean> {

        public Adapter(Context context, List<ExperienCeEntity.DataBean.ExperienceListBean> datas, int layoutId) {
            super(context, datas, layoutId);
        }


        @Override
        public void convert(ViewHolder holder, final ExperienCeEntity.DataBean.ExperienceListBean experienceListBean) {
            holder.setText(R.id.tv_name, experienceListBean.getContent());
            holder.setText(R.id.tv_time, DateUtils.transferLongToDate(experienceListBean.getTime()));

            final SwipeLayout swipeLayout = holder.getView(R.id.swipelayout);

            swipeLayout.setOnSwipeLayoutClickListener(new SwipeLayout.OnSwipeLayoutClickListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MyExperienCeActivity.this, experienceListBean.getContent(), Toast.LENGTH_SHORT).show();
                }
            });
            ((AutoLinearLayout) swipeLayout.getDeleteView()).getChildAt(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.put("id",experienceListBean.getId());
                    SwipeLayoutManager.getInstance().closeOpenInstance();
                    myLists.remove(experienceListBean.getContent());
                    adapter.notifyDataSetChanged();
                    OkHttpUtils.getInstance().post(DELET_SALE_MANS, map, new MyNetWorkCallback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Log.e("TAG", "onSuccess: " + o.toString());
                            experienCeContractPresenter.starts(myMaps);
                        }

                        @Override
                        public void onError(int errorCode, String errorMsg) {

                        }
                    });
                }
            });
        }
    }

}
