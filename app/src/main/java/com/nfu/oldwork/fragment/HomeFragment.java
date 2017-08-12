package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nfu.oldwork.R;
import com.nfu.oldwork.adapter.HotAdPagerAdapter;
import com.nfu.oldwork.adapter.HomeNewsListViewPagerAdapter;
import com.nfu.oldwork.adapter.NewsListAdapter;
import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.NewsModel;
import com.nfu.oldwork.model.NewsModels;
import com.nfu.oldwork.model.TurnPicModel;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.view.PagerIndicator;
import com.nfu.oldwork.view.PointPagerIndicator;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/11.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_fragment_viewpager)
    ViewPager mTrunPicViewPager;

    @BindView(R.id.nfu_hot_list_ad_indicator)
    PointPagerIndicator pointPagerIndicator;

    @BindView(R.id.home_newslist_layout_tab)
    PagerIndicator mPagerIndicator;
    @BindView(R.id.home_newslist_viewpager)
    ViewPager mNewsViewPager;
    private NewsListAdapter newsListAdapter;
    private NewsListAdapter announcementlistAdapter;

    private NewsListAdapter policylistAdapter;

    private XRecyclerView newsRecyclerView;
    private XRecyclerView policyrecyclerView;

    private XRecyclerView announcementRecyclerView;


    private int a_currentPage = 0;
    private int a_iRecordCount = 0;
    private int p_currentPage = 0;
    private int p_iRecordCount = 0;
    private int n_currentPage = 0;
    private int n_iRecordCount = 0;

    private final static int REFRESH_TYPE = 1001;
    private final static int LOADMORE_TYPE = 1002;
    private final static int LEFT_TYPE = 2001;
    private final static int RIGHT_TYPE = 2002;
    private static final int PAGESIZE = 7;
    Unbinder unbinder;
    private Timer mTimer;
    private TimerTask mTimerTask;

    int adIndex = 0;
    private Handler MyHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mTrunPicViewPager != null && mTrunPicViewPager.getAdapter() != null && mTrunPicViewPager.getAdapter().getCount() != 0) {
                mTrunPicViewPager.setCurrentItem(adIndex);
            }
            adIndex++;
            if (adIndex == mTrunPicViewPager.getAdapter().getCount() - 1) {
                adIndex = 0;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "HomeFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initPager();
        loadData();
        return rootView;
    }



    private void initPager() {
        mTrunPicViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    RequestOptions options = new RequestOptions()
            .centerCrop().placeholder(R.drawable.def_turn);
    @Override
    protected void loadData() {
        ApiManager.getInstance().getTurnPic("1008", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("HomeFragment--->loadData--->onError--->" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                if(getContext()!=null){
                    LogUtil.i("HomeFragment--->loadData--->onResponse--->" + response);
                    try {
                        TurnPicModel turnPicModel = new Gson().fromJson(response, TurnPicModel.class);
                        LogUtil.i("HomeFragment--->loadData--->TurnPicModel--->" + turnPicModel);
                        final List<TurnPicModel.StrResultBean> pics = turnPicModel.getStrResult();
                        final ArrayList<ImageView> ads = new ArrayList<ImageView>();
                        if (pics != null && pics.size() > 0 && !NfuResource.getInstance().isUseDefPic()) {
                            for (int i = 0; i < pics.size(); i++) {
                                ImageView imageView = new ImageView(getContext());
                                Glide.with(HomeFragment.this).load(pics.get(i).getPicurl()).apply(options).into(imageView);
                                ads.add(imageView);
                            }

                            if (ads.size() == 2) {
                                pointPagerIndicator.setIsTwoPage(true);
                            } else {
                                Log.e("HomeFragment", "pointPagerIndicator **** pointPagerIndicator..." +pointPagerIndicator);
                                pointPagerIndicator.setIsTwoPage(false);
                            }
                        } else {
                            ImageView imageView = new ImageView(getContext());
                            Glide.with(getContext()).load(R.drawable.def_turn).into(imageView);
                            ads.add(imageView);
                        }

                        HotAdPagerAdapter adPagerAdapter = new HotAdPagerAdapter(new HotAdPagerAdapter.AdItemOnClickListener() {
                            @Override
                            public void viewPagerItemOnClickListener(int position) {
                                LogUtil.d("viewPagerItemOnClickListener:position:" + position);
                                TurnPicModel.StrResultBean model = pics.get(position);
                                gotoDetailFragment(model.getId());
                            }
                        });


                        adPagerAdapter.setData(ads);
                        mTrunPicViewPager.setAdapter(adPagerAdapter);
                        pointPagerIndicator.setViewPager(mTrunPicViewPager);
                        if (pics != null && pics.size() != 0) {
                            int mid = adPagerAdapter.getCount() / 2;
                            pointPagerIndicator.setCurrentItem(mid - mid / pics.size(), false);
                        }

                        if (ads.size() < 2) {
                            pointPagerIndicator.setVisibility(View.INVISIBLE);
                        } else {
                            startAdTimer();
                        }
                    }catch (Exception e){
                        LogUtil.i("HomeFragment--->loadData--->Exception--->"+e);
                    }

                }
            }
        });
        getNewsList(0,0,REFRESH_TYPE);
        getAnnouncementList(0,0,REFRESH_TYPE);
        getPolicyList(0,0,REFRESH_TYPE);


    }
    private void gotoDetailFragment(String id){
        ApiManager.getInstance().getNewsDetail(id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("PolicyFragment--->initView--->getNewsDetail--->onError::"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("PolicyFragment--->initView--->getNewsDetail--->onResponse::"+response);
                NewsListModel listModel = new Gson().fromJson(response, NewsListModel.class);
                LogUtil.i("PolicyFragment--->initView--->getNewsDetail--->NewsListModel::"+listModel);
                NewsModel model1 = new Gson().fromJson(listModel.getStrResult(),NewsModel.class);
                LogUtil.i("PolicyFragment--->initView--->getNewsDetail--->NewsModel::"+model1);
                gotoDetailFragment(model1);
            }
        });
    }
    private void gotoDetailFragment(NewsModel newsModel){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title","媒体报道");
        bundle.putSerializable("news",newsModel);
        newsDetailFragment.setArguments(bundle);
        fragmentTransaction.hide(this);
        fragmentTransaction.add(R.id.activity_main_content_frameLayout , newsDetailFragment);
        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, newsDetailFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void initView() {
         newsRecyclerView = new XRecyclerView(getContext());
        // dateRecyclerView.setLayoutParams();
         newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         newsRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
         newsRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        newsListAdapter = new NewsListAdapter(getContext(), null,0, new NewsListAdapter.IOnDetailListener() {
            @Override
            public void onDetailListener(NewsModel model) {
                gotoDetailFragment(model.getId());
            }

        });
         newsRecyclerView.setAdapter(newsListAdapter);

         newsRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
             @Override
             public void onRefresh() {
                 LogUtil.i("news_recyclerview--->onRefresh");
                 getNewsList(0, 0, REFRESH_TYPE);

             }

             @Override
             public void onLoadMore() {
                 LogUtil.i("news_recyclerview--->onLoadMore");
                 getNewsList(n_currentPage, n_iRecordCount, LOADMORE_TYPE);
             }
        });
        announcementRecyclerView = new XRecyclerView(getContext());
        // dateRecyclerView.setLayoutParams();
        announcementRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        announcementRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        announcementRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        announcementlistAdapter = new NewsListAdapter(getContext(), null, 1,new NewsListAdapter.IOnDetailListener() {
            @Override
            public void onDetailListener(NewsModel model) {
                gotoDetailFragment(model.getId());
            }
        });
        announcementRecyclerView.setAdapter(announcementlistAdapter);

        announcementRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                LogUtil.i("announcementRecyclerView--->onRefresh");
                getAnnouncementList(0, 0, REFRESH_TYPE);


            }

            @Override
            public void onLoadMore() {
                LogUtil.i("announcementRecyclerView--->onLoadMore");
                getAnnouncementList(a_currentPage, a_iRecordCount, LOADMORE_TYPE);
            }
        });


        policyrecyclerView = new XRecyclerView(getContext());
        // dateRecyclerView.setLayoutParams();
        policyrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        policyrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        policyrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        policylistAdapter = new NewsListAdapter(getContext(), null, 2, new NewsListAdapter.IOnDetailListener() {
            @Override
            public void onDetailListener(NewsModel model) {
                gotoDetailFragment(model.getId());
            }
            }
        );
        policyrecyclerView.setAdapter(policylistAdapter);

        policyrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
             @Override
             public void onRefresh() {
                 LogUtil.i("news_recyclerview--->onRefresh");
                 getPolicyList(0, 0, REFRESH_TYPE);


             }

             @Override
             public void onLoadMore() {
                 LogUtil.i("news_recyclerview--->onLoadMore");
                 getPolicyList(n_currentPage, n_iRecordCount, LOADMORE_TYPE);
             }
        });



        ArrayList<View> views = new ArrayList<>();
        views.add( newsRecyclerView);
        views.add( announcementRecyclerView);
        views.add( policyrecyclerView);
        HomeNewsListViewPagerAdapter viewPagerAdapter = new HomeNewsListViewPagerAdapter(views, new String[]{"新闻", "通知","政策"});
        mNewsViewPager.setAdapter(viewPagerAdapter);
        mPagerIndicator.setViewPager(mNewsViewPager, 0);

    }


    private void getNewsList(int currentPage, int iRecordCount, final int type) {
        ApiManager.getInstance().getAllNewsList(PAGESIZE, currentPage, iRecordCount, "createdate", "desc", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->onError::" + e);
                if (type == REFRESH_TYPE) {
                    newsRecyclerView.refreshComplete();
                } else {
                    newsRecyclerView.loadMoreComplete();

                }
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->onResponse::" + response);
                NewsListModel newsListModel = new Gson().fromJson(response, NewsListModel.class);
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->newsListModel::" + newsListModel);
                NewsModels newsModels = new Gson().fromJson(newsListModel.getStrResult(), NewsModels.class);
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->NewsModels::" + newsModels);
                if (type == REFRESH_TYPE) {
                    if (newsListModel != null&&newsModels!=null) {
                        n_currentPage = newsModels.getCurrentPage();
                        n_currentPage++;
                        n_iRecordCount = newsModels.getRecordCount();
                    }
                    newsListAdapter.setNewsData(newsModels.getData());
                    newsRecyclerView.refreshComplete();
                } else {

                    if (newsListModel != null) {
                        if (n_currentPage <= newsModels.getCurrentPage()) {
                            n_currentPage = newsModels.getCurrentPage();
                            n_currentPage++;
                            n_iRecordCount = newsModels.getRecordCount();
                            newsListAdapter.addNewsData(newsModels.getData());
                        }
                        newsRecyclerView.loadMoreComplete();
                    }
                }

            }
        });
    }
    private void getAnnouncementList(int currentPage, int iRecordCount, final int type) {
        ApiManager.getInstance().getNewsList("1001",PAGESIZE, currentPage, iRecordCount, "createdate", "desc", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->onError::" + e);
                if (type == REFRESH_TYPE) {
                    announcementRecyclerView.refreshComplete();
                } else {
                    announcementRecyclerView.loadMoreComplete();

                }
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->onResponse::" + response);
                NewsListModel newsListModel = new Gson().fromJson(response, NewsListModel.class);
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->newsListModel::" + newsListModel);
                NewsModels newsModels = new Gson().fromJson(newsListModel.getStrResult(), NewsModels.class);
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->NewsModels::" + newsModels);
                if (type == REFRESH_TYPE) {
                    if (newsListModel != null&&newsModels!=null) {
                        a_currentPage = newsModels.getCurrentPage();
                        a_currentPage++;
                        a_iRecordCount = newsModels.getRecordCount();
                    }
                    announcementlistAdapter.setNewsData(newsModels.getData());
                    announcementRecyclerView.refreshComplete();
                } else {

                    if (newsListModel != null) {
                        if (a_currentPage <= newsModels.getCurrentPage()) {
                            a_currentPage = newsModels.getCurrentPage();
                            a_currentPage++;
                            a_iRecordCount = newsModels.getRecordCount();
                            announcementlistAdapter.addNewsData(newsModels.getData());
                        }
                        announcementRecyclerView.loadMoreComplete();
                    }
                }

            }
        });
    }
    private void getPolicyList(int currentPage, int iRecordCount, final int type) {
        ApiManager.getInstance().getNewsList("1002",PAGESIZE, currentPage, iRecordCount, "createdate", "desc", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->onError::" + e);
                if (type == REFRESH_TYPE) {
                    policyrecyclerView.refreshComplete();
                } else {
                    policyrecyclerView.loadMoreComplete();

                }
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->onResponse::" + response);
                NewsListModel newsListModel = new Gson().fromJson(response, NewsListModel.class);
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->newsListModel::" + newsListModel);
                NewsModels newsModels = new Gson().fromJson(newsListModel.getStrResult(), NewsModels.class);
                LogUtil.i("AnnouncementFragment--->getNormalList--->getNewsList--->NewsModels::" + newsModels);
                if (type == REFRESH_TYPE) {
                    if (newsListModel != null&&newsModels!=null) {
                        p_currentPage = newsModels.getCurrentPage();
                        p_currentPage++;
                        p_iRecordCount = newsModels.getRecordCount();
                    }
                    policylistAdapter.setNewsData(newsModels.getData());
                    policyrecyclerView.refreshComplete();
                } else {

                    if (newsListModel != null) {
                        if (p_currentPage <= newsModels.getCurrentPage()) {
                            p_currentPage = newsModels.getCurrentPage();
                            p_currentPage++;
                            p_iRecordCount = newsModels.getRecordCount();
                            policylistAdapter.addNewsData(newsModels.getData());
                        }
                        policyrecyclerView.loadMoreComplete();
                    }
                }

            }
        });
    }
    public void startAdTimer() {
        if (mTimerTask == null) {
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    MyHandle.sendEmptyMessage(0);
                }
            };
            mTimer.schedule(mTimerTask, 5000, 5000);
        }
    }

    public void stopAdTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        stopAdTimer();
    }
}
