package com.nfu.oldwork.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import com.nfu.oldwork.R;
import com.nfu.oldwork.activity.HomeActivity;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.Feedback;
import com.nfu.oldwork.utils.BitmapAndStringUtils;
import com.nfu.oldwork.utils.DensityUtil;
import com.nfu.oldwork.utils.ImageUtils;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;
import com.nfu.oldwork.view.NfuCustomDialog;
import com.nfu.oldwork.view.SelectPicPopupWindow;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/25.
 * 咨询页面
 */

public class SafeguardDetailFragment extends BaseFragment{
    @BindView(R.id.btn_back)
    ButtonExtendM btnBack;
    @BindView(R.id.top_title)
    TextView tv_title;
    @BindView(R.id.et_question)
    EditText ed_question;
    @BindView(R.id.constacts_msg_name_et)
    EditText ed_name;
    @BindView(R.id.constacts_msg_number_et)
    EditText ed_phone;
    @BindView(R.id.btn_submit)
    Button btn_submit;

    private int type = 1;

    @BindView(R.id.btn_upload1)
    ButtonExtendM btnUpload1;
    @BindView(R.id.btn_upload2)
    ButtonExtendM btnUpload2;
    @BindView(R.id.btn_upload3)
    ButtonExtendM btnUpload3;
    @BindView(R.id.iv_upload_im1)
    ImageView imgUpload1;
    @BindView(R.id.iv_upload_im2)
    ImageView imgUpload2;
    @BindView(R.id.iv_upload_im3)
    ImageView imgUpload3;

    private SelectPicPopupWindow menuWindow;

    /**
     * 选择图片的返回码
     */
    private final static int SELECT_IMAGE_RESULT_CODE1 = 200;
    private final static int SELECT_IMAGE_RESULT_CODE2 = 300;
    private final static int SELECT_IMAGE_RESULT_CODE3 = 400;
    private int curretnRequestCode = SELECT_IMAGE_RESULT_CODE1;
    /**
     * 当前选择的图片的路径
     */
    public String imagePath;
    private Bitmap path1 = null;
    private Bitmap path2 = null;
    private Bitmap path3 = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView(inflater,R.layout.safeguard_detail_fragment,container);
        initView();
        loadData();
        return rootView;
    }




    @Override
    protected void loadData() {
        Bundle bundle = getArguments();

//        type = bundle.getInt("type");

         /*   ed_question.setHint(R.string.right_str1);

            ed_question.setHint(R.string.right_str2);
        */

        btnUpload1.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                curretnRequestCode = SELECT_IMAGE_RESULT_CODE1;
                showPicturePopupWindow();
            }
        });

        btnUpload2.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                curretnRequestCode = SELECT_IMAGE_RESULT_CODE2;
                showPicturePopupWindow();
            }
        });

        btnUpload3.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                curretnRequestCode = SELECT_IMAGE_RESULT_CODE3;
                showPicturePopupWindow();
            }
        });
    }

    @Override
    protected void initView() {
        ((HomeActivity)getActivity()).setOnFragmentResult(onFragmentResult);
        tv_title.setText(R.string.home_fragment_maintain_str);
        btnBack.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ed_name.getText())||TextUtils.isEmpty(ed_phone.getText())||TextUtils.isEmpty(ed_question.getText())){
                    ToastUtil.showShortToast(getContext(), R.string.feedback_str);
                }else {
                    btn_submit.setClickable(false);
                    btn_submit.setText("提交中...");
                    Feedback feedback = new Feedback();
                    feedback.setSignKey(ApiConfig.signKey);
                    feedback.setContacterMobile(ed_phone.getText().toString());
                    feedback.setContacterName(ed_name.getText().toString());
                    feedback.setOpinionType(type);
                    feedback.setFeedbackContent(ed_question.getText().toString());
                    feedback.setImgStrFirst(BitmapAndStringUtils.convertIconToString(path1));
                    feedback.setImgStrSecond(BitmapAndStringUtils.convertIconToString(path2));
                    feedback.setImgStrThird(BitmapAndStringUtils.convertIconToString(path3));
                    String str = new Gson().toJson(feedback);
                    ApiManager.getInstance().postOpinionFeedBack(str, new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.i("SafeguardDetailFragment--->postOpinionFeedBack--->onError--->"+e);
                            ToastUtil.showShortToast(getContext(),R.string.feedback_str_error);
                            btn_submit.setText("提交失败");
                            btn_submit.setClickable(true);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.i("SafeguardDetailFragment--->postOpinionFeedBack--->onResponse--->"+response);
                            //ToastUtil.showShortToast(getContext(),R.string.feedback_str_ok);
                            showTipDialog();
                            btn_submit.setClickable(true);
                            btn_submit.setText("提交");
                        }
                    });
                }

            }
        });
    }

    private void showTipDialog(){
        NfuCustomDialog.Builder builder = new NfuCustomDialog.Builder(getContext());
        builder.setMessage(getString(R.string.submit_ok));
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        // 获得代表当前window属性的对象
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = DensityUtil.dip2px(getContext(),276.8f);
        params.height = DensityUtil.dip2px(getContext(),160.5f);
        // 方式一：设置属性
        window.setAttributes(params);
        dialog.show();
    }


    /**
     * 拍照或从图库选择图片(PopupWindow形式)
     */
    private void showPicturePopupWindow(){
        if (menuWindow==null){
            menuWindow = new SelectPicPopupWindow(getContext(), new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 隐藏弹出窗口
                    menuWindow.dismiss();
                    switch (v.getId()) {
                        case R.id.takePhotoBtn:// 拍照
                            //takePhoto();
                            ((HomeActivity)getActivity()).autoObtainCameraPermission(curretnRequestCode);
                            break;
                        case R.id.pickPhotoBtn:// 相册选择图片
                            //pickPhoto();
                            ((HomeActivity)getActivity()).autoObtainStoragePermission(curretnRequestCode);
                            break;
                        case R.id.cancelBtn:// 取消
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        menuWindow.showAtLocation(rootView, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            /**
             * 通过指定图片存储路径，解决部分机型onActivityResult回调 data返回为null的情况
             */
            //获取与应用相关联的路径
            String imageFilePath = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
            //根据当前时间生成图片的名称
            String timestamp = "/"+formatter.format(new Date())+".jpg";
            File imageFile = new File(imageFilePath,timestamp);// 通过路径创建保存文件
            imagePath = imageFile.getAbsolutePath();
            Uri imageFileUri = Uri.fromFile(imageFile);// 获取文件的Uri
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageFileUri);// 告诉相机拍摄完毕输出图片到指定的Uri
            getActivity().startActivityForResult(intent, curretnRequestCode);
        } else {
            Toast.makeText(getContext(), "内存卡不存在！", Toast.LENGTH_LONG).show();
        }
    }


    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        //intent.setType("image/*");
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        getActivity().startActivityForResult(intent, curretnRequestCode);
    }

    private HomeActivity.OnFragmentResult onFragmentResult = new HomeActivity.OnFragmentResult() {
        @Override
        public void onResult(Bitmap bitmap, int requestCode) {
           /* if (!TextUtils.isEmpty(mImagePath)){
                imagePath = mImagePath;
            }*/
            //获取图片缩略图，避免OOM
            //Bitmap bitmap = ImageUtils.getImageThumbnail(imagePath, ImageUtils.getWidth(getContext()) / 3 - 5, ImageUtils.getWidth(getContext()) / 3 - 5);
            switch (requestCode){
                case SELECT_IMAGE_RESULT_CODE1:
                    imgUpload1.setImageBitmap(bitmap);
                    path1 = bitmap;
                    break;
                case SELECT_IMAGE_RESULT_CODE2:
                    imgUpload2.setImageBitmap(bitmap);
                    path2 = bitmap;
                    break;
                case SELECT_IMAGE_RESULT_CODE3:
                    imgUpload3.setImageBitmap(bitmap);
                    path3 = bitmap;
                    break;
            }
        }

    };

    private String getImageCode(String path){
        String code = "";
        if (!TextUtils.isEmpty(path)){
            byte[] data = null;
            try {
                InputStream in = new FileInputStream(path);
                data = new byte[in.available()];
                in.read(data);
                in.close();
                code = Base64.encodeToString(data,Base64.DEFAULT);
            }catch (Exception e){
                LogUtil.i("SafeguardDetailFragment--->getImageCode--->"+e);
            }
        }

        return code;
    }
}
