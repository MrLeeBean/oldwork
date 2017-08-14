package com.nfu.oldwork.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;


import com.nfu.oldwork.R;
import com.nfu.oldwork.fragment.CommunicateFragment;
import com.nfu.oldwork.fragment.HomeFragment;
import com.nfu.oldwork.fragment.MineFragment;
import com.nfu.oldwork.fragment.StudyFragment;
import com.nfu.oldwork.utils.ImageUtils;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.view.ButtonExtendM;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends AppCompatActivity {

    Unbinder unbinder;

    @BindView(R.id.btn_home)
    ButtonExtendM btnHome;
    @BindView(R.id.btn_study)
    ButtonExtendM btnStudy;
    @BindView(R.id.btn_communicate)
    ButtonExtendM btnCommunicate;
    @BindView(R.id.btn_mine)
    ButtonExtendM btnMine;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.activity_main_content_frameLayout)
    FrameLayout mContentView;

    private static final int ACCESS_COARSE_LOCATION_REQUEST_CODE = 0x1001;
    private final int PERMS_REQUEST_CODE = 0x1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        if(savedInstanceState ==null){
            setHomeFragment();
        }
        initView();
        initData();
    }

    private void initData() {

    }


    private void setHomeFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);
        fragmentTransaction.commit();

    }

    boolean isHomeClick = false;
    boolean isServiceClick = false;
    boolean isConsultClick = false;

    private void initView() {
        btnHome.setNfuSeleted(true);

        btnHome.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelected();
                btnHome.setNfuSeleted(true);
                Fragment fragment = getVisibleFragment();
                if (fragment==null||!(fragment instanceof HomeFragment)){
                    setHomeFragment();
                }


            }
        });


        btnStudy.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelected();
                btnStudy.setNfuSeleted(true);
                Fragment fragment = getVisibleFragment();
                if (fragment==null||!(fragment instanceof StudyFragment)){
                    setStudyFragment();
                }
            }
        });

        btnCommunicate.setOnClickListener(new ButtonExtendM.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                clearSelected();
                btnCommunicate.setNfuSeleted(true);
                Fragment fragment = getVisibleFragment();
                if (fragment==null||!(fragment instanceof CommunicateFragment)){
                    setCommunicateFragment();
                }
            }
        });
        btnMine.setOnClickListener(new ButtonExtendM.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                clearSelected();
                btnMine.setNfuSeleted(true);
                Fragment fragment = getVisibleFragment();
                if (fragment==null||!(fragment instanceof MineFragment)){
                    setMineFragment();
                }
            }
        });
    }

    private Fragment getVisibleFragment(){
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment f:fragments){
            if (f!=null && f.isVisible()){
               return f;
            }
        }
        return null;
    }

    private void setCommunicateFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        CommunicateFragment communicateFragment = new CommunicateFragment();
        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, communicateFragment);
        fragmentTransaction.commit();
    }


    private void setStudyFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        StudyFragment fragment = new StudyFragment();
        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, fragment);
        fragmentTransaction.commit();
    }
  private void setMineFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
         MineFragment mineFragment = new MineFragment();
        fragmentTransaction.replace(R.id.activity_main_content_frameLayout, mineFragment);
        fragmentTransaction.commit();
    }


    private void clearSelected() {
        btnHome.setNfuSeleted(false);
        btnStudy.setNfuSeleted(false);
        btnCommunicate.setNfuSeleted(false);
        btnMine.setNfuSeleted(false);
//        isClick = false;
    }

    @Override
    protected void onDestroy() {
//        mLocationClient.unRegisterLocationListener(myListener);//取消注册的位置监听，以免内存泄露
//        mLocationClient.stop();// 退出时销毁定位
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 选择图片的返回码
     */
    public final static int SELECT_IMAGE_RESULT_CODE1 = 200;
    public final static int SELECT_IMAGE_RESULT_CODE2 = 300;
    public final static int SELECT_IMAGE_RESULT_CODE3 = 400;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i("MainActivity--->onActivityResult--->requestCode:"+requestCode+",resultCode:"+resultCode);
        String imagePath = "";
        if((requestCode == SELECT_IMAGE_RESULT_CODE1||requestCode == SELECT_IMAGE_RESULT_CODE2||
                requestCode == SELECT_IMAGE_RESULT_CODE3) && resultCode== RESULT_OK){
            if(data != null && data.getData() != null){//有数据返回直接使用返回的图片地址
                Uri uri = data.getData();
                if(uri.getScheme().equals("content")) {//判断uri地址是以什么开头的
                    imagePath = ImageUtils.getFilePathByFileUri(this, data.getData());
                }else{
                    imagePath = ImageUtils.getFilePathByFileUri(this, getFileUri(uri));
                }

            }

            if (mOnFragmentResult!=null){
                mOnFragmentResult.onResult(imagePath,requestCode);
            }

        }
    }

    /**
     * Fragment回调接口
     */
    public OnFragmentResult mOnFragmentResult;

    public void setOnFragmentResult(OnFragmentResult onFragmentResult){
        mOnFragmentResult = onFragmentResult;
    }

    /**
     * 回调数据给Fragment的接口
     */
    public interface OnFragmentResult{
        void onResult(String mImagePath,int requestCode);
    }

    public Uri getFileUri(Uri uri){
        if (uri.getScheme().equals("file")) {
            String path = uri.getEncodedPath();
            LogUtil.i("path1 is " + path);
            if (path != null) {
                path = Uri.decode(path);
                LogUtil.i("path2 is " + path);
                ContentResolver cr = this.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(")
                        .append(MediaStore.Images.ImageColumns.DATA)
                        .append("=")
                        .append("'" + path + "'")
                        .append(")");
                Cursor cur = cr.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur
                        .moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    //do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    LogUtil.i("uri_temp is " + uri_temp);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }

}
