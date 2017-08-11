package com.nfu.oldwork.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nfu.oldwork.R;
import com.nfu.oldwork.utils.LogUtil;


/**
 * Created by landptf on 2016/10/31.
 * 扩展Button，支持文字和icon分上下左右四种方式显示
 * 默认为左右结构，图片在左，文字在右
 */
public class ButtonExtendM extends RelativeLayout {
    /**
     * 左右结构，图片在左，文字在右
     */
    public static final int STYLE_ICON_LEFT = 0;
    /**
     * 左右结构，图片在右，文字在左
     */
    public static final int STYLE_ICON_RIGHT = 1;
    /**
     * 上下结构，图片在上，文字在下
     */
    public static final int STYLE_ICON_UP = 2;
    /**
     * 上下结构，图片在下，文字在上
     */
    public static final int STYLE_ICON_DOWN = 3;

    private static final int CENTER = 0;
    private static final int CENTER_CROP = 1;
    private static final int CENTER_INSIDE = 2;
    private static final int FIT_CENTER = 3;
    private static final int FIT_END = 4;
    private static final int FIT_START = 5;
    private static final int FIT_XY = 6;
    private static final int MATRIX = 7;

    /*
    *定义按钮类型
    */
    private static final int NORMAL = 0;
    private static final int CHECK = 1;

    /**
     * 定义控件
     */
    private ImageView ivIcon;
    private TextView tvContent;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * View的背景色
     */
    private int backColor = 0;
    /**
     * View被按下时的背景色
     */
    private int backColorPress = 0;
    /**
     * icon的背景图片
     */
    private Drawable iconDrawable = null;
    /**
     * icon被按下时显示的背景图片
     */
    private Drawable iconDrawablePress = null;
    /**
     * View文字的颜色
     */
    private ColorStateList textColor = null;
    /**
     * View被按下时文字的颜色
     */
    private ColorStateList textColorPress = null;
    /**
     * 两个控件之间的间距，默认为11dp
     */
    private int spacing = 11;
    /**
     * 两个控件的位置结构
     */
    private int mStyle = STYLE_ICON_LEFT;
    /**
     * 标示onTouch方法的返回值，用来解决onClick和onTouch冲突问题
     */
    private boolean isCost = true;

    /**
     * 判断当前控件是否是选中状态
     */
    private boolean isSelected = false;
    /**
     * 判断当前控件类型
     */
    private int buttonType = NORMAL;

    private int imgHeight = 0;
    private int imgWidth = 0;

    private ImageView.ScaleType scaleType;

    private OnClickListener onClickListener = null;

    public interface OnClickListener {
        void onClick(View v);
    }

    /**
     * 设置View的Click事件
     *
     * @param l
     */
    public void setOnClickListener(OnClickListener l) {
        this.onClickListener = l;
        isCost = false;
    }

    public ButtonExtendM(Context context) {
        this(context, null, 0);
    }

    public ButtonExtendM(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonExtendM(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.nfu_view_button_extend_m, this, true);
        //初始化控件
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        tvContent = (TextView) findViewById(R.id.tv_content);
        setGravity(Gravity.CENTER);
        TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.buttonExtendM, defStyle, 0);
        if (a != null) {
            //设置背景色
            ColorStateList colorList = a.getColorStateList(R.styleable.buttonExtendM_backColor);
            if (colorList != null) {
                backColor = colorList.getColorForState(getDrawableState(), 0);
                if (backColor != 0) {
                    setBackgroundColor(backColor);
                }
            }
            //记录View被按下时的背景色
            ColorStateList colorListPress = a.getColorStateList(R.styleable.buttonExtendM_backColorPress);
            if (colorListPress != null) {
                backColorPress = colorListPress.getColorForState(getDrawableState(), 0);
            }
            //设置icon
            iconDrawable = a.getDrawable(R.styleable.buttonExtendM_iconDrawable);
            if (iconDrawable != null) {
                ivIcon.setImageDrawable(iconDrawable);
            }

            scaleType = getScaleType(a.getInt(R.styleable.buttonExtendM_scType,0));
            ivIcon.setScaleType(scaleType);

            //记录View被按下时的icon的图片
            iconDrawablePress = a.getDrawable(R.styleable.buttonExtendM_iconDrawablePress);
            //设置文字的颜色
            textColor = a.getColorStateList(R.styleable.buttonExtendM_nfuTextColor);
            if (textColor != null) {
                tvContent.setTextColor(textColor);
            }
            //记录View被按下时文字的颜色
            textColorPress = a.getColorStateList(R.styleable.buttonExtendM_textColorPress);
            //设置显示的文本内容
            String text = a.getString(R.styleable.buttonExtendM_text);
            if (text != null) {
                //默认为隐藏的，设置文字后显示出来
                tvContent.setVisibility(VISIBLE);
                tvContent.setText(text);
            }
            //设置文本字体大小
            float textSize = a.getDimensionPixelSize(R.styleable.buttonExtendM_nfuTextSize, 0);
            if (textSize != 0) {
                tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
            }

            //获取图片宽高
            imgWidth = a.getDimensionPixelSize(R.styleable.buttonExtendM_imgWidth,0);
            imgHeight = a.getDimensionPixelSize(R.styleable.buttonExtendM_imgHeight,0);

            //设置两个控件之间的间距
            spacing = a.getDimensionPixelSize(R.styleable.buttonExtendM_spacing,dp2px(context, 8));
            //spacing = dp2px(context,f_spacing);
            //设置两个控件的位置结构
            mStyle = a.getInt(R.styleable.buttonExtendM_drec, 0);
            setIconStyle(mStyle);
            //获取控件类型
            buttonType = a.getInt(R.styleable.buttonExtendM_buttonType,0);

            a.recycle();
        }

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                //根据touch事件设置按下抬起的样式
                return setTouchStyle(event.getAction());
            }
        });

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 设置为被选中状态
     *   @param state in MotionEvent.ACTION_DOWN or MotionEvent.ACTION_UP
     */
    public void setPressState(int state){
        if (state != MotionEvent.ACTION_DOWN && state != MotionEvent.ACTION_UP){
            LogUtil.i("无效参数");
            return;
        }
        setTouchStyle(state);

    }

    /**
     * 根据按下或者抬起来改变背景和文字样式
     *
     * @param state
     * @return isCost
     */
    private boolean setTouchStyle(int state) {
        if(buttonType == NORMAL){
            if (state == MotionEvent.ACTION_DOWN) {
                if (backColorPress != 0) {
                    setBackgroundColor(backColorPress);
                }
                if (iconDrawablePress != null) {
                    ivIcon.setImageDrawable(iconDrawablePress);
                }
                if (textColorPress != null) {
                    tvContent.setTextColor(textColorPress);
                }
            }
            if (state == MotionEvent.ACTION_UP) {
                if (backColor != 0) {
                    setBackgroundColor(backColor);
                }
                if (iconDrawable != null) {
                    ivIcon.setImageDrawable(iconDrawable);
                }
                if (textColor != null) {
                    tvContent.setTextColor(textColor);
                }
            }
        }else{
            if (state == MotionEvent.ACTION_UP) {
                isSelected = !isSelected;
                setNfuSeleted(isSelected);

            }
        }
        return isCost;
    }

    /**
     * 设置图标位置
     * 通过重置LayoutParams来设置两个控件的摆放位置
     * @param style
     */
    public void setIconStyle(int style) {
        mStyle = style;
        LayoutParams lp;
        if(imgWidth<=0){
            imgWidth = LayoutParams.WRAP_CONTENT;
        }

        if(imgHeight<=0){
            imgHeight = LayoutParams.WRAP_CONTENT;
        }
        switch (style) {
            case STYLE_ICON_LEFT:
                lp = new LayoutParams(imgWidth, imgHeight);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                ivIcon.setLayoutParams(lp);
                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
                lp.leftMargin = spacing;
                tvContent.setLayoutParams(lp);
                break;
            case STYLE_ICON_RIGHT:
                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                lp.rightMargin = spacing;
                tvContent.setLayoutParams(lp);
                lp = new LayoutParams(imgWidth,imgHeight);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                lp.addRule(RelativeLayout.RIGHT_OF, tvContent.getId());
                ivIcon.setLayoutParams(lp);
                break;
            case STYLE_ICON_UP:
                lp = new LayoutParams(imgWidth, imgHeight);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                ivIcon.setLayoutParams(lp);
                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.addRule(RelativeLayout.BELOW, ivIcon.getId());
                lp.topMargin = spacing;
                tvContent.setLayoutParams(lp);
                break;
            case STYLE_ICON_DOWN:
                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                tvContent.setLayoutParams(lp);
                lp = new LayoutParams(imgWidth, imgHeight);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.addRule(RelativeLayout.BELOW, tvContent.getId());
                lp.topMargin = spacing;
                ivIcon.setLayoutParams(lp);
                break;
            default:
                break;
        }
    }

    private ImageView.ScaleType getScaleType(int type){
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
        switch (type){
            case CENTER:
                scaleType = ImageView.ScaleType.CENTER;
                break;
            case CENTER_CROP:
                scaleType = ImageView.ScaleType.CENTER_CROP;
                break;
            case CENTER_INSIDE:
                scaleType = ImageView.ScaleType.CENTER_INSIDE;
                break;
            case FIT_CENTER:
                scaleType = ImageView.ScaleType.FIT_CENTER;
                break;
            case FIT_END:
                scaleType = ImageView.ScaleType.FIT_END;
                break;
            case FIT_START:
                scaleType = ImageView.ScaleType.FIT_START;
                break;
            case FIT_XY:
                scaleType = ImageView.ScaleType.FIT_XY;
                break;
            case MATRIX:
                scaleType = ImageView.ScaleType.MATRIX;
                break;
        }
        return scaleType;
    }

    /**
     * 设置View的背景色
     *
     * @param backColor
     */
    public void setBackColor(int backColor) {
        this.backColor = backColor;
        setBackgroundColor(backColor);
    }

    /**
     * 设置View被按下时的背景色
     *
     * @param backColorPress
     */
    public void setBackColorPress(int backColorPress) {
        this.backColorPress = backColorPress;
    }

    /**
     * 设置icon的图片
     *
     * @param iconDrawable
     */
    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
        ivIcon.setImageDrawable(iconDrawable);
    }

    /**
     * 设置View被按下时的icon的图片
     *
     * @param iconDrawablePress
     */
    public void setIconDrawablePress(Drawable iconDrawablePress) {
        this.iconDrawablePress = iconDrawablePress;
    }

    /**
     * 设置文字的颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        if (textColor == 0) return;
        this.textColor = ColorStateList.valueOf(textColor);
        tvContent.setTextColor(this.textColor);
    }

    /**
     * 设置View被按下时文字的颜色
     *
     * @param textColorPress
     */
    public void setTextColorPress(int textColorPress) {
        if (textColorPress == 0) return;
        this.textColorPress = ColorStateList.valueOf(textColorPress);
    }

    /**
     * 设置显示的文本内容
     *
     * @param text
     */
    public void setText(CharSequence text) {
        //默认为隐藏的，设置文字后显示出来
        tvContent.setVisibility(VISIBLE);
        tvContent.setText(text);
    }

    /**
     * 获取显示的文本
     *
     * @return
     */
    public String getText() {
        return tvContent.getText().toString();
    }

    /**
     * 设置文本字体大小
     *
     * @param size
     */
    public void setTextSize(float size) {
        tvContent.setTextSize(size);
    }

    /**
     * 设置两个控件之间的间距
     *
     * @param spacing
     */
    public void setSpacing(int spacing) {
        this.spacing = dp2px(mContext, spacing);
        //设置完成后刷新一下两个控件的结构，避免先执行了setIconStyle后，setSpacing不生效
        setIconStyle(mStyle);
    }

    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setNfuSeleted(boolean state){
  /*      ivIcon.setScaleType(scaleType);
        setIconStyle(mStyle);*/
        isSelected = state;
        if(isSelected){
            if (backColorPress != 0) {
                setBackgroundColor(backColorPress);
            }
            if (iconDrawablePress != null) {
                ivIcon.setImageDrawable(iconDrawablePress);
            }
            if (textColorPress != null) {
                tvContent.setTextColor(textColorPress);
            }
        }else {
            if (backColor != 0) {
                setBackgroundColor(backColor);
            }
            if (iconDrawable != null) {
                ivIcon.setImageDrawable(iconDrawable);
            }
            if (textColor != null) {
                tvContent.setTextColor(textColor);
            }
        }
    }
}
