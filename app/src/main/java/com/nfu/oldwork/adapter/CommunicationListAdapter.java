package com.nfu.oldwork.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfu.oldwork.R;
import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.model.CommunicationInfo;
import com.nfu.oldwork.model.NewsModel;
import com.nfu.oldwork.utils.AppUtils;
import com.nfu.oldwork.utils.DensityUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017-7-26.
 */

public class CommunicationListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CommunicationInfo> newsModelList;
    private IOnDetailListener iOnDetailListener;

    private int ACTION_ITEM_TYPE = 1;
    private int HOUSE_ITEM_TYPE = 2;

    public CommunicationListAdapter(Context mContext,IOnDetailListener iOnDetailListener) {
        this.mContext = mContext;
        this.iOnDetailListener = iOnDetailListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ACTION_ITEM_TYPE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.nodata_view,parent,false);

            return new NodataViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.communication_item,parent,false);

            return new MyViewHolder(view);
        }

    }

    RequestOptions options = new RequestOptions().override(DensityUtil.dip2px(mContext,60),DensityUtil.dip2px(mContext,60)).error(R.drawable.tu)
            .centerCrop().placeholder(R.drawable.tu);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            CommunicationInfo newsModel = newsModelList.get(position);
            MyViewHolder holder1 = (MyViewHolder) holder;
            if (!NfuResource.getInstance().isUseDefPic()){
                Glide.with(mContext).load(newsModel.getPicurl()).apply(options).into(holder1.iv_head);
            }else {
                Glide.with(mContext).load(R.drawable.tu).apply(options).into(holder1.iv_head);
            }
            holder1.title.setText(newsModel.getTitle());
            holder1.name.setText(newsModel.getReleasePeople());
            holder1.tv_eye.setText(AppUtils.str2Num(newsModel.getViewCount()));
            holder1.tv_reply.setText(AppUtils.str2Num(newsModel.getRespondCount()));
            holder1.tv_time.setText(newsModel.getCreatedate());
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
        if (newsModelList==null||newsModelList.size()<=0)
            return ACTION_ITEM_TYPE;
        return HOUSE_ITEM_TYPE;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,name,tv_eye,tv_reply,tv_time;
        CircleImageView iv_head;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_eye = (TextView) itemView.findViewById(R.id.tv_eye);
            tv_reply = (TextView) itemView.findViewById(R.id.tv_reply);
            iv_head = (CircleImageView) itemView.findViewById(R.id.iv_head);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);

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

    public void setNewsData(List<CommunicationInfo> newsData){
        newsModelList = newsData;
        notifyDataSetChanged();
    }

    public void addNewsData(List<CommunicationInfo> newsData){
        if (newsModelList!=null){
            newsModelList.addAll(newsData);
        }else {
            newsModelList = newsData;
        }

        notifyDataSetChanged();
    }

    public interface IOnDetailListener{
        void onDetailListener(CommunicationInfo model);
    }
}
