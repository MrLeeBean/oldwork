package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nfu.oldwork.R;
import com.nfu.oldwork.adapter.CommunicationDetailListAdapter;
import com.nfu.oldwork.adapter.CommunicationListAdapter;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.CommDetail;
import com.nfu.oldwork.model.CommunicationInfo;
import com.nfu.oldwork.model.CommunicationList;
import com.nfu.oldwork.model.CommunicationModel;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.QueryModel;
import com.nfu.oldwork.model.ReplyInfo;
import com.nfu.oldwork.utils.AppUtils;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ActionSheetWindow;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/11.
 */

public class CommunicateDetailFragment extends BaseFragment{
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.communicationList)
    XRecyclerView communicationlist;
    @BindView(R.id.tv_title)
    TextView tv_tile;
    @BindView(R.id.tv_eye)
    TextView tv_eye;
    @BindView(R.id.tv_reply)
    TextView tv_reply;
    @BindView(R.id.card_view)
    CardView cardView;
    //@BindView(R.id.iv_nodata)
    //ImageView iv_nodata;

    private int pageSize = 10;
    private int c_currentPage = 0;
    private int c_recordCount = 0;
    private final static int REFRESH_TYPE = 1001;
    private final static int LOADMORE_TYPE = 1002;
    private CommunicationDetailListAdapter detailListAdapter;
    private ActionSheetWindow commitWindow;
    private String id = null;
    private String title = null;

    int index = 0;
    private int[] arrIds = new int[]{8007,8008,8009,8010,8011,8012};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "CommunicateFragment **** onCreateView...");
        bindView(inflater,R.layout.communicate_detail_fragment,container);
        initView();
        loadData();
        return rootView;
    }
    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        //jsonStr = "{\"signKey\":" + ApiConfig.signKey + ",\"id\":\"" + id + "\"}";
        getNormalList(0,0,REFRESH_TYPE);
    }

    @Override
    protected void initView() {


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        detailListAdapter = new CommunicationDetailListAdapter(getContext(), new CommunicationDetailListAdapter.IOnDetailListener() {
            @Override
            public void onDetailListener(ReplyInfo model) {

            }
        });
        communicationlist.setLayoutManager(new LinearLayoutManager(getContext()));
        communicationlist.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        communicationlist.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        communicationlist.setAdapter(detailListAdapter);
        // policy_recyclerview.addItemDecoration(new MyItemDecoration(getContext(),MyItemDecoration.VERTICAL_LIST));
        communicationlist.setLoadingListener(new XRecyclerView.LoadingListener() {
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

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInform();
            }
        });
    }

    private void showInform(){
       if (commitWindow==null){
           commitWindow = new ActionSheetWindow(getContext(), new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   // 隐藏弹出窗口
                   commitWindow.dismiss();
                   switch (v.getId()) {
                       case R.id.takePhotoBtn://

                           if(NfuResource.isLoginSuccess){
                               CommReplyFragment questionFragment = new CommReplyFragment();
                               Bundle bundle = new Bundle();
                               bundle.putString("id",id);
                               bundle.putString("title",title);
                               questionFragment.setArguments(bundle);
                               gotoDetailFragment(questionFragment);
                           }else {
                               ToastUtil.showShortToast(getContext(),"您还未登录，不能进行回复！");
                           }

                           break;
                       case R.id.cancelBtn:// 取消
                           break;
                       default:
                           break;
                   }
               }
           }).setText("回复");
       }
       commitWindow.showAtLocation(rootView, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void getNormalList(final int currentPage, int iRecordCount, final int type) {
        QueryModel queryModel = new QueryModel();
        queryModel.setId(id);
        queryModel.setSignKey(ApiConfig.signKey);
        queryModel.setiPageSize(pageSize);
        queryModel.setiCurrentPage(c_currentPage);
        queryModel.setiRecordCount(c_recordCount);
        String jsonStr = new Gson().toJson(queryModel);

        ApiManager.getInstance().getCommunicationDetail(jsonStr, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("CommunicateFragment--->getNormalList--->onError::" + e);
                if (type == REFRESH_TYPE) {
                    communicationlist.refreshComplete();
                } else {
                    communicationlist.loadMoreComplete();

                }
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("CommunicateFragment--->getNormalList--->onResponse::" + response);
                NewsListModel models = new Gson().fromJson(response,NewsListModel.class);
                LogUtil.i("CommunicateFragment--->getNormalList--->NewsListModel::" + models);
                CommDetail details = new Gson().fromJson(models.getStrResult(),CommDetail.class);
                LogUtil.i("CommunicateFragment--->getNormalList--->CommDetail::" + details);
                if (REFRESH_TYPE==type){
                    communicationlist.refreshComplete();
                    if (details!=null){
                       // communicationlist.setVisibility(View.VISIBLE);
                       // iv_nodata.setVisibility(View.INVISIBLE);
                        title = details.getTitle();
                        c_currentPage = details.getCurrentPage();
                        c_currentPage++;
                        c_recordCount = details.getRecordCount();
                        tv_tile.setText(details.getTitle());
                        tv_eye.setText(AppUtils.str2Num(details.getViewCount()));
                        tv_reply.setText(AppUtils.str2Num(details.getRespondCount()));
                    }
                    detailListAdapter.setNewsData(details);
                }else {
                    communicationlist.loadMoreComplete();
                    if (details!=null){
                        title = details.getTitle();
                        if (c_currentPage <= details.getCurrentPage()){
                            c_currentPage = details.getCurrentPage();
                            c_currentPage++;
                            c_recordCount = details.getRecordCount();
                            detailListAdapter.addNewsData(details);
                        }
                        tv_tile.setText(details.getTitle());
                        tv_eye.setText(AppUtils.str2Num(details.getViewCount()));
                        tv_reply.setText(AppUtils.str2Num(details.getRespondCount()));
                    }
                }
            }
        });
    }

    private void gotoDetailFragment(Fragment fragment){
        FragmentManager fragmentManager = CommunicateDetailFragment.this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.hide(CommunicateFragment.this);
        fragmentTransaction.replace(R.id.activity_main_content_frameLayout , fragment);
        fragmentTransaction.addToBackStack(null);
       // fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);

        fragmentTransaction.commit();
    }

}
