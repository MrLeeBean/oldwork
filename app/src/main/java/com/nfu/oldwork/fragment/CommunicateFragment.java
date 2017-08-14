package com.nfu.oldwork.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.baoyz.actionsheet.ActionSheet;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nfu.oldwork.R;
import com.nfu.oldwork.adapter.CommunicationListAdapter;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.CommunicationInfo;
import com.nfu.oldwork.model.CommunicationList;
import com.nfu.oldwork.model.CommunicationModel;
import com.nfu.oldwork.model.NewsList;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.NewsModel;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.view.ActionSheetWindow;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Headers;

/**
 * Created by Administrator on 2017/8/11.
 */

public class CommunicateFragment extends BaseFragment{
    @BindView(R.id.iv_publish)
    ImageView iv_publish;
    @BindView(R.id.sp_conditon)
    Spinner sp_condition;
    @BindView(R.id.communicationList)
    XRecyclerView communicationlist;
    //@BindView(R.id.iv_nodata)
    //ImageView iv_nodata;

    private int pageSize = 10;
    private int c_currentPage = 0;
    private int c_recordCount = 0;
    private final static int REFRESH_TYPE = 1001;
    private final static int LOADMORE_TYPE = 1002;
    private CommunicationListAdapter communicationListAdapter;
    private ActionSheetWindow commitWindow;

    int index = 0;
    private int[] arrIds = new int[]{8007,8008,8009,8010,8011,8012};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "CommunicateFragment **** onCreateView...");
        bindView(inflater,R.layout.communicate_fragment,container);
        initView();
        loadData();
        return rootView;
    }
    @Override
    protected void loadData() {
        //getNormalList(0,0,REFRESH_TYPE);
    }

    @Override
    protected void initView() {
        sp_condition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                LogUtil.i("CommunicateFragment--->setOnItemSelectedListener--->index"+index);
                getNormalList(0,0,REFRESH_TYPE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        iv_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInform();
                /*CommQuestionFragment commQuestionFragment = new CommQuestionFragment();
                gotoDetailFragment(commQuestionFragment);*/
            }
        });

        communicationListAdapter = new CommunicationListAdapter(getContext(), new CommunicationListAdapter.IOnDetailListener() {
            @Override
            public void onDetailListener(CommunicationInfo model) {

            }
        });
        communicationlist.setLayoutManager(new LinearLayoutManager(getContext()));
        communicationlist.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        communicationlist.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        communicationlist.setAdapter(communicationListAdapter);
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
                getNormalList(c_currentPage,c_recordCount,REFRESH_TYPE);
            }
        });

    }

    private String[] informs = new String[]{"我要提问"};
    private int gotoIndex = -1;
    private void showInform(){
       if (commitWindow==null){
           commitWindow = new ActionSheetWindow(getContext(), new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   // 隐藏弹出窗口
                   commitWindow.dismiss();
                   switch (v.getId()) {
                       case R.id.takePhotoBtn://
                           CommQuestionFragment questionFragment = new CommQuestionFragment();
                           gotoDetailFragment(questionFragment);
                           break;
                       case R.id.cancelBtn:// 取消
                           break;
                       default:
                           break;
                   }
               }
           }).setText("我要提问");
       }
       commitWindow.showAtLocation(rootView, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void getNormalList(final int currentPage, int iRecordCount, final int type) {
        final CommunicationModel communicationModel = new CommunicationModel();
        communicationModel.setSignKey(ApiConfig.signKey);
        communicationModel.setTypeId(arrIds[index]);
        communicationModel.setiPageSize(pageSize);
        communicationModel.setiCurrentPage(currentPage);
        communicationModel.setiRecordCount(iRecordCount);
        String str = new Gson().toJson(communicationModel);
        ApiManager.getInstance().getCommunicationList(str, new StringCallback() {
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
                CommunicationList communicationList = new Gson().fromJson(models.getStrResult(),CommunicationList.class);
                LogUtil.i("CommunicateFragment--->getNormalList--->communicationList::" + communicationList);
                if (REFRESH_TYPE==type){
                    communicationlist.refreshComplete();
                    List<CommunicationInfo> commList = communicationList.getData();
                    if (commList!=null&&commList.size()>0){
                       // communicationlist.setVisibility(View.VISIBLE);
                       // iv_nodata.setVisibility(View.INVISIBLE);
                        c_currentPage = communicationList.getCurrentPage();
                        c_currentPage++;
                        c_recordCount = communicationList.getRecordCount();


                    }
                    communicationListAdapter.setNewsData(commList);
                }else {
                    communicationlist.loadMoreComplete();
                    List<CommunicationInfo> commList = communicationList.getData();
                    if (commList!=null&&commList.size()>0){
                        if (c_currentPage<=communicationList.getCurrentPage()){
                            c_currentPage = communicationList.getCurrentPage();
                            c_currentPage++;
                            c_recordCount = communicationList.getRecordCount();
                            communicationListAdapter.addNewsData(commList);
                        }


                    }
                }
            }
        });
    }

    private void gotoDetailFragment(Fragment fragment){
        FragmentManager fragmentManager = CommunicateFragment.this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.hide(CommunicateFragment.this);
        fragmentTransaction.replace(R.id.activity_main_content_frameLayout , fragment);
        fragmentTransaction.addToBackStack(null);
       // fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);

        fragmentTransaction.commit();
    }

}
