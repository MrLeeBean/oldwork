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
import com.nfu.oldwork.model.CommunicationInfo;
import com.nfu.oldwork.model.FilialStar;
import com.nfu.oldwork.utils.AppUtils;
import com.nfu.oldwork.utils.DensityUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017-7-26.
 */

public class FilialListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<FilialStar> newsModelList;
    private IOnDetailListener iOnDetailListener;

    private int ACTION_ITEM_TYPE = 1;
    private int HOUSE_ITEM_TYPE = 2;

    public FilialListAdapter(Context mContext, IOnDetailListener iOnDetailListener) {
        this.mContext = mContext;
        this.iOnDetailListener = iOnDetailListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ACTION_ITEM_TYPE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.nodata_view,parent,false);
            return new NodataViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.filialstar_item,parent,false);
            return new MyViewHolder(view);
        }

    }

    RequestOptions options = new RequestOptions().override(DensityUtil.dip2px(mContext,60),DensityUtil.dip2px(mContext,60)).error(R.drawable.tu)
            .centerCrop().placeholder(R.drawable.tu);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            FilialStar newsModel = newsModelList.get(position);
            MyViewHolder holder1 = (MyViewHolder) holder;

            holder1.tv_name.setText(newsModel.getOldName());
            holder1.tv__cardnum.setText(newsModel.getCertificatesNumber());
            holder1.tv_sex.setText(newsModel.getSex());
            holder1.tv_nation.setText(newsModel.getNation());
            holder1.tv__starnum.setText(newsModel.getStarNumber());
            holder1.tv__state.setText(newsModel.getWorkState());
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
        TextView tv_name,tv_sex,tv_nation,tv__cardnum,tv__starnum,tv__state;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_sex = (TextView) itemView.findViewById(R.id.tv_sex);
            tv_nation = (TextView) itemView.findViewById(R.id.tv_nation);
            tv__cardnum = (TextView) itemView.findViewById(R.id.tv__cardnum);
            tv__starnum = (TextView) itemView.findViewById(R.id.tv__starnum);
            tv__state = (TextView) itemView.findViewById(R.id.tv__state);

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

    public void setNewsData(List<FilialStar> newsData){
        newsModelList = newsData;
        notifyDataSetChanged();
    }

    public void addNewsData(List<FilialStar> newsData){
        if (newsModelList!=null){
            newsModelList.addAll(newsData);
        }else {
            newsModelList = newsData;
        }

        notifyDataSetChanged();
    }

    public interface IOnDetailListener{
        void onDetailListener(FilialStar model);
    }
}
