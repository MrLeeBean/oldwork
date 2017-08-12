package com.nfu.oldwork.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.nfu.oldwork.R;
import com.nfu.oldwork.model.NewsModel;
import com.nfu.oldwork.utils.DensityUtil;


import java.util.List;

/**
 * Created by Administrator on 2017-7-26.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<NewsModel> newsModelList;
    private int mIconType;
    private IOnDetailListener iOnDetailListener;
    CardView card_view;
    /*private final int WITH_PIC = 10;
    private final int NORMAL = 20;*/

    public NewsListAdapter(Context mContext, List<NewsModel> newsModelList, int iconType,IOnDetailListener iOnDetailListener) {
        this.mContext = mContext;
        this.mIconType =iconType;
        this.newsModelList = newsModelList;
        this.iOnDetailListener = iOnDetailListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item3,parent,false);

        return new MyViewHolder(view);
    }

    RequestOptions options = new RequestOptions().override(DensityUtil.dip2px(mContext,60),DensityUtil.dip2px(mContext,60))
            .centerCrop().placeholder(R.drawable.def_turn);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsModel newsModel = newsModelList.get(position);
        MyViewHolder holder1 = (MyViewHolder) holder;
     /*   String content = newsModel.getContent();
        content = content.replace("&nbsp;"," ");
        if (content.length()>40){
            content = content.substring(0,40);
        }


        content = content + "...";
        holder1.content.setText(content);*/

        String title = newsModel.getTitle();
        if (title.length()>12){
            title = title.substring(0,12) + "...";
        }

        holder1.title.setText(title);


    }

    @Override
    public int getItemCount() {
        if (newsModelList == null){
            return 0;
        }
        return newsModelList.size();

    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView arrow,iconType;
        TextView content;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            iconType = (ImageView) itemView.findViewById(R.id.icon_type);
            if(mIconType ==0){
                iconType.setBackgroundResource(R.drawable.news_icon1);
            }else if(mIconType ==1){
                iconType.setBackgroundResource(R.drawable.advice_icon);
            }else if(mIconType ==2){
                iconType.setBackgroundResource(R.drawable.policy_icon);
            }

            arrow = (ImageView) itemView.findViewById(R.id.right_arrow);
            card_view = (CardView) itemView.findViewById(R.id.card_view);

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iOnDetailListener!=null){
                        iOnDetailListener.onDetailListener(newsModelList.get(getAdapterPosition()-1));
                    }
                }
            });
        }
    }

    public void setNewsData(List<NewsModel> newsData){
        newsModelList = newsData;
        notifyDataSetChanged();
    }

    public void addNewsData(List<NewsModel> newsData){
        if (newsModelList!=null){
            newsModelList.addAll(newsData);
        }else {
            newsModelList = newsData;
        }

        notifyDataSetChanged();
    }

    public interface IOnDetailListener{
        void onDetailListener(NewsModel model);
    }
}
