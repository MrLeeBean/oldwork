package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.nfu.oldwork.R;
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.nfu.oldwork.view.ButtonExtendM;

import butterknife.BindView;

import static com.nfu.oldwork.utils.SharedPreferencesManager.getUser;


/**
 * Created by user on 2017/7/29.
 */

public class UserInfolFragment extends BaseFragment {
    @BindView(R.id.btn_back)
    ButtonExtendM btnBack;
    @BindView(R.id.top_title)
    TextView tv_title;

    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.cityName)
    TextView cityName;
    @BindView(R.id.countryName)
    TextView countryName;
    @BindView(R.id.streetName)
    TextView streetName;
    @BindView(R.id.communityName)
    TextView communityName;
    @BindView(R.id.deptName)
    TextView deptName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.userinfo_fragment, container);
        initView();
        loadData();
        return rootView;
    }



    @Override
    protected void loadData() {
        UserInfo userInfo = SharedPreferencesManager.getUser("userinfo","UserInfo","");

        LogUtil.d("ServiceDetailFragment ====>  detailbean =" +userInfo.toString());
        tv_title.setText("个人详情");

        userName.setText(userInfo.getUserName());
        cityName.setText(userInfo.getCityName());
        countryName.setText(userInfo.getCountryName());
        streetName.setText(userInfo.getStreetName());
        communityName.setText(userInfo.getCommunityName());
        deptName.setText(userInfo.getDeptName());
    }

    @Override
    protected void initView() {
        btnBack.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
}
