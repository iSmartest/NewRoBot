package com.furuiintelligence.newrobot.ui.fm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.adapter.MyFragmentAdapter;
import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.base.BaseFragment;
import com.furuiintelligence.newrobot.mvp.cotract.ExperienCeContract;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienCeEntity;
import com.furuiintelligence.newrobot.mvp.moudle.ExperienceListEntity;
import com.furuiintelligence.newrobot.mvp.presenter.ExperienCePresenter;
import com.furuiintelligence.newrobot.ui.activity.home.intformtion.EditActivity;
import com.furuiintelligence.newrobot.ui.activity.my.MyExperienCeActivity;
import com.furuiintelligence.newrobot.ui.activity.my.PersonalMainActivity;
import com.furuiintelligence.newrobot.ui.view.GlideCircleTransform;
import com.furuiintelligence.newrobot.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IMAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NAME;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.PAGE_SIZI;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SUCCESSINDEX;
import static com.furuiintelligence.newrobot.mvp.config.Url.HTTP;

/**
 * Name 赋睿智能
 * Date 2018/5/2
 * Time 16:36
 * <p>
 * 我的页面
 */

public class MyFragment extends BaseFragment implements ExperienCeContract.View {

    private static final String TAG = "MyFragment";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_icon_img)
    ImageView myIconImgview;
    @BindView(R.id.title_Layout)
    RelativeLayout titleLayout;
    @BindView(R.id.mName)
    TextView mName;
    @BindView(R.id.my_doubel_no)
    TextView myDoubelNo;
    @BindView(R.id.my_doubel_nos)
    TextView myDoubelNos;
    @BindView(R.id.mPtrLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.mXd)
    TextView mXd;
    Unbinder unbinder;
    private ExperienCeContract.ExperienCeContractPresenter experienCeContractPresenter;
    private Map<String, String> myMap = new HashMap<>();
    private MyFragmentAdapter fragmentAdapter;
    private List<ExperienceListEntity.DataBean.ContentListBean> myList;

    @Override
    protected int getLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initView(View view) {

        title.setText(R.string.app_mine);
        titleLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorTransParent));

    }

    @Override
    protected void loadData() {
        myList = new ArrayList<>();
        fragmentAdapter = new MyFragmentAdapter(getActivity(), R.layout.may_list_item, myList);
        mListView.setAdapter(fragmentAdapter);



        myMap.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
        myMap.put(PAGE,String.valueOf(PAGE_SIZI));
        experienCeContractPresenter = new ExperienCePresenter(this);
        experienCeContractPresenter.start(myMap);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_icon_img, R.id.mXd})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.mXd:

                ActivityUtils.getInstance().showActivity(getActivity(), MyExperienCeActivity.class);
                break;

            case R.id.my_icon_img:
                ActivityUtils.getInstance().showActivity(APP.context, PersonalMainActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(this).load(HTTP + (String) sp.getSharedPreference(IMAGE, ""))
                .transform(new GlideCircleTransform(getContext())).into(myIconImgview);
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

        switch (exp.getResultCode()){
            case 0:

                myList.addAll(exp.getData().getContentList());
                fragmentAdapter.notifyDataSetChanged();
                break;
            case 1:
                showToast(exp.getMsg());
                break;
        }


    }

    @Override
    public void setRequests(ExperienCeEntity exps) {
        //无用
    }

    @Override
    public void showErrMessage(int code, String err) {
        if (err.equals("Failed to connect to")) {
            showToast("未能连接到服务器");
        }
    }
}
