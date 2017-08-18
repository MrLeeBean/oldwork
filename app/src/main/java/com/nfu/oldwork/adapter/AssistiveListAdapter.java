package com.nfu.oldwork.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.nfu.oldwork.R;
import com.nfu.oldwork.model.AssistiveCard;
import com.nfu.oldwork.model.FilialStar;
import com.nfu.oldwork.utils.DensityUtil;

import java.util.List;

/**
 * Created by Administrator on 2017-7-26.
 */

public class AssistiveListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<AssistiveCard> newsModelList;
    private IOnDetailListener iOnDetailListener;

    private int ACTION_NONE_TYPE = 0;
    private int ACTION_ITEM_TYPE = 1;
    private int HOUSE_ITEM_TYPE = 2;

    public AssistiveListAdapter(Context mContext, IOnDetailListener iOnDetailListener) {
        this.mContext = mContext;
        this.iOnDetailListener = iOnDetailListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ACTION_NONE_TYPE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.nonedata_view,parent,false);
            return new NonedataViewHolder(view);
        }else if (viewType == ACTION_ITEM_TYPE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.nodata_view,parent,false);
            return new NodataViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.assistive_item,parent,false);
            return new MyViewHolder(view);
        }

    }

    RequestOptions options = new RequestOptions().override(DensityUtil.dip2px(mContext,60),DensityUtil.dip2px(mContext,60)).error(R.drawable.tu)
            .centerCrop().placeholder(R.drawable.tu);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            AssistiveCard newsModel = newsModelList.get(position);
            MyViewHolder holder1 = (MyViewHolder) holder;

            holder1.tv_name.setText(newsModel.getName());
            holder1.tv__cardnum.setText(newsModel.getCertificatesNumber());
            holder1.tv_sex.setText(newsModel.getSex());
            holder1.tv__makeCardSuccess.setText(newsModel.getMakeCardSuccess());
            holder1.tv__householdCounty.setText(newsModel.getHouseholdCounty());
        }

    }

    @Override
    public int getItemCount() {
        if (newsModelList == null||newsModelList.size()<=0){
            return 1;
        }
        return newsModelList.size();

    }

    @Override
    public int getItemViewType(int position) {
        if (newsModelList==null)
            return ACTION_NONE_TYPE;
        else if (newsModelList.size()<=0)
            return ACTION_ITEM_TYPE;
        else
            return HOUSE_ITEM_TYPE;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_sex,tv__cardnum,tv__makeCardSuccess,tv__householdCounty;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_sex = (TextView) itemView.findViewById(R.id.tv_sex);
            tv__cardnum = (TextView) itemView.findViewById(R.id.tv__cardnum);
            tv__makeCardSuccess = (TextView) itemView.findViewById(R.id.tv__makeCardSuccess);
            tv__householdCounty = (TextView) itemView.findViewById(R.id.tv__householdCounty);

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

    private class NodataViewHolder extends RecyclerView.ViewHolder{


        public NodataViewHolder(View itemView) {
            super(itemView);

        }
    }

    private class NonedataViewHolder extends RecyclerView.ViewHolder{


        public NonedataViewHolder(View itemView) {
            super(itemView);

        }
    }

    public void setNewsData(List<AssistiveCard> newsData){
        newsModelList = newsData;
        notifyDataSetChanged();
    }

    public void addNewsData(List<AssistiveCard> newsData){
        if (newsModelList!=null){
            newsModelList.addAll(newsData);
        }else {
            newsModelList = newsData;
        }

        notifyDataSetChanged();
    }

    public interface IOnDetailListener{
        void onDetailListener(AssistiveCard model);
    }
}
