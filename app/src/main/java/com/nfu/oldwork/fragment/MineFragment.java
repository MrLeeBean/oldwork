package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nfu.oldwork.R;
import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.model.NewsModel;
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/11.
 */

public class MineFragment extends BaseFragment{
    @BindView(R.id.card_view2)
    CardView card_view2;
    @BindView(R.id.card_view3)
    CardView card_view3;
    @BindView(R.id.card_view4)
    CardView card_view4;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.name_tv)
    TextView name_tv;
    boolean isLoginSuccess = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "HomeFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        loadData();
        initView();

        return rootView;
    }

    @Override
    protected void loadData() {

       /* Bundle bundle = getArguments();
        if(bundle != null){
            isLoginSuccess = bundle.getBoolean("isLoginSuccess");

        }*/


     /*   UserInfo userInfo = SharedPreferencesManager.getUser("userinfo", "UserInfo", "");
        if(userInfo!=null){
            isLoginSuccess =true;
            loginTv.setClickable(false);
        }*/
    }

    @Override
    protected void initView() {
        if(NfuResource.isLoginSuccess){
            card_view4.setVisibility(View.VISIBLE);
            card_view4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonalInfoFragment personalInfoFragment = new PersonalInfoFragment();
                    gotoFragment(personalInfoFragment);
                }
            });
            loginTv.setVisibility(View.GONE);
            name_tv.setText(SharedPreferencesManager.getUser("userinfo","UserInfo","").getUserName());

        }
        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingFragment settingFragment = new SettingFragment();
                gotoFragment(settingFragment);
            }
        });
        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateFragment updateFragment = new UpdateFragment();
                gotoFragment(updateFragment);
            }
        });


        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormaLoginFragment loginFragment = new NormaLoginFragment();
                gotoFragment(loginFragment);
            }
        });

    }
    private void gotoFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(this);
        fragmentTransaction.add(R.id.activity_main_content_frameLayout , fragment);
//        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
