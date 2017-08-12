package com.nfu.oldwork.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wpp.
 *
 * @description 搜索页的ViewPager 适配器
 * @date 2017/4/5
 */
public class HomeNewsListViewPagerAdapter extends PagerAdapter {
    private List<View> mData;
    private String[] mTitles = null;
    public HomeNewsListViewPagerAdapter(List<View> data, String[] titles){
        this.mData = data;
        this.mTitles = titles;
    }
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mData.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mData.get(position));
        return mData.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
