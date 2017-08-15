package com.nfu.oldwork.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfu.oldwork.R;
import com.nfu.oldwork.config.NfuResource;
import com.nfu.oldwork.model.CommDetail;
import com.nfu.oldwork.model.CommunicationInfo;
import com.nfu.oldwork.model.ReplyInfo;
import com.nfu.oldwork.utils.AppUtils;
import com.nfu.oldwork.utils.DensityUtil;
import com.nfu.oldwork.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017-7-26.
 */

public class CommunicationDetailListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private CommDetail commDetail;
    private IOnDetailListener iOnDetailListener;

    private int ITEM_TYPE1 = 1;
    private int ITEM_TYPE2 = 2;
    private int ITEM_TYPE3 = 3;

    public CommunicationDetailListAdapter(Context mContext, IOnDetailListener iOnDetailListener) {
        this.mContext = mContext;
        this.iOnDetailListener = iOnDetailListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE1){
            View view = LayoutInflater.from(mContext).inflate(R.layout.nodata_view,parent,false);
            return new NodataViewHolder(view);
        }/*else if (viewType == ITEM_TYPE2){
            View view = LayoutInflater.from(mContext).inflate(R.layout.comm_detail_title,parent,false);
            return new TitleViewHolder(view);
        }*/else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.comm_detail_item,parent,false);
            return new DetailViewHolder(view);
        }

    }

    RequestOptions options = new RequestOptions().override(DensityUtil.dip2px(mContext,60),DensityUtil.dip2px(mContext,60)).error(R.drawable.tu)
            .centerCrop().placeholder(R.drawable.tu);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LogUtil.i("onBindViewHolder--->position::"+position);
       /* if (holder instanceof TitleViewHolder){
            TitleViewHolder holder1 = (TitleViewHolder) holder;

            holder1.title.setText(commDetail.getTitle());
            holder1.tv_eye.setText(AppUtils.str2Num(commDetail.getViewCount()));
            holder1.tv_reply.setText(AppUtils.str2Num(commDetail.getRespondCount()));
        }else */if (holder instanceof DetailViewHolder){
            DetailViewHolder detailViewHolder = (DetailViewHolder) holder;
            ReplyInfo replyInfo = commDetail.getData().get(position);
            if (!NfuResource.getInstance().isUseDefPic()){
                Glide.with(mContext).load(replyInfo.getRespondPicurl()).apply(options).into(detailViewHolder.iv_head);
            }else {
                Glide.with(mContext).load(R.drawable.tu).apply(options).into(detailViewHolder.iv_head);
            }
            detailViewHolder.tv_content.setText(replyInfo.getRespondContent());
            detailViewHolder.tv_time.setText(replyInfo.getRespondDate());
            detailViewHolder.name.setText(replyInfo.getRespondPeople());
            if (position == 0){
                replyInfo.setSquence("楼主");
            }else if (position == 1){
                replyInfo.setSquence("沙发");
            }else if (position == 2){
                replyInfo.setSquence("板凳");
            }else {
                replyInfo.setSquence((position+1)+"楼");
            }
            detailViewHolder.tv_sequence.setText(replyInfo.getSquence());
        }

    }

    @Override
    public int getItemCount() {
        if (commDetail==null||commDetail.getData() == null ||commDetail.getData().size()<=0){
            return 1;
        }
        return commDetail.getData().size();

    }

    @Override
    public int getItemViewType(int position) {
        if (commDetail==null){
            return ITEM_TYPE1;
        }else{
           /* if (position == 0){
                return ITEM_TYPE2;
            }else {*/
                return ITEM_TYPE3;
           // }
        }
    }

    private class TitleViewHolder extends RecyclerView.ViewHolder{
        TextView title,tv_eye,tv_reply;
        CardView card_view;

        public TitleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_eye = (TextView) itemView.findViewById(R.id.tv_eye);
            tv_reply = (TextView) itemView.findViewById(R.id.tv_reply);

            card_view = (CardView) itemView.findViewById(R.id.card_view);


        }
    }

    private class DetailViewHolder extends RecyclerView.ViewHolder{
        TextView tv_sequence,name,tv_time,tv_content;
        CircleImageView iv_head;
        CardView card_view;

        public DetailViewHolder(View itemView) {
            super(itemView);
            tv_sequence = (TextView) itemView.findViewById(R.id.tv_sequence);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_head = (CircleImageView) itemView.findViewById(R.id.iv_head);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);

            card_view = (CardView) itemView.findViewById(R.id.card_view);


        }
    }

    private class NodataViewHolder extends RecyclerView.ViewHolder{


        public NodataViewHolder(View itemView) {
            super(itemView);

        }
    }

    public void setNewsData(CommDetail newsData){
        commDetail = newsData;
        if (newsData!=null){
            ReplyInfo replyInfo = new ReplyInfo();
            replyInfo.setRespondContent(newsData.getContent());
            replyInfo.setRespondDate(newsData.getCreatedate());
            replyInfo.setRespondPeople(newsData.getReleasePeople());
            replyInfo.setRespondPicurl(newsData.getPicurl());
            if (commDetail.getData() == null){
                ArrayList<ReplyInfo> list = new ArrayList<>();
                commDetail.setData(list);
            }
            commDetail.getData().add(0,replyInfo);
        }

        notifyDataSetChanged();
    }

    public void addNewsData(CommDetail commDetail){
        if (this.commDetail!=null&&this.commDetail.getData()!=null){
            if (commDetail!=null&&commDetail.getData()!=null){
                this.commDetail.getData().addAll(commDetail.getData());
            }
        }else {
            this.commDetail = commDetail;
        }

        notifyDataSetChanged();
    }

    public interface IOnDetailListener{
        void onDetailListener(ReplyInfo model);
    }
}
