package com.nfu.oldwork.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bigkoo.pickerview.TimePickerView;
import com.nfu.oldwork.R;
import com.nfu.oldwork.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-7-27.
 */

public class QueryFealtyFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_cardnum)
    EditText et_cardnum;
    @BindView(R.id.et_seledt_year)
    EditText et_year;
    @BindView(R.id.btn_query )
    Button btn_query;
    int e_year = 2018;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater, R.layout.query_fealty_fragment, container);
        initView();
        loadData();
        return rootView;
    }

    @Override
    protected void loadData() {
        String year = DateUtil.getCurrentMsecString("yyyy");
        e_year = Integer.parseInt(year);
        et_year.setText(year);
    }

    @Override
    protected void initView() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        et_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBDateDialog();
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void showBDateDialog(){
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                et_year.setText(getTime(date));
            }
        }).setRange(e_year-90,e_year).setTitleText("年份").setType(new boolean[]{true,false,false,false,false,false})
                .build();

        //pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    private void gotoDetailFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(this);
        fragmentTransaction.add(R.id.activity_main_content_frameLayout , fragment);
        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);
        fragmentTransaction.commit();
    }


}
