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
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

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
    @BindView(R.id.commit_cardview)
    CardView commit_cardview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "HomeFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.nornallogin_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }
    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        tv_title.setText(R.string.bottom_bar_login_str);

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
                checkLoginResult();
            }
        });
    }

    private void checkLoginResult() {
        String name = normal_name_et.getText().toString().trim();
        String password = normal_pwd_et.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setSignKey(ApiConfig.signKey);
            userInfo.setLoginid(name);
            userInfo.setPassword(password);
            String str = new Gson().toJson(userInfo);
            ApiManager.getInstance().postCheckLogin(str, new StringCallback() {
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
            ToastUtil.showShortToast(getActivity(),"请输入用户名或密码");
        }
    }
}
