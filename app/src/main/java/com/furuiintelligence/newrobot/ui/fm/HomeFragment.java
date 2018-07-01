package com.furuiintelligence.newrobot.ui.fm;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.furuiintelligence.newrobot.R;
import com.furuiintelligence.newrobot.adapter.AbsAdapter;
import com.furuiintelligence.newrobot.adapter.GridViewAdapter;
import com.furuiintelligence.newrobot.adapter.HomeBannerAdapter;
import com.furuiintelligence.newrobot.app.APP;
import com.furuiintelligence.newrobot.mvp.base.BaseFragment;
import com.furuiintelligence.newrobot.mvp.cotract.HomeModelContract;
import com.furuiintelligence.newrobot.mvp.moudle.GridViewBean;
import com.furuiintelligence.newrobot.mvp.moudle.HomeEntity;
import com.furuiintelligence.newrobot.mvp.presenter.HomePresenter;
import com.furuiintelligence.newrobot.ui.activity.home.CustomerActivity;
import com.furuiintelligence.newrobot.ui.activity.home.NewPoint_Activity;
import com.furuiintelligence.newrobot.ui.activity.home.ProductSales_Activity;
import com.furuiintelligence.newrobot.ui.activity.home.TodoActivity;
import com.furuiintelligence.newrobot.utils.ActivityUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.furuiintelligence.newrobot.app.APP.sp;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.IMGS;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.NAMES;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.RESULTCODE_L;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.RESULTCODE_ONE;
import static com.furuiintelligence.newrobot.mvp.base.BaseUrl.SALE_ID;
import static com.furuiintelligence.newrobot.mvp.config.Url.HTTP;

/**
 * Name 赋睿智能
 * Date 2018/5/2
 * Time 16:36
 * home页面
 */

public class HomeFragment extends BaseFragment implements HomeModelContract.View, OnBannerListener {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_Layout)
    RelativeLayout titleLayout;
    @BindView(R.id.homeListView)
    ListView homeListView;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.homeGridView)
    GridView homeGridView;


    Unbinder unbinder;
    private ActivityUtils instance;
    private HomeModelContract.HomeContractPresenter homeContractPresenter;
    private Map<String, String> homeMap = new HashMap<>();
    private List<String> titleList = new ArrayList<>();
    private List<String> imgList = new ArrayList<>();
    private List<HomeEntity.DataBean.ImageListBean> homeList = new ArrayList<>();
    private List<HomeEntity.DataBean.NoticeListBean> mmList = new ArrayList<>();
    private List<GridViewBean> gridViewBeanList =new ArrayList<>();
    private GridViewAdapter gridViewAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {
        title.setText(R.string.app_home);
        titleLayout.setBackgroundColor(APP.context.getResources().getColor(R.color.colorTransParent));
        instance = ActivityUtils.getInstance();

        for (int i = 0; i < IMGS.length; i++) {
            GridViewBean bean =new GridViewBean();
            bean.setImg(IMGS[i]);
            bean.setName(NAMES[i]);
            gridViewBeanList.add(bean);
        }
        gridViewAdapter =new GridViewAdapter(APP.context,R.layout.home_gridview_item_list,gridViewBeanList);
        homeGridView.setAdapter(gridViewAdapter);
        homeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //待办
                        instance.showActivity(APP.context, TodoActivity.class);
                        break;
                    case 1:
                        //客户资料
                        instance.showActivity(APP.context, CustomerActivity.class);
                        break;
                    case 2:
                        //我的机器人
//                instance.showActivity(APP.context, TodoActivity.class);
                        break;
                    case 3:
                        //新品买点
                        instance.showActivity(APP.context, NewPoint_Activity.class);

                        break;
                    case 4:
                        instance.showActivity(APP.context, ProductSales_Activity.class);
                        break;
                    case 5:

                        //紧急停止
//                instance.showActivity(APP.context, TodoActivity.class);



                        break;
                }
            }
        });

    }

    @Override
    protected void loadData() {
        homeContractPresenter = new HomePresenter(this);
        homeMap.put(SALE_ID, (String) sp.getSharedPreference(SALE_ID, ""));
        homeContractPresenter.start(homeMap);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new HomeBannerAdapter())
                .setImages(imgList)
                .setBannerAnimation(Transformer.Default)
                .setBannerTitles(titleList)
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @OnClick({R.id.mTodo, R.id.mPoint, R.id.mCustomer, R.id.mSales, R.id.mRobots, R.id.mStop})
//    public void onViewClicked(View view) {
//
//        }
//    }

    @Override
    public void setPresenter(HomeModelContract.HomeContractPresenter homeContractPresenter) {
        this.homeContractPresenter = homeContractPresenter;
    }

    @Override
    public void showCashProfitDialog() {

    }

    @Override
    public void dissmiCashProfitDialog() {

    }

    @Override
    public void setRequest(final HomeEntity homeEntity) {


        switch (homeEntity.getResultCode()) {
            case RESULTCODE_L:
                homeList.addAll(homeEntity.getData().getImageList());
                for (int i = 0; i < homeEntity.getData().getImageList().size(); i++) {
                    titleList.add(homeEntity.getData().getImageList().get(i).getName());
                    imgList.add(HTTP + homeEntity.getData().getImageList().get(i).getAddress());
                }
                if (homeEntity.getData().getNoticeList().size() != 0) {
                    mmList.addAll(homeEntity.getData().getNoticeList());
                    homeListView.setAdapter(new AbsAdapter<HomeEntity.DataBean.NoticeListBean>(APP.context, R.layout.home_list_item, mmList) {
                        @Override
                        public void bindResponse(ViewHolder holder, HomeEntity.DataBean.NoticeListBean data) {
                            ((TextView) holder.getView(R.id.home_item_tv)).setText(data.getContent());
                            Glide.with(getActivity()).load(HTTP+homeEntity.getData().getImage()).into((ImageView)holder.getView(R.id.home_ImgView));
                            ((TextView) holder.getView(R.id.home_tvName)).setText(homeEntity.getData().getName());
                        }

                    });
                }
                break;
            case RESULTCODE_ONE:
                showToast(homeEntity.getMsg());
                break;
        }


    }

    @Override
    public void showErrMessage(String err) {
        if (err.equals("Failed to connect to")) {
            showToast("未能连接到服务器");
        }
    }

    @Override
    public void OnBannerClick(int position) {
        showToast("这是第" + homeList.get(position) + "条....");
    }
}
