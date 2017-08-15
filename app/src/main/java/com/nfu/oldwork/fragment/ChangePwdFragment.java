package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.UpdatePassword;
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.nfu.oldwork.utils.StringUtil;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by user on 2017/8/13.
 */

public class ChangePwdFragment extends BaseFragment {
    @BindView(R.id.reset_pwd_et)
    EditText reset_pwd_et;
    @BindView(R.id.confirm_pwd_et)
    EditText confirm_pwd_et;
    @BindView(R.id.btn_back)
    ButtonExtendM btnBack;
    @BindView(R.id.top_title)
    TextView tv_title;
    @BindView(R.id.commit_cardview)
    CardView commit_cardview;
    UserInfo userInfo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "HomeFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.changepwd_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        loadData();
        initView();
        return rootView;
    }
    @Override
    protected void loadData() {
         userInfo = SharedPreferencesManager.getUser("userinfo", "UserInfo", "");

    }

    @Override
    protected void initView() {
        tv_title.setText(R.string.top_bar_changepwd_str);

        btnBack.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        commit_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //TODO 登录结果
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String newpwd = reset_pwd_et.getText().toString().trim();
        String  confirmpwd= confirm_pwd_et.getText().toString().trim();
        if(!TextUtils.isEmpty(newpwd)&&!TextUtils.isEmpty(confirmpwd)) {
            if(StringUtil.isPwdLengthValid(newpwd)&&StringUtil.isPwdLengthValid(confirmpwd)){
                if(newpwd.equals(confirmpwd)){
                    UpdatePassword updatePassword = new UpdatePassword();

                    updatePassword.setSignKey(ApiConfig.signKey);
                    //TODO sp从获取old pwd name
                    updatePassword.setLoginid( userInfo.getLoginId());
                    updatePassword.setOldPassword(userInfo.getLoginInfo().getPassword());
                    updatePassword.setNewPassword(confirmpwd);
                    String str = new Gson().toJson(updatePassword);
                    ApiManager.getInstance().postUpdatePwd(str, new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.i("CommQuestionFragment--->postOpinionFeedBack--->onError--->" + e);
                            ToastUtil.showShortToast(getContext(), R.string.question_str_error);

                        }
                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.i("CommQuestionFragment--->postOpinionFeedBack--->onResponse--->" + response);
                            //ToastUtil.showShortToast(getContext(),R.string.feedback_str_ok);
//                    tv_release.setClickable(true);
//                    tv_release.setText("发布");
                            //TODO 处理返回结果  保存信息到sp中跳转到 头像页面
                            getFragmentManager().popBackStack();
                        }
                    });
                }else {
                    ToastUtil.showShortToast(getActivity(),"输入的密码不一致！");
                }

            }else {
                ToastUtil.showShortToast(getActivity(),"密码长度为6-18位！");
            }


        }else {
            ToastUtil.showShortToast(getActivity(),"请输入用户名或密码");
        }
    }
}
