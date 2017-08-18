package com.nfu.oldwork.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.google.gson.Gson;
import com.nfu.oldwork.R;
import com.nfu.oldwork.activity.HomeActivity;
import com.nfu.oldwork.config.ApiConfig;
import com.nfu.oldwork.manager.ApiManager;
import com.nfu.oldwork.model.ReplyModel;
import com.nfu.oldwork.utils.BitmapAndStringUtils;
import com.nfu.oldwork.utils.ImageUtils;
import com.nfu.oldwork.utils.LogUtil;
import com.nfu.oldwork.utils.SharedPreferencesManager;
import com.nfu.oldwork.utils.ToastUtil;
import com.nfu.oldwork.view.ButtonExtendM;
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
 * Created by Administrator on 2017/8/11.
 */

public class CommReplyFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_question)
    EditText ed_question;
    @BindView(R.id.btn_upload1)
    ButtonExtendM btn_upload1;
    @BindView(R.id.iv_upload_im1)
    ImageView iv_upload;
    @BindView(R.id.tv_cacel)
    TextView tv_cancel;
    @BindView(R.id.tv_release)
    TextView tv_release;
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
    private String path1 = null;


    int index = 0;
    private int[] arrIds = null;
    private String[] titles = null;
    private String id = null;
    private String title = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i("CommQuestionFragment **** onCreateView...");
        bindView(inflater, R.layout.communicate_reply_fragment, container);
        initView();
        loadData();
        return rootView;
    }

    @Override
    protected void loadData() {
        Bundle bundle = getArguments();
        id = bundle.getString("id");
        title = bundle.getString("title");
        tv_title.setText(title);
        arrIds = getResources().getIntArray(R.array.comtype_arr_id);
        titles = getResources().getStringArray(R.array.comtype_arr);
    }

    @Override
    protected void initView() {
        ((HomeActivity) getActivity()).setOnFragmentResult(onFragmentResult);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        btn_upload1.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInform();
            }
        });

        tv_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(ed_question.getText())) {
                    ToastUtil.showShortToast(getContext(), R.string.feedback_str);
                } else {
                    tv_release.setClickable(false);
                    tv_release.setText("发布中...");
                    ReplyModel replyModel = new ReplyModel();
                    replyModel.setSignKey(ApiConfig.signKey);
                    replyModel.setId(id);
                    replyModel.setContent(ed_question.getText().toString());
                    replyModel.setRespondPeople(SharedPreferencesManager.getUser("userinfo","UserInfo",null).getUserName());
                    replyModel.setRespondPeopleId(SharedPreferencesManager.getUser("userinfo","UserInfo",null).getUserId());
                    replyModel.setStrBase64(BitmapAndStringUtils.convertIconToString(path1));
                    String str = new Gson().toJson(replyModel);
                    ApiManager.getInstance().postCommResponse(str, new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.i("CommReplyFragment--->postCommResponse--->onError--->" + e);
                            ToastUtil.showShortToast(getContext(), R.string.question_str_error);
                            tv_release.setText("发布");
                            tv_release.setClickable(true);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.i("CommReplyFragment--->postCommResponse--->onResponse--->" + response);
                            //ToastUtil.showShortToast(getContext(),R.string.feedback_str_ok);
                            tv_release.setClickable(true);
                            tv_release.setText("发布");
                            getFragmentManager().popBackStack();
                        }
                    });
                }
            }
        });

    }

    private String[] informs = new String[]{"拍照", "从相册选择"};

    private void showInform() {
        ActionSheet.createBuilder(getContext(), getFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles(informs)
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        LogUtil.i("CommunicateFragment--->showInform--->index::" + index);
                        if (index == 0) {
                            // takePhoto();
                            ((HomeActivity)getActivity()).autoObtainCameraPermission(curretnRequestCode);
                        } else if (index == 1) {
                            //pickPhoto();
                            ((HomeActivity)getActivity()).autoObtainStoragePermission(curretnRequestCode);
                        }

                    }
                }).show();
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
            String timestamp = "/" + formatter.format(new Date()) + ".jpg";
            File imageFile = new File(imageFilePath, timestamp);// 通过路径创建保存文件
            imagePath = imageFile.getAbsolutePath();
            Uri imageFileUri = Uri.fromFile(imageFile);// 获取文件的Uri
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);// 告诉相机拍摄完毕输出图片到指定的Uri
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
        public void onResult(String mImagePath, int requestCode) {
            if (!TextUtils.isEmpty(mImagePath)) {
                imagePath = mImagePath;
            }
            //获取图片缩略图，避免OOM
            Bitmap bitmap = ImageUtils.getImageThumbnail(imagePath, ImageUtils.getWidth(getContext()) / 3 - 5, ImageUtils.getWidth(getContext()) / 3 - 5);
            path1 = imagePath;
            iv_upload.setImageBitmap(bitmap);
            /*switch (requestCode){
                case SELECT_IMAGE_RESULT_CODE1:
                    imgUpload1.setImageBitmap(bitmap);
                    path1 = imagePath;
                    break;
                case SELECT_IMAGE_RESULT_CODE2:
                    imgUpload2.setImageBitmap(bitmap);
                    path2 = imagePath;
                    break;
                case SELECT_IMAGE_RESULT_CODE3:
                    imgUpload3.setImageBitmap(bitmap);
                    path3 = imagePath;
                    break;
            }*/
        }

    };

 /*   private String getImageCode(String path) {
        String code = "";
        if (!TextUtils.isEmpty(path)) {
            byte[] data = null;
            try {
                InputStream in = new FileInputStream(path);
                data = new byte[in.available()];
                in.read(data);
                in.close();
                code = Base64.encodeToString(data, Base64.DEFAULT);
            } catch (Exception e) {
                LogUtil.i("SafeguardDetailFragment--->getImageCode--->" + e);
            }
        }

        return code;
    }*/

}
