package com.nfu.oldwork.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.nfu.oldwork.R;
import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.model.Transaction;
import com.nfu.oldwork.utils.DensityUtil;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;
import com.nfu.oldwork.view.NfuCustomDialog;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import okhttp3.Call;

import static com.nfu.oldwork.R.id.btn_querycard;
import static com.nfu.oldwork.R.id.tv_title;

/**
 * Created by Administrator on 2017-7-27.
 */

public class QueryFragment extends BaseFragment {
    @BindView(R.id.btn_queryfealty)
    Button btn_queryfealty;
    @BindView(R.id.btn_querycard)
    Button btn_querycard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.query_fragment, container);
        initView();
        loadData();
        return rootView;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        btn_queryfealty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NfuResource.isLoginSuccess){
                    QueryFealtyFragment queryFealtyFragment = new QueryFealtyFragment();
                    gotoDetailFragment(queryFealtyFragment);
                }else {
                    ToastUtil.showShortToast(getContext(),"您还未登录，不能进行查询！");
                }
            }
        });
        btn_querycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NfuResource.isLoginSuccess){
                    QueryAssistiveCardFragment assistiveCardFragment = new QueryAssistiveCardFragment();
                    gotoDetailFragment(assistiveCardFragment);
                }else {
                    ToastUtil.showShortToast(getContext(),"您还未登录，不能进行查询！");
                }
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


}
