package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nfu.oldwork.R;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.FilialStarDetail;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.QuerySimpleModel;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.ToastUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017-7-27.
 */

public class QueryFealtyDetailFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv__starnum)
    TextView tv__starnum;
    @BindView(R.id.tv_nation)
    TextView tv_nation;
    @BindView(R.id.tv__birthday)
    TextView tv__birthday;
    @BindView(R.id.tv_polity)
    TextView tv_polity;
    @BindView(R.id.tv__mobiephone)
    TextView tv__mobiephone;
    @BindView(R.id.tv_telephone)
    TextView tv_telephone;
    @BindView(R.id.tv__cultureType)
    TextView tv__cultureType;
    @BindView(R.id.tv_postalParcel)
    TextView tv_postalParcel;
    @BindView(R.id.tv__cardnum)
    TextView tv__cardnum;
    /*@BindView(R.id.tv__state)
    TextView tv__state;*/
    @BindView(R.id.tv__job)
    TextView tv__job;
    @BindView(R.id.tv__workunit)
    TextView tv__workunit;
    @BindView(R.id.tv__address)
    TextView tv__address;
    @BindView(R.id.tv__applyway)
    TextView tv__applyway;
    @BindView(R.id.tv__storyType)
    TextView tv__storyType;

    @BindView(R.id.tv__baRemarks)
    TextView tv__baRemarks;
    @BindView(R.id.tv__countiesIdea)
    TextView tv__countiesIdea;
    @BindView(R.id.tv__cityIdea)
    TextView tv__cityIdea;
    @BindView(R.id.tv__recommendUser1)
    TextView tv__recommendUser1;
    @BindView(R.id.tv__recommendUser2)
    TextView tv__recommendUser2;
    @BindView(R.id.tv__recommendUser3)
    TextView tv__recommendUser3;
    @BindView(R.id.tv_recommendUserPhone1)
    TextView tv_recommendUserPhone1;
    @BindView(R.id.tv_recommendUserPhone2)
    TextView tv_recommendUserPhone2;
    @BindView(R.id.tv_recommendUserPhone3)
    TextView tv_recommendUserPhone3;
    @BindView(R.id.tv_selfStory)
    TextView tv_selfStory;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.query_fealty_detail_fragment, container);
        initView();
        loadData();
        return rootView;
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        getNormalList(id);
    }

    @Override
    protected void initView() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

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

    private void getNormalList(String id) {
        QuerySimpleModel simpleModel = new QuerySimpleModel();
        simpleModel.setSignKey(ApiConfig.signKey);
        simpleModel.setStarId(id);
        String str = new Gson().toJson(simpleModel);
        ApiManager.getInstance().getFilialStarDetail(str, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("QueryFealtyDetailFragment--->getNormalList--->onError::" + e);

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("QueryFealtyDetailFragment--->getNormalList--->onResponse::" + response);
                if(getContext()!=null){
                    NewsListModel models = new Gson().fromJson(response,NewsListModel.class);
                    LogUtil.i("QueryFealtyDetailFragment--->getNormalList--->NewsListModel::" + models);
                    FilialStarDetail filialStarDetail = new Gson().fromJson(models.getStrResult(),FilialStarDetail.class);
                    LogUtil.i("QueryFealtyDetailFragment--->getNormalList--->FilialStarDetail::" + filialStarDetail);
                    if (filialStarDetail!=null){
                        tv_name.setText(filialStarDetail.getOldName());
                        tv_sex.setText(filialStarDetail.getSex());
                        tv__starnum.setText(filialStarDetail.getStarNumber());
                        tv_nation.setText(filialStarDetail.getNation());
                        tv__birthday.setText(filialStarDetail.getBirthdate());
                        tv_polity.setText(filialStarDetail.getPolity());
                        tv_telephone.setText(filialStarDetail.getLinkTelephone());
                        tv__mobiephone.setText(filialStarDetail.getLinkPhone());
                        tv__cultureType.setText(filialStarDetail.getCultureType());
                        tv_postalParcel.setText(filialStarDetail.getPostalParcel());
                        tv__cardnum.setText(filialStarDetail.getCertificatesNumber());
                        tv__job.setText(filialStarDetail.getJobType());
                        tv__workunit.setText(filialStarDetail.getWorkUnit());
                        tv__address.setText(filialStarDetail.getAddress());
                        tv__applyway.setText(filialStarDetail.getApplyway());
                        tv__storyType.setText(filialStarDetail.getStoryType());
                        tv__baRemarks.setText(filialStarDetail.getBaRemarks());
                        tv__countiesIdea.setText(filialStarDetail.getCountiesIdea());
                        tv__cityIdea.setText(filialStarDetail.getCityIdea());
                        tv__recommendUser1.setText(filialStarDetail.getRecommendUser());
                        tv_recommendUserPhone1.setText(filialStarDetail.getRecommendUserPhone());
                        tv__recommendUser2.setText(filialStarDetail.getRecommendUser1());
                        tv_recommendUserPhone2.setText(filialStarDetail.getRecommendUserPhone1());
                        tv__recommendUser3.setText(filialStarDetail.getRecommendUser2());
                        tv_recommendUserPhone3.setText(filialStarDetail.getRecommendUserPhone2());
                        tv_selfStory.setText(filialStarDetail.getSelfStory());
                    }else {
                        ToastUtil.showShortToast(getContext(),models.getStrError());
                    }

                }

            }
        });
    }
}
