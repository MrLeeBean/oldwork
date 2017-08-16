
package com.nfu.oldwork.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nfu.oldwork.R;



/*

*/

/**
 * Created by Administrator on 2017/7/26.
 *//*
*/



public class ContactCallDailog extends Dialog{





/**
     * 自定义对话框    AlertDialog.Builder 模式
     *
     * @author Administrator
     * @version 1.0
     * @date 2016-10-18 下午4:15:04
     *//*
*/


    public ContactCallDailog(Context context) {
        super(context);
    }

    public ContactCallDailog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics d = getContext().getResources().getDisplayMetrics(); // 获取屏幕宽、高用

        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        lp.height = (int) (d.heightPixels * 0.3); // 宽度设置为屏幕的0.8
        window.setAttributes(lp);

       /* DisplayMetrics dm = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // layoutParams.width = (int) (dm.widthPixels / 2);
        // layoutParams.height = (int) (dm.heightPixels / 2.2);

        layoutParams.width = (int) (DisplayManagers.getInstance().getScreenWidth() * 0.55);
        layoutParams.height = (int) (DisplayManagers.getInstance().getScreenHeight() * 0.55);
        window.setAttributes(layoutParams);*/

    }

    public static class Builder {
            private Context mContext;
            private String mTitle;
            private String mPositiveButtonText;
            private String mNegativeButtonText;
            private boolean flag = true;
            private boolean isHost = false;

            private OnClickListener mPositiveButtonClickListener, mNegativeButtonClickListener;

            public Builder(Context context) {
                mContext = context;
            }

            public Builder setContent(String content) {
                mTitle = content;
                return this;
            }

            public Builder setTitle(int resId) {
                mTitle = (String) mContext.getText(resId);
                return this;
            }

            public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
                mPositiveButtonText = positiveButtonText;
                mPositiveButtonClickListener = listener;
                return this;
            }

            public Builder setCancelable(boolean flag) {
                this.flag = flag;
                return this;
            }

            public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
                mPositiveButtonText = (String) mContext.getText(positiveButtonText);
                mPositiveButtonClickListener = listener;
                return this;
            }

            public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
                mNegativeButtonText = negativeButtonText;
                mNegativeButtonClickListener = listener;
                return this;
            }

            public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
                mNegativeButtonText = (String) mContext.getText(negativeButtonText);
                mNegativeButtonClickListener = listener;
                return this;
            }

            public ContactCallDailog create() {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                View view = inflater.inflate(R.layout.contact_call_dialog, null);
                final ContactCallDailog customAlertDialog = new ContactCallDailog(mContext,R.style.Dialog);
                TextView tvAlertTitle = (TextView) view.findViewById(R.id.call_number);
                tvAlertTitle.setText(mTitle);

                Button btnPositive = (Button) view.findViewById(R.id.positiveButton);
                if (!TextUtils.isEmpty(mPositiveButtonText)) {
                    btnPositive.setText(mPositiveButtonText);
                    btnPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            mPositiveButtonClickListener.onClick(customAlertDialog, BUTTON_POSITIVE);
                        }
                    });
                } else {
                    btnPositive.setVisibility(View.GONE);
                }
                Button btnNegative = (Button) view.findViewById(R.id.negativeButton);
                if (!TextUtils.isEmpty(mNegativeButtonText)) {
                    btnNegative.setText(mNegativeButtonText);
                    if (mNegativeButtonClickListener != null) {
                        btnNegative.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mNegativeButtonClickListener.onClick(customAlertDialog, BUTTON_NEGATIVE);
                            }
                        });
                    } else {
                        btnNegative.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customAlertDialog.dismiss();
                            }
                        });
                    }
                } else {
                    btnNegative.setVisibility(View.GONE);
                }
                if (View.VISIBLE == btnPositive.getVisibility() && View.GONE == btnNegative.getVisibility()) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                    btnPositive.setLayoutParams(layoutParams);
                }
                customAlertDialog.setContentView(view);
                customAlertDialog.setCancelable(flag);
                return customAlertDialog;
            }

        }


}

