package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.nfu.oldwork.BuildConfig;
import com.nfu.oldwork.R;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/2.
 */

public class UpdateFragment extends BaseFragment{
    @BindView(R.id.btn_back)
    ButtonExtendM btnBack;
    @BindView(R.id.top_title)
    TextView tv_title;
    @BindView(R.id.current_version)
    TextView current_version;
    @BindView(R.id.release_date)
    TextView release_date;
    @BindView(R.id.check_update_btn)
    Button check_update_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.update_fragment,container);
        initView();
        loadData();
        return rootView;
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        tv_title.setText(R.string.update_str);
        current_version.setText("当前版本：V"+getASVersionName());
//        release_date.setText("发布日期："+getASVersionName());

        btnBack.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        check_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShortToast(getActivity(),"当前已为最新版本！");
            }
        });
    }

    public String getASVersionName(){
//        int versionCode = BuildConfig.VERSION_CODE;
       return BuildConfig.VERSION_NAME;
    }
}
