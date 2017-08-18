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
import com.nfu.oldwork.model.AssistiveCardDetail;
import com.nfu.oldwork.model.FilialStarDetail;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.QuerySimpleModel;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.ToastUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017-7-27.
 */

public class QueryAssistiveCardDetailFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv__certificatesNumber)
    TextView tv__certificatesNumber;
    @BindView(R.id.tv__birthday)
    TextView tv__birthday;
    @BindView(R.id.tv__householdCounty)
    TextView tv__householdCounty;
    @BindView(R.id.tv__householdStreet)
    TextView tv__householdStreet;
    @BindView(R.id.tv__householdCommunity)
    TextView tv__householdCommunity;
    @BindView(R.id.tv__householdAddress)
    TextView tv__householdAddress;
    @BindView(R.id.tv__residenceCounty)
    TextView tv__residenceCounty;
    @BindView(R.id.tv__residenceStreet)
    TextView tv__residenceStreet;
    @BindView(R.id.tv__residenceCommunity)
    TextView tv__residenceCommunity;
    @BindView(R.id.tv__residenceAddress)
    TextView tv__residenceAddress;
    @BindView(R.id.tv__contacterTel)
    TextView tv__contacterTel;
    @BindView(R.id.tv_contacterMobile)
    TextView tv_contacterMobile;
    @BindView(R.id.tv__isNonlocal)
    TextView tv__isNonlocal;

    @BindView(R.id.tv__idcradDept)
    TextView tv__idcradDept;
    @BindView(R.id.tv__contacter)
    TextView tv__contacter;
    @BindView(R.id.tv__contacterIdcardNumber)
    TextView tv__contacterIdcardNumber;
    @BindView(R.id.tv__contacterMobileNumber)
    TextView tv__contacterMobileNumber;
    @BindView(R.id.tv__cityPushed)
    TextView tv__cityPushed;
    @BindView(R.id.tv__cityPushedDate)
    TextView tv__cityPushedDate;
    @BindView(R.id.tv__hcPushedDate)
    TextView tv__hcPushedDate;
    @BindView(R.id.tv__hcBackInTime)
    TextView tv__hcBackInTime;
    @BindView(R.id.tv__hcSuccess)
    TextView tv__hcSuccess;
    @BindView(R.id.tv__hcReason)
    TextView tv__hcReason;
    @BindView(R.id.tv__pushedDate)
    TextView tv__pushedDate;
    @BindView(R.id.tv__backInTime)
    TextView tv__backInTime;
    @BindView(R.id.tv__makeCardSuccess)
    TextView tv__makeCardSuccess;
    @BindView(R.id.tv__failureReason)
    TextView tv__failureReason;
    @BindView(R.id.tv__bankCard)
    TextView tv__bankCard;
    @BindView(R.id.tv__bookId)
    TextView tv__bookId;
    @BindView(R.id.tv__bjtNumber)
    TextView tv__bjtNumber;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.query_assistive_detail_fragment, container);
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
        simpleModel.setOldId(id);
        String str = new Gson().toJson(simpleModel);
        ApiManager.getInstance().getAssistiveCardDetail(str, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.i("QueryAssistiveCardDetailFragment--->getNormalList--->onError::" + e);

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.i("QueryAssistiveCardDetailFragment--->getNormalList--->onResponse::" + response);
                if(getContext()!=null){
                    NewsListModel models = new Gson().fromJson(response,NewsListModel.class);
                    LogUtil.i("QueryAssistiveCardDetailFragment--->getNormalList--->NewsListModel::" + models);
                    AssistiveCardDetail assistiveCardDetail = new Gson().fromJson(models.getStrResult(),AssistiveCardDetail.class);
                    LogUtil.i("QueryAssistiveCardDetailFragment--->getNormalList--->AssistiveCardDetail::" + assistiveCardDetail);
                    if (assistiveCardDetail!=null){
                        tv_name.setText(assistiveCardDetail.getName());
                        tv__certificatesNumber.setText(assistiveCardDetail.getCertificatesNumber());
                        tv_sex.setText(assistiveCardDetail.getSex());
                        tv__birthday.setText(assistiveCardDetail.getBirthdate());
                        tv__householdCounty.setText(assistiveCardDetail.getHouseholdCounty());
                        tv__householdStreet.setText(assistiveCardDetail.getHouseholdStreet());
                        tv__householdCommunity.setText(assistiveCardDetail.getHouseholdCommunity());
                        tv__householdAddress.setText(assistiveCardDetail.getHouseholdAddress());
                        tv__residenceCounty.setText(assistiveCardDetail.getResidenceCounty());
                        tv__residenceStreet.setText(assistiveCardDetail.getResidenceStreet());
                        tv__residenceCommunity.setText(assistiveCardDetail.getResidenceCommunity());
                        tv__residenceAddress.setText(assistiveCardDetail.getResidenceAddress());
                        tv__contacterTel.setText(assistiveCardDetail.getContacterTel());
                        tv_contacterMobile.setText(assistiveCardDetail.getContacterMobile());
                        tv__isNonlocal.setText(assistiveCardDetail.getIsNonlocal());
                        tv__idcradDept.setText(assistiveCardDetail.getIdcradDept());
                        tv__contacter.setText(assistiveCardDetail.getContacter());
                        tv__contacterIdcardNumber.setText(assistiveCardDetail.getContacterIdcardNumber());
                        tv__contacterMobileNumber.setText(assistiveCardDetail.getContacterMobileNumber());

                        tv__cityPushed.setText(assistiveCardDetail.getCityPushed());
                        tv__cityPushedDate.setText(assistiveCardDetail.getCityPushedDate());
                        tv__hcPushedDate.setText(assistiveCardDetail.getHcPushedDate());
                        tv__hcBackInTime.setText(assistiveCardDetail.getHcBackInTime());
                        tv__hcSuccess.setText(assistiveCardDetail.getHcSuccess());
                        tv__hcReason.setText(assistiveCardDetail.getHcReason());
                        tv__pushedDate.setText(assistiveCardDetail.getPushedDate());
                        tv__backInTime.setText(assistiveCardDetail.getBackInTime());

                        tv__makeCardSuccess.setText(assistiveCardDetail.getMakeCardSuccess());
                        tv__failureReason.setText(assistiveCardDetail.getFailureReason());
                        tv__bankCard.setText(assistiveCardDetail.getBankCard());
                        tv__bookId.setText(assistiveCardDetail.getBookId());
                        tv__bjtNumber.setText(assistiveCardDetail.getBjtNumber());
                    }else {
                        ToastUtil.showShortToast(getContext(),models.getStrError());
                    }

                }

            }
        });
    }
}
