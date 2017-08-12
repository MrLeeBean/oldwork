package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


import com.nfu.oldwork.R;
import com.nfu.oldwork.model.NewsModel;
import com.nfu.oldwork.view.ButtonExtendM;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/25.
 * 咨询页面
 */

public class NewsDetailFragment extends BaseFragment {
    @BindView(R.id.btn_back)
    ButtonExtendM btnBack;
    @BindView(R.id.top_title)
    TextView top_title;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_creatdate)
    TextView tv_creatdate;
    @BindView(R.id.wv_news_content)
    WebView wv_content;

    private NewsModel newsModel = null;
    private String title = "媒体报道";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater,R.layout.news_detail_fragment,container);
        initView();
        loadData();
        return rootView;
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            newsModel = (NewsModel) bundle.getSerializable("news");
            title = bundle.getString("title");
        }

        top_title.setText(title);
        if (newsModel!=null){
            tv_title.setText(newsModel.getTitle());
            tv_title.setMovementMethod(ScrollingMovementMethod.getInstance());
            tv_creatdate.setText(newsModel.getCreatedate());
            wv_content.loadDataWithBaseURL(null,newsModel.getContent(),"text/html","utf-8",null);
        }
    }

    @Override
    protected void initView() {
        wv_content.getSettings().setJavaScriptEnabled(true);
        wv_content.getSettings().setUseWideViewPort(true);
        wv_content.getSettings().setLoadWithOverviewMode(true);
        wv_content.getSettings().setBuiltInZoomControls(true);
       // wv_content.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_content.getSettings().setSupportZoom(true);
        btnBack.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }


}
