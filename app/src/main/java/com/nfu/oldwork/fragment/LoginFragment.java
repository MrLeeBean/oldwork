package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nfu.oldwork.R;
import com.nfu.oldwork.adapter.LoginpageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/13.
 */

public class LoginFragment extends BaseFragment {
    @BindView(R.id.vp_homepage_show)
    ViewPager mViewPager;

    @BindView(R.id.tl_homepage_navigation)
    TabLayout mTabLayout;

    private List<Fragment> mFragments;
    private List<String>mTitles;
    private LoginpageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("HomeFragment", "HomeFragment **** onCreateView...");
        View rootView = inflater.inflate(R.layout.login_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        loadData();
        setData();
        return rootView;
    }

    private void setData() {
        mViewPager.setAdapter(mAdapter);
        //设置Viewpager和Tablayout进行联动
        mTabLayout.setupWithViewPager(mViewPager);
        //设置可以滑动
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//        //将标题设置可以左右摇动而不是移动
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
////        //设置预加载页数
//        mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void loadData() {
        //初始化导航标题,如果是title在json数据中,在初始化的时候可以使用异步任务加载的形式添加
        mTitles=new ArrayList<>();
        mTitles.add("普通登录");
        mTitles.add("快捷登录");
        //初始化Fragment
        mFragments=new ArrayList<>();
        for (int i = 0; i <mTitles.size() ; i++) {
            if(i==0){
                mFragments.add(new NormaLoginFragment());
            }else if(i==1){
                mFragments.add(new QuickLoginFragment());
            }
        }
        //getSupportFragmentManager()是Activity嵌套fragment时使用
        //getChildFragmentManager()是Fragment嵌套Fragment时使用
        mAdapter=new LoginpageAdapter(getChildFragmentManager(),mFragments,mTitles);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initView() {

    }
}
