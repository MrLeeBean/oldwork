package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nfu.oldwork.R;
import com.nfu.oldwork.adapter.CommunicationListAdapter;
import com.nfu.oldwork.adapter.FilialListAdapter;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.CommunicationInfo;
import com.nfu.oldwork.model.FilialStar;
import com.nfu.oldwork.model.FilialStarList;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.QueryFilial;
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.DateUtil;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017-7-27.
 */

public class QueryFealtyFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_cardnum)
    EditText et_cardnum;
    @BindView(R.id.et_seledt_year)
    EditText et_year;
    @BindView(R.id.btn_query )
    Button btn_query;
    int e_year = 2018;
    @BindView(R.id.card_xrecyclerView)
    XRecyclerView card_xrecyclerView;


    private int pageSize = 10;
    private int c_currentPage = 0;
    private int c_recordCount = 0;
    private final static int REFRESH_TYPE = 1001;
    private final static int LOADMORE_TYPE = 1002;
    private FilialListAdapter filialListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.query_fealty_fragment, container);
        initView();
        loadData();
        return rootView;
    }

    @Override
    protected void loadData() {
        String year = DateUtil.getCurrentMsecString("yyyy");
        e_year = Integer.parseInt(year);
        et_year.setText(year);
    }

    @Override
    protected void initView() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        et_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBDateDialog();
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNormalList(0,0,REFRESH_TYPE);
            }
        });

        filialListAdapter = new FilialListAdapter(getContext(), new FilialListAdapter.IOnDetailListener() {
            @Override
            public void onDetailListener(FilialStar model) {
                QueryFealtyDetailFragment queryFealtyDetailFragment = new QueryFealtyDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id",model.getId());
                queryFealtyDetailFragment.setArguments(bundle);
                gotoDetailFragment(queryFealtyDetailFragment);
            }
        });
        card_xrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        card_xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        card_xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        card_xrecyclerView.setAdapter(filialListAdapter);
        card_xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                LogUtil.i("communicationlist--->onRefresh");
                getNormalList(0,0,REFRESH_TYPE);

            }

            @Override
            public void onLoadMore() {
                LogUtil.i("communicationlist--->onRefresh");
                getNormalList(c_currentPage,c_recordCount,LOADMORE_TYPE);
            }
        });
    }

    private void showBDateDialog(){
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                et_year.setText(getTime(date));
            }
        }).setRange(e_year-90,e_year).setTitleText("年份").setType(new boolean[]{true,false,false,false,false,false})
                .build();

        //pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    private void gotoDetailFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(this);
        fragmentTransaction.add(R.id.activity_main_content_frameLayout , fragment);
        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void getNormalList(final int currentPage, int iRecordCount, final int type) {
        final QueryFilial queryFilial = new QueryFilial();
        UserInfo userInfo = SharedPreferencesManager.getUser("userinfo","UserInfo",null);
        queryFilial.setSignKey(ApiConfig.signKey);
        queryFilial.setStarYear(Integer.parseInt(et_year.getText().toString()));
        queryFilial.setiPageSize(pageSize);
        queryFilial.setiCurrentPage(currentPage);
        queryFilial.setiRecordCount(iRecordCount);
        queryFilial.setName(TextUtils.isEmpty(et_name.getText().toString())?"":et_name.getText().toString());
        queryFilial.setCum(TextUtils.isEmpty(et_cardnum.getText().toString())?"":et_cardnum.getText().toString());
        if (TextUtils.isEmpty(userInfo.getCityId())){
            queryFilial.setCityId(0);
        }else {
            queryFilial.setCityId(Integer.parseInt(userInfo.getCityId()));
        }

        if (TextUtils.isEmpty(userInfo.getCountryId())){
            queryFilial.setCountyId(0);
        }else {
            queryFilial.setCountyId(Integer.parseInt(userInfo.getCountryId()));
        }

        if (TextUtils.isEmpty(userInfo.getStreetId())){
            queryFilial.setStreetId(0);
        }else {
            queryFilial.setStreetId(Integer.parseInt(userInfo.getStreetId()));
        }

        if (TextUtils.isEmpty(userInfo.getCommunityId())){
            queryFilial.setCommunityId(0);
        }else {
            queryFilial.setCommunityId(Integer.parseInt(userInfo.getCommunityId()));
        }
        String str = new Gson().toJson(queryFilial);
        ApiManager.getInstance().getFilialStar(str, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("QueryFealtyFragment--->getNormalList--->onError::" + e);
                if (type == REFRESH_TYPE) {
                    card_xrecyclerView.refreshComplete();
                } else {
                    card_xrecyclerView.loadMoreComplete();

                }
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("QueryFealtyFragment--->getNormalList--->onResponse::" + response);

                if(getContext()!=null){
                    NewsListModel models = new Gson().fromJson(response,NewsListModel.class);
                    LogUtil.i("QueryFealtyFragment--->getNormalList--->NewsListModel::" + models);
                    FilialStarList filialStarList = new Gson().fromJson(models.getStrResult(),FilialStarList.class);
                    LogUtil.i("QueryFealtyFragment--->getNormalList--->FilialStarList::" + filialStarList);
                    if (REFRESH_TYPE==type){
                        card_xrecyclerView.refreshComplete();
                        List<FilialStar> commList = filialStarList.getData();
                        if (commList!=null&&commList.size()>0){

                            c_currentPage = filialStarList.getCurrentPage();
                            c_currentPage++;
                            c_recordCount = filialStarList.getRecordCount();


                        }
                        filialListAdapter.setNewsData(commList);
                    }else {
                        card_xrecyclerView.loadMoreComplete();
                        List<FilialStar> commList = filialStarList.getData();
                        if (commList!=null&&commList.size()>0){
                            if (c_currentPage<=filialStarList.getCurrentPage()){
                                c_currentPage = filialStarList.getCurrentPage();
                                c_currentPage++;
                                c_recordCount = filialStarList.getRecordCount();
                                filialListAdapter.addNewsData(commList);
                            }


                        }
                    }
                }

            }
        });
    }
}
