package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nfu.oldwork.R;

import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/13.
 */

public class QuickLoginFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "HomeFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.quicklogin_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);


        return rootView;
    }
    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }
}
