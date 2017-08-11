package com.nfu.oldwork.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nfu.oldwork.R;
import com.nfu.oldwork.adapter.HotAdPagerAdapter;


/**
 * Created by wpp on 2017/4/26.
 */
public class PointPagerIndicator extends HorizontalScrollView {

    private ViewPager mViewPager;

    private LinearLayout mIndicatorContainer;
    private Paint selectPaint;
    private Paint unSelectPaint;
    private int colorSelectPoint = 0xff00ff00;
    private int colorUnSelectPoint = 0xffff00ff;
    private int selectPointSize = 100;
    private int unSelectPointSize = 90;
    private int spacing = 8;



    private int currentPosition = 0;
    private int tabCount;
    private int lastScrollX = 0;
    private boolean mIsTwoPage = false;


    public PointPagerIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public PointPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public PointPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {

        setFillViewport(true);
        setWillNotDraw(false);

        //init attrs
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PointPagerIndicator);
            colorSelectPoint = a.getColor(R.styleable.PointPagerIndicator_select_point_color, 0xff00ff00);
            colorUnSelectPoint = a.getColor(R.styleable.PointPagerIndicator_unselect_point_color, 0xffff00ff);
            selectPointSize = a.getDimensionPixelSize(R.styleable.PointPagerIndicator_select_point_size, 100);
            unSelectPointSize = a.getDimensionPixelSize(R.styleable.PointPagerIndicator_unselect_point_size, 90);
            spacing = a.getDimensionPixelSize(R.styleable.PointPagerIndicator_point_spacing, 8);
            a.recycle();
        }
        setMinimumHeight(selectPointSize > unSelectPointSize ? selectPointSize * 2 : unSelectPointSize * 2);
        //initial paints
        initPaints();
        //initial linearLayout
        initContainer(context);
        //initial child layout params

    }

    private void initPaints() {
        unSelectPaint = new Paint();
        unSelectPaint.setAntiAlias(true);
        unSelectPaint.setColor(colorUnSelectPoint);
        selectPaint = new Paint();
        selectPaint.setAntiAlias(true);
        selectPaint.setStyle(Paint.Style.FILL);
        selectPaint.setColor(colorSelectPoint);
    }

    private void initContainer(Context context) {
        mIndicatorContainer = new LinearLayout(context);
        mIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        mIndicatorContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mIndicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
        addView(mIndicatorContainer);
    }


    @Override
    protected void onDraw(Canvas canvas) {
         drawPoints(canvas);
    }

    private void drawPoints(Canvas canvas) {
        if (mViewPager != null) {
            int count = ((HotAdPagerAdapter)mViewPager.getAdapter()).getItemCount();
            if(mIsTwoPage){
                count = 2;
            }
            int index = mViewPager.getCurrentItem();
            index %= count;
            if (index < 0){
                index = count + index;
            }
            int uLeft = (getWidth() - unSelectPointSize * count - spacing * (count - 1)) / 2;
            int y = getHeight() / 2;
            int sR = selectPointSize / 2;
            int uR = unSelectPointSize / 2;
            for (int i = 0; i < count; i++) {
                int uX = uLeft + 2 * i * uR + uR + i * spacing;
                canvas.drawCircle(uX, y, uR, unSelectPaint);
            }
            float uX = uLeft + (2 * index + 1) * uR + index * spacing;
            canvas.drawCircle(uX, y, sR, selectPaint);
        }
    }

    public void setIsTwoPage(boolean isTwoPage){
        this.mIsTwoPage = isTwoPage;
    }
    public void setCurrentItem(int position, boolean animate) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position, animate);
        }
    }

    /**
     * bind indicator to your viewpager.
     * @param viewPager your viewpager
     */
    public void setViewPager(ViewPager viewPager) {
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        tabCount = ((HotAdPagerAdapter)viewPager.getAdapter()).getItemCount();
        if(mIsTwoPage){
            tabCount = 2;
        }
        this.mViewPager = viewPager;
        addPageChangeListener();

        int count = ((HotAdPagerAdapter)viewPager.getAdapter()).getItemCount();
        if(mIsTwoPage){
            count = 2;
        }
        setMinimumWidth(selectPointSize > unSelectPointSize ? selectPointSize * count + spacing * (count - 1) : unSelectPointSize * count + spacing * (count - 1));
    }

    private void addPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPosition = position;
                invalidate();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

/*    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SaveState saveState = new SaveState(superState);
        saveState.position = currentPosition;
        return saveState;
    }

    static class SaveState extends Parcelable {
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

        public static final Parcelable.Creator<SaveState> CREATOR = new Parcelable.Creator<SaveState>() {
            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[size];
            }
        };
    }*/

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

    private View getItem(int position) {
        return mIndicatorContainer.getChildAt(position);
    }
}
