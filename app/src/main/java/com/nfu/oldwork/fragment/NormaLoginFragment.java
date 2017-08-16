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
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nfu.oldwork.R;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.LoginInfo;
import com.nfu.oldwork.model.TurnPicModel;
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.nfu.oldwork.R.id.commit_cardview;

/**
 * Created by user on 2017/8/13.
 */

public class NormaLoginFragment extends BaseFragment {
    @BindView(R.id.normal_name_et)
    EditText normal_name_et;
    @BindView(R.id.normal_pwd_et)
    EditText normal_pwd_et;
    @BindView(R.id.btn_back)
    ButtonExtendM btnBack;
    @BindView(R.id.top_title)
    TextView tv_title;
    @BindView(commit_cardview)
    CardView commitCardview;
    boolean isLoginSuccess =false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "HomeFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.nornallogin_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }
    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        tv_title.setText(R.string.top_bar_login_str);

        btnBack.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        commitCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLoginResult();
            }
        });
    }
    //LoginInfo loginInfo;
    private void checkLoginResult() {
        String name = normal_name_et.getText().toString().trim();
        String password = normal_pwd_et.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)) {
            final LoginInfo  loginInfo = new LoginInfo();
            loginInfo.setSignKey(ApiConfig.signKey);
            loginInfo.setLoginid(name);
            loginInfo.setPassword(password);
            String str = new Gson().toJson(loginInfo);
            ApiManager.getInstance().postCheckLogin(str, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    LogUtil.i("NormaLoginFragment--->postCheckLogin--->onError--->" + e);
                    ToastUtil.showShortToast(getContext(), R.string.login_str_error);

                }

                @Override
                public void onResponse(String response, int id) {
                    LogUtil.i("NormaLoginFragment--->postCheckLogin--->onResponse--->" + response);
                    //ToastUtil.showShortToast(getContext(),R.string.feedback_str_ok);
//                    tv_release.setClickable(true);
//                    tv_release.setText("发布");

                    //800=登录成功|100=登录失败，没有该用户|101=参数错误|102=系统异常|103=登录失败，密码错误"
                    UserInfo userInfo = new Gson().fromJson(response, UserInfo.class);
                    userInfo.setLoginInfo(loginInfo);
                    String iResult = userInfo.getIResult();
                      LogUtil.i("NormaLoginFragment--->postCheckLogin--->onResponse--->userInfo ::" + userInfo.toString());
                      LogUtil.i("NormaLoginFragment--->postCheckLogin--->onResponse--->iResult ::" + iResult);
         //           SharedPreferencesManager.putUser("userinfo","UserInfo",userInfo);
                    if(iResult.equals("800")){
                        //=登录成功
                        //TODO 处理返回结果  保存信息到sp中跳转到 头像页面
                        NfuResource.isLoginSuccess = true;
                        ToastUtil.showShortToast(getActivity(),"登录成功");
                        userInfo.setLoginSuccess(true);
                        SharedPreferencesManager.putUser("userinfo","UserInfo",userInfo);
                       // UserInfo user = SharedPreferencesManager.getUser("userinfo", "UserInfo", "");
                        //LogUtil.i("NormaLoginFragment--->postCheckLogin--->getuserInfoFromSp-->userInfo ::" + userInfo.toString());
//                        SharedPreferencesManager.putUser("logininfo","LoginInfo",loginInfo);
                        //    getFragmentManager().popBackStack();
                        MineFragment mineFragment = new MineFragment();

                     /*   Bundle bundle = new Bundle();
                         isLoginSuccess = true;
                        bundle.putBoolean("isLoginSuccess",isLoginSuccess);
                        mineFragment.setArguments(bundle);*/
                        gotoFragment(mineFragment);

                    }else if (iResult .equals( "100")) {
                        ToastUtil.showShortToast(getActivity(),"登录失败，没有该用户");
                    }else if(iResult .equals( "103")){
                        ToastUtil.showShortToast(getActivity(),"登录失败，密码错误");
                    }
                }
            });

        }else {
            ToastUtil.showShortToast(getActivity(),"请输入用户名或密码");
        }
    }
    private void gotoFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.hide(this);
//        fragmentTransaction.add(R.id.activity_main_content_frameLayout , fragment);
        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
