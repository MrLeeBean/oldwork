package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nfu.oldwork.R;
import com.nfu.oldwork.adapter.CommunicationListAdapter;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.CommunicationInfo;
import com.nfu.oldwork.model.CommunicationList;
import com.nfu.oldwork.model.CommunicationModel;
import com.nfu.oldwork.model.NewsListModel;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.view.ButtonExtendM;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/11.
 */

public class CommQuestionFragment extends BaseFragment{

    @BindView(R.id.sp_conditon)
    Spinner sp_condition;
    @BindView(R.id.et_question)
    EditText ed_question;
    @BindView(R.id.btn_upload1)
    ButtonExtendM btn_upload1;
    @BindView(R.id.iv_upload_im1)
    ImageView iv_upload;
    @BindView(R.id.tv_cacel)
    TextView tv_cancel;
    @BindView(R.id.tv_release)
    TextView tv_release;


    int index = 0;
    private int[] arrIds = new int[]{8007,8008,8009,8010,8011,8012};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i("CommQuestionFragment **** onCreateView...");
        bindView(inflater,R.layout.communicate_question_fragment,container);
        initView();
        loadData();
        return rootView;
    }
    @Override
    protected void loadData() {
    }

    @Override
    protected void initView() {
        sp_condition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                LogUtil.i("CommunicateFragment--->setOnItemSelectedListener--->index"+index);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        btn_upload1.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private String[] informs = new String[]{"我要提问"};
    private void showInform(){
        ActionSheet.createBuilder(getContext(),getFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles(informs)
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        LogUtil.i("CommunicateFragment--->showInform--->index::"+index);

                    }
                }).show();
    }



}
