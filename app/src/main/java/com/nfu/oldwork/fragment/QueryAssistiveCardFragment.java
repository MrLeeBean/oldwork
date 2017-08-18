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
import com.nfu.oldwork.adapter.AssistiveListAdapter;
import com.nfu.oldwork.adapter.FilialListAdapter;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.AssistiveCard;
import com.nfu.oldwork.model.AssistiveCardList;
import com.nfu.oldwork.model.FilialStar;
import com.nfu.oldwork.model.FilialStarList;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.QueryFilial;
import com.nfu.oldwork.model.QueryOlder;
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

public class QueryAssistiveCardFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_cardnum)
    EditText et_cardnum;
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
    private AssistiveListAdapter assistiveListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.query_assistivecard_fragment, container);
        initView();
        loadData();
        return rootView;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNormalList(0,0,REFRESH_TYPE);
            }
        });

        assistiveListAdapter = new AssistiveListAdapter(getContext(), new AssistiveListAdapter.IOnDetailListener() {
            @Override
            public void onDetailListener(AssistiveCard model) {
                QueryAssistiveCardDetailFragment queryAssistiveCardDetailFragment = new QueryAssistiveCardDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id",model.getId());
                queryAssistiveCardDetailFragment.setArguments(bundle);
                gotoDetailFragment(queryAssistiveCardDetailFragment);
            }
        });
        card_xrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        card_xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        card_xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        card_xrecyclerView.setAdapter(assistiveListAdapter);
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
        final QueryOlder queryOlder = new QueryOlder();
        UserInfo userInfo = SharedPreferencesManager.getUser("userinfo","UserInfo",null);
        queryOlder.setSignKey(ApiConfig.signKey);
        queryOlder.setiPageSize(pageSize);
        queryOlder.setiCurrentPage(currentPage);
        queryOlder.setiRecordCount(iRecordCount);
        queryOlder.setName(TextUtils.isEmpty(et_name.getText().toString())?"":et_name.getText().toString());
        queryOlder.setCum(TextUtils.isEmpty(et_cardnum.getText().toString())?"":et_cardnum.getText().toString());
        if (TextUtils.isEmpty(userInfo.getCityId())){
            queryOlder.setCityId(0);
        }else {
            queryOlder.setCityId(Integer.parseInt(userInfo.getCityId()));
        }

        if (TextUtils.isEmpty(userInfo.getCountryId())){
            queryOlder.setCountyId(0);
        }else {
            queryOlder.setCountyId(Integer.parseInt(userInfo.getCountryId()));
        }

        if (TextUtils.isEmpty(userInfo.getStreetId())){
            queryOlder.setStreetId(0);
        }else {
            queryOlder.setStreetId(Integer.parseInt(userInfo.getStreetId()));
        }

        if (TextUtils.isEmpty(userInfo.getCommunityId())){
            queryOlder.setCommunityId(0);
        }else {
            queryOlder.setCommunityId(Integer.parseInt(userInfo.getCommunityId()));
        }
        String str = new Gson().toJson(queryOlder);
        ApiManager.getInstance().getAssistiveCard(str, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("QueryAssistiveCardFragment--->getNormalList--->onError::" + e);
                if (type == REFRESH_TYPE) {
                    card_xrecyclerView.refreshComplete();
                } else {
                    card_xrecyclerView.loadMoreComplete();

                }
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("QueryAssistiveCardFragment--->getNormalList--->onResponse::" + response);

                if(getContext()!=null){
                    NewsListModel models = new Gson().fromJson(response,NewsListModel.class);
                    LogUtil.i("QueryAssistiveCardFragment--->getNormalList--->NewsListModel::" + models);
                    AssistiveCardList assistiveCardList = new Gson().fromJson(models.getStrResult(),AssistiveCardList.class);
                    LogUtil.i("QueryAssistiveCardFragment--->getNormalList--->AssistiveCardList::" + assistiveCardList);
                    if (REFRESH_TYPE==type){
                        card_xrecyclerView.refreshComplete();
                        List<AssistiveCard> commList = assistiveCardList.getData();
                        if (commList!=null&&commList.size()>0){

                            c_currentPage = assistiveCardList.getCurrentPage();
                            c_currentPage++;
                            c_recordCount = assistiveCardList.getRecordCount();


                        }
                        assistiveListAdapter.setNewsData(commList);
                    }else {
                        card_xrecyclerView.loadMoreComplete();
                        List<AssistiveCard> commList = assistiveCardList.getData();
                        if (commList!=null&&commList.size()>0){
                            if (c_currentPage<=assistiveCardList.getCurrentPage()){
                                c_currentPage = assistiveCardList.getCurrentPage();
                                c_currentPage++;
                                c_recordCount = assistiveCardList.getRecordCount();
                                assistiveListAdapter.addNewsData(commList);
                            }


                        }
                    }
                }

            }
        });
    }
}
