package com.nfu.oldwork.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by wpp.
 *
 * @description 热门页顶部推荐viewpager适配器
 * @date 2017/3/30
 */
public class HotAdPagerAdapter extends PagerAdapter {
    private List<ImageView> mData;
    private AdItemOnClickListener itemOnClickListener;
    public HotAdPagerAdapter(AdItemOnClickListener listener){
        this.itemOnClickListener = listener;
    }

    public void setData(List<ImageView> data){
        this.mData = data;
    }
    @Override
    public int getCount() {
        if(mData == null){
            return 0;
        }
        if(mData.size() == 1){
            return 1;
        }
        return mData.size() * 5000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mData.get(position));
    }

    public int getItemCount(){
        if(mData != null) {
            return mData.size();
        } else {
            return 0;
        }

    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= mData.size();
        if (position<0){
            position = mData.size()+position;
        }
        ImageView view = mData.get(position);
        ViewParent vp = view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }
        final int finalPosition = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemOnClickListener != null){
                    itemOnClickListener.viewPagerItemOnClickListener(finalPosition);
                }
            }
        });
        container.addView(view);
        return view;
    }

    public interface AdItemOnClickListener{
        void viewPagerItemOnClickListener(int position);
    }
}
