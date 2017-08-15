package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.nfu.oldwork.R;
import com.nfu.oldwork.model.LocalInfo;
import com.nfu.oldwork.model.UserInfo;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nfu.oldwork.R.id.normal_name_et;
import static com.nfu.oldwork.R.id.normal_pwd_et;
import static com.nfu.oldwork.utils.SharedPreferencesManager.saveLocalInfo;

/**
 * Created by Administrator on 2017/8/11.
 */

public class PersonalInfoFragment extends BaseFragment {
    @BindView(R.id.btn_back)
    ButtonExtendM btnBack;
    @BindView(R.id.top_title)
    TextView tv_title;
    @BindView(R.id.name_et)
    EditText name_et;
    @BindView(R.id.workaddr_et)
    EditText workaddr_et;
    @BindView(R.id.worktype_et)
    EditText worktype_et;
    @BindView(R.id.identity_card_et)
    EditText identity_card_et;
    @BindView(R.id.spinner2)
    Spinner sexSpinner;
    @BindView(R.id.commit_cardview)
    CardView commit_cardview;
    int position = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("PersonalInfoFragment", "PersonalInfoFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.personal_info_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        loadData();
        initView();
        return rootView;
    }
    LocalInfo local;
    String sex = "";
    @Override
    protected void loadData() {

         local = SharedPreferencesManager.getLocalInfo("localinfo", "LocalInfo", "");

        LogUtil.i("NormaLoginFragment--->postCheckLogin--->getuserInfoFromSp-->localinfo ::" + local.toString());
        name_et.setText(local.getName());
        workaddr_et.setText(local.getWorkAddr());
        worktype_et.setText(local.getWorkType());
        identity_card_et.setText(local.getIdCard());
        String gender = local.getGender();

        if("男".equals(gender)){
            position = 0;
        }else {
            position=1;
        }
        sexSpinner.setSelection(position,true);
    }

    @Override
    protected void initView() {

        tv_title.setText(R.string.top_bar_person_str);

        btnBack.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                local = null;
                getFragmentManager().popBackStack();

            }
        });
        commit_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePersonInfoToLocal();
            }
        });
        sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                 sex = (String) sexSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    LocalInfo localInfo;
    private void savePersonInfoToLocal() {
         localInfo = new LocalInfo();

        localInfo.name = name_et.getText().toString().trim();
        localInfo.workAddr = workaddr_et.getText().toString().trim();
        localInfo.workType = worktype_et.getText().toString().trim();
        localInfo.idCard = identity_card_et.getText().toString().trim();
//        localInfo.gender = "男";
        localInfo.gender = sex;
        saveLocalInfo("localinfo","LocalInfo",localInfo);
        LogUtil.i("NormaLoginFragment--->postCheckLogin--->savePersonInfoToLocal-->localInfo ::" + localInfo.toString());
        ToastUtil.showShortToast(getActivity(),"保存成功");
    }

    private void gotoFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(this);
        fragmentTransaction.add(R.id.activity_main_content_frameLayout, fragment);
//        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
