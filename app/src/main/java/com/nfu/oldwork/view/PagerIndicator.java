package com.nfu.oldwork.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nfu.oldwork.R;
import com.nfu.oldwork.utils.LogUtil;


/**
 * Created by zz on 2016/8/22.
 */
public class PagerIndicator extends LinearLayout {

    private ViewPager mViewPager;

    private LinearLayout mIndicatorContainer;
    private boolean shouldExpand = false;
    private Paint selectPaint;
    private Paint unSelectPaint;
    private Paint underlinePaint;
    private int colorSelectPoint = 0xff00ff00;
    private int colorUnSelectPoint = 0xffff00ff;

    private int tabTextColorSelect = 0xffff00ff;
    private int tabTextColorUnSelect = 0xff00ff00;
    private int underlineHeight = 10;
    private int underlinePadding = 0;
    private int underlineColor = 0xffe96d5b;
    private int tabTextSize = 20;
    private int tabPadding = 24;

    private LayoutParams defaultTabLayoutParams;
    private LayoutParams expandedTabLayoutParams;

    private int currentPosition = 0;
    private float currentPositionOffset = 0f;

    private int tabCount;
    private int lastScrollX = 0;


    public PagerIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {

        setWillNotDraw(false);

        //init attrs
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PagerIndicator);
            shouldExpand = a.getBoolean(R.styleable.PagerIndicator_should_tab_expand, false);
            tabTextColorSelect = a.getColor(R.styleable.PagerIndicator_select_tab_text_color, 0xffff00ff);
            tabTextColorUnSelect = a.getColor(R.styleable.PagerIndicator_unselect_tab_text_color, 0xff00ff00);
            underlineHeight = a.getDimensionPixelSize(R.styleable.PagerIndicator_underline_height, 10);
            underlinePadding = a.getDimensionPixelSize(R.styleable.PagerIndicator_underline_padding, 20);
            tabPadding = a.getDimensionPixelSize(R.styleable.PagerIndicator_tab_padding, 24);
            underlineColor = a.getColor(R.styleable.PagerIndicator_underline_color, 0xffe96d5b);
            tabTextSize = a.getDimensionPixelSize(R.styleable.PagerIndicator_tab_text_size, 20);
            LogUtil.d("tabTextSize"+tabTextSize);
            a.recycle();
        }
        //initial paints
        initPaints();
        //initial linearLayout
        initContainer(context);
        //initial child layout params
        initParams();

    }

    private void initPaints() {
        unSelectPaint = new Paint();
        unSelectPaint.setAntiAlias(true);
        unSelectPaint.setColor(colorUnSelectPoint);
        selectPaint = new Paint();
        selectPaint.setAntiAlias(true);
        selectPaint.setStyle(Paint.Style.FILL);
        selectPaint.setColor(colorSelectPoint);
        underlinePaint = new Paint();
        underlinePaint.setColor(underlineColor);
        underlinePaint.setStyle(Paint.Style.FILL);
        underlinePaint.setAntiAlias(true);
    }

    private void initContainer(Context context) {
        mIndicatorContainer = new LinearLayout(context);
        mIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        mIndicatorContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mIndicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
        addView(mIndicatorContainer);
    }

    private void initParams() {
        defaultTabLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnderline(canvas);
    }

    private void drawUnderline(Canvas canvas) {
        if (mViewPager != null) {
            View currentTab = getItem(currentPosition);
            if (currentTab != null) {
                float lineLeft = currentTab.getLeft() + underlinePadding;
                float lineRight = currentTab.getRight() - underlinePadding;

                // if there is an offset, start interpolating left and right coordinates between current and next tab
                if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {

                    View nextTab = getItem(currentPosition + 1);
                    final float nextTabLeft = nextTab.getLeft() + underlinePadding;
                    final float nextTabRight = nextTab.getRight() - underlinePadding;

                    lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
                    lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
                }
                canvas.drawRect(lineLeft, getHeight() - underlineHeight, lineRight, getHeight(), underlinePaint);
            }
        }
    }

    public void setCurrentItem(int position, boolean animate) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position, animate);
        }
    }

    /**
     * bind indicator to your viewpager.
     *
     * @param viewPager your viewpager
     */
    public void setViewPager(ViewPager viewPager, int select) {
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        tabCount = viewPager.getAdapter().getCount();
        this.mViewPager = viewPager;
        addPageChangeListener();
        setUpText();
        selectText(select);
    }

    private void setUpText() {
        mIndicatorContainer.removeAllViews();
        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            TextView tv = new TextView(getContext());
            tv.setFocusable(true);
            tv.setClickable(true);
            tv.setSingleLine();
            tv.setGravity(Gravity.CENTER);
            tv.setText(mViewPager.getAdapter().getPageTitle(i));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
            final int finalI = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCurrentItem(finalI, true);
                }
            });
            tv.setPadding(tabPadding, 0, tabPadding, 0);
            mIndicatorContainer.addView(tv, i, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
        }
    }

    private void addPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPosition = position;
                currentPositionOffset = positionOffset;
                invalidate();
            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SaveState saveState = new SaveState(superState);
        saveState.position = currentPosition;
        return saveState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SaveState savedState = (SaveState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.position;
    }

    static class SaveState extends BaseSavedState {
        int position;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.position);
        }

        public SaveState(Parcelable superState) {
            super(superState);
        }

        protected SaveState(Parcel in) {
            super(in);
            this.position = in.readInt();
        }

        public static final Creator<SaveState> CREATOR = new Creator<SaveState>() {
            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[size];
            }
        };
    }

    private void scrollToChild(int position, int offset) {
        if (tabCount == 0) {
            return;
        }

        if (getItem(position) != null) {
            int newScrollX = getItem(position).getLeft() + offset;

            if (newScrollX != lastScrollX) {
                lastScrollX = newScrollX;
                scrollTo(newScrollX, 0);
            }
        }

    }

    private void selectText(int position) {
        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            TextView tv = (TextView) getItem(i);
            if (i == position) {
                tv.setTextColor(tabTextColorSelect);
            } else {
                tv.setTextColor(tabTextColorUnSelect);
            }
        }
    }

    private View getItem(int position) {
        return mIndicatorContainer.getChildAt(position);
    }
}
