package com.nfu.oldwork.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nfu.oldwork.R;


public class NfuCustomDialog extends Dialog {
  
    public NfuCustomDialog(Context context) {
        super(context);  
    }  
  
    public NfuCustomDialog(Context context, int theme) {
        super(context, theme);  
    }  
  
    public static class Builder {  
        private Context context;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }


        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;  
            this.negativeButtonClickListener = listener;  
            return this;  
        }  
  
        public NfuCustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme  
            final NfuCustomDialog dialog = new NfuCustomDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.nfu_custom_dialog, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title  
            // set the confirm button
            if (positiveButtonText != null) {  
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);  
                if (positiveButtonClickListener != null) {  
                    ((Button) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,  
                                            DialogInterface.BUTTON_POSITIVE);
                                }  
                            });  
                }  
            } else {  
                // if no confirm button just set the visibility to GONE  
                layout.findViewById(R.id.positiveButton).setVisibility(  
                        View.GONE);
            }  
            // set the cancel button  
            if (negativeButtonText != null) {  
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);  
                if (negativeButtonClickListener != null) {  
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,  
                                            DialogInterface.BUTTON_NEGATIVE);
                                }  
                            });  
                }  
            } else {  
                // if no confirm button just set the visibility to GONE  
                layout.findViewById(R.id.negativeButton).setVisibility(  
                        View.GONE);
            }  
            // set the content message  
            if (message != null) {  
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {  
                // if no message set  
                // add the contentView to the dialog body  
                ((LinearLayout) layout.findViewById(R.id.content))
                        .removeAllViews();  
                ((LinearLayout) layout.findViewById(R.id.content))
                        .addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }  
            dialog.setContentView(layout);  
            return dialog;  
        }  
    }  
} 