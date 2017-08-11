package com.nfu.oldwork.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-7-25.
 */

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected Unbinder unbinder;

    protected abstract void loadData();

    protected abstract void initView();

    protected void bindView(LayoutInflater inflater, int id, @Nullable ViewGroup container){
        rootView = inflater.inflate(id, container, false);
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
