package com.example.lps.superplayer.viewpagerindicator.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by lps on 2017/8/17.
 *
 * @version 1
 * @see
 * @since 2017/8/17 14:44
 */


@SuppressLint("AppCompatCustomView")
public class ViewPagerTitle extends TextView implements IViewPagerTitleView {
    private Context mContext;
    private int normolColor;
    private int selectedColor;

    public ViewPagerTitle(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        int padding=dip2px(20);
        setPadding(padding,0,padding,0);
        setMaxLines(1);
        setEllipsize(TextUtils.TruncateAt.END);

    }

    private int dip2px(int dip) {
        float mDensity = mContext.getResources().getDisplayMetrics().density;
        return (int) (mDensity*dip+0.5f);
    }

    @Override
    public void onSelected(int index, int totalCount) {
setTextColor(selectedColor);
    }

    @Override
    public void onDisSelected(int index, int totalCount) {
setTextColor(normolColor);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean isLeftToRight) {

    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean isLeftToRight) {

    }

    @Override
    public int  getContentLeft() {
        Rect bond=new Rect();
        getPaint().getTextBounds(getText().toString(),0,getText().length(),bond);
        int contentWith=bond.width();
return getLeft()+getWidth()/2-contentWith/2;
    }

    @Override
    public int getContentTop() {
        Paint.FontMetrics mMetrics=getPaint().getFontMetrics();
        float contentHeight=mMetrics.bottom- mMetrics.top;
        return (int) (getHeight()/2-contentHeight/2);
    }

    @Override
    public int getContentRight() {
        Rect bond=new Rect();
        getPaint().getTextBounds(getText().toString(),0,getText().length(),bond);
        int contentWith=bond.width();
        return getLeft()+getWidth()/2+contentWith/2;
    }

    @Override
    public int  getContentBottom() {
        Paint.FontMetrics mMetrics=getPaint().getFontMetrics();
        float contentHeight=mMetrics.bottom- mMetrics.top;
        return (int) (getHeight()/2+contentHeight/2);
    }

    public void setNormolColor(int mNormolColor) {
        normolColor = mNormolColor;
    }

    public void setSelectedColor(int mSelectedColor) {
        selectedColor = mSelectedColor;
    }
}
