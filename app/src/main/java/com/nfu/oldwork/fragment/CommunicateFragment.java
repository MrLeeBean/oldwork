package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nfu.oldwork.R;
import com.nfu.oldwork.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/11.
 */

public class CommunicateFragment extends BaseFragment{
    @BindView(R.id.iv_publish)
    ImageView iv_publish;
    @BindView(R.id.sp_conditon)
    Spinner sp_condition;
    @BindView(R.id.communicationList)
    XRecyclerView communicationlist;

    int index = 0;
    private int[] arrIds = new int[]{8007,8008,8009,8010,8011,8012};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "CommunicateFragment **** onCreateView...");
        bindView(inflater,R.layout.communicate_fragment,container);
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
    }

}
