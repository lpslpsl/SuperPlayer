package com.example.lps.superplayer.viewpagerindicator.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.lps.superplayer.viewpagerindicator.PositionData;
import com.example.lps.superplayer.viewpagerindicator.indicator.IPagerIndicatorView;

import java.util.List;

/**
 * Created by lps on 2017/8/17.
 *
 * @version 1
 * @see
 * @since 2017/8/17 15:17
 */


public class ViewPagerIndicatorView extends View implements IPagerIndicatorView {
    private Context mContext;
    int mVerticalPadding;
    int mHorizonalPadding;
    Paint mPaint;
    List<PositionData> mPositionDatas;
    private RectF mRectF = new RectF();
    LinearInterpolator mStartInterpolator = new LinearInterpolator();
    LinearInterpolator mEndInterpolator = new LinearInterpolator();
    int mRoundRadius;
    private int fiterColor = Color.RED;

    public ViewPagerIndicatorView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mVerticalPadding = dip2px(6);
        mHorizonalPadding = dip2px(10);
    }

    private int dip2px(int value) {
        float mDensity = mContext.getResources().getDisplayMetrics().density;
        return (int) (value * mDensity + 0.5f);
    }

    @Override
    public void onPagerSelected(int position) {

    }

    @Override
    public void onPagerScrolled(int position, float positionOffsetPersent, int positionOffsetPixel) {
        if (mPositionDatas == null || mPositionDatas.isEmpty()) {
            return;
        }
        int currentPosition = Math.min(mPositionDatas.size() - 1, position);
        int nextPosition = Math.min(mPositionDatas.size() - 1, position + 1);
        PositionData current = mPositionDatas.get(currentPosition);
        PositionData next = mPositionDatas.get(nextPosition);
        mRectF.left = current.mContentLeft - mHorizonalPadding +
                (next.mContentLeft - current.mContentLeft) *
                        mEndInterpolator.getInterpolation(positionOffsetPersent);
        mRectF.right = current.mContentRight + mHorizonalPadding +
                (next.mContentRight - current.mContentRight) * mStartInterpolator.getInterpolation(positionOffsetPersent);
        mRectF.bottom = current.mContentBottom + mVerticalPadding;
        mRectF.top = current.mContentTop - mVerticalPadding;
        mRoundRadius = (int) (mRectF.height() / 2);
        invalidate();
    }

    @Override
    public void onPagerScrollStateChanaged(int position) {

    }

    @Override
    public void setPositionData(List<PositionData> list) {
        mPositionDatas = list;
    }

    public void setFiterColor(int mFiterColor) {
        fiterColor = mFiterColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(fiterColor);
        canvas.drawRoundRect(mRectF, mRoundRadius, mRoundRadius, mPaint);
    }
}
