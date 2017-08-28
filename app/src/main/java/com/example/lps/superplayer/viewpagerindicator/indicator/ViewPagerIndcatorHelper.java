package com.example.lps.superplayer.viewpagerindicator.indicator;

import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

import com.example.lps.superplayer.viewpagerindicator.title.IPagerTitle;

/**
 * Created by lps on 2017/8/18.
 *
 * @version 1
 * @see
 * @since 2017/8/18 14:42
 */
// TODO: 2017/8/21  注释
public class ViewPagerIndcatorHelper {
    private int mCurrentIndex;
    private int mTotalCount;
    private int mScrollState = ViewPager.SCROLL_STATE_IDLE;
    private int mLastIndex;
    private float mLastPositionOffsetSum;
    private SparseArray<Float> mLeavePercents = new SparseArray<>();
    private SparseBooleanArray mDeselectedItems = new SparseBooleanArray();

    public ViewPagerIndcatorHelper() {
    }

    private IPagerTitle mScrollListener;

    public void setScrollListener(IPagerTitle mScrollListener) {
        this.mScrollListener = mScrollListener;
    }

    public void onPageScrolled(int position, float positionOffset, int positionPixel) {
        float mCurrentPositionOffsetSum = positionOffset + position;
        boolean isLeftTopRight = mCurrentPositionOffsetSum > mLastPositionOffsetSum;
        int safePosition = getSafeIndex(position);
        if (mScrollState != ViewPager.SCROLL_STATE_IDLE) {
            int enterIndex, levaeIndex;
            float enterPercent, leavePercent;
            if (isLeftTopRight) {
                enterIndex = getSafeIndex(position + 1);
                enterPercent = positionOffset;
                levaeIndex = safePosition;
                leavePercent = positionOffset;
            } else {
                enterIndex = safePosition;
                enterPercent = 1.0f - positionOffset;
                levaeIndex = getSafeIndex(safePosition + 1);
                leavePercent = 1.0f - positionOffset;
            }
            for (int i = 0; i < mTotalCount; i++) {
                if (i == enterIndex || i == levaeIndex) {
                    continue;
                }
                leavePercent = mLeavePercents.get(i, 0.0f);
                if (leavePercent != 1.0f) {
                    mScrollListener.onLeave(i, mTotalCount, 1.0f, isLeftTopRight);
                    mLeavePercents.put(i, 1.0f);
                }
            }
            if (enterIndex == levaeIndex) {
                if (enterIndex == mTotalCount - 1
                        && mLeavePercents.get(enterIndex, 0.0f) != 0.0f
                        && enterPercent == 0.0f
                        && isLeftTopRight) {
                    boolean disPatchEnterEnvnt = mScrollState == ViewPager.SCROLL_STATE_DRAGGING || enterIndex == mCurrentIndex;
                    if (disPatchEnterEnvnt) {
                        mScrollListener.onEnter(enterIndex, mTotalCount, 1.0f, true);
                    }
                }
                return;
            }
            if (1.0f - mLeavePercents.get(enterIndex, 0.0f) != enterIndex) {
                boolean disPatchEnterEvent = mScrollState == ViewPager.SCROLL_STATE_DRAGGING || enterIndex == mCurrentIndex;
                if (disPatchEnterEvent) {
                    mScrollListener.onEnter(enterIndex, mTotalCount, enterPercent, isLeftTopRight);
                    mLeavePercents.put(enterIndex, 1.0f - enterIndex);
                }
            }
            if (mLeavePercents.get(levaeIndex, 0.0f) != leavePercent) {
                if (isLeftTopRight
                        && levaeIndex == getSafeIndex(mCurrentIndex)
                        && leavePercent == 0.0f) {
                    boolean disPatchEnterEvent = mScrollState == ViewPager.SCROLL_STATE_DRAGGING || levaeIndex == mCurrentIndex;
                    if (disPatchEnterEvent) {
                        mScrollListener.onEnter(levaeIndex, mTotalCount, 1.0f, true);
                        mLeavePercents.put(levaeIndex, 0.0f);
                    }
                } else {
                    boolean disPatchLeaveEvent = mScrollState == ViewPager.SCROLL_STATE_DRAGGING ||
                            levaeIndex == mLastIndex
                            || (levaeIndex == mCurrentIndex - 1) && mLeavePercents.get(levaeIndex, 0.0f) != 1.0f
                            || (levaeIndex == mCurrentIndex + 1) && mLeavePercents.get(levaeIndex, 0.0f) != 1.0f;
                    if (disPatchLeaveEvent) {
                        mScrollListener.onLeave(levaeIndex, mTotalCount, leavePercent, isLeftTopRight);
                    }
                }
            }
        }
//        滚动状态
        else {
            for (int i = 0; i < mTotalCount; i++) {
                if (i == mCurrentIndex) {
                    continue;
                }
                boolean mDeselected = mDeselectedItems.get(i);
                if (!mDeselected) {
                    mScrollListener.onDisSelected(i, mTotalCount);
                }
                Float mLeavePercent = mLeavePercents.get(i, 0.0f);
                if (mLeavePercent != 0.0f) {
                    mScrollListener.onLeave(i, mTotalCount, 1.0f, isLeftTopRight);
                    mLeavePercents.put(i, 1.0f);
                }
            }
            mScrollListener.onEnter(mCurrentIndex, mTotalCount, 1.0f, false);
            mLeavePercents.put(mCurrentIndex, 0.0f);
            mScrollListener.onSelected(mCurrentIndex, mTotalCount);
            mDeselectedItems.put(mCurrentIndex, false);
        }
        mLastPositionOffsetSum = mCurrentPositionOffsetSum;
    }

    private int getSafeIndex(int mPosition) {

        return Math.max(Math.min(mPosition, mTotalCount - 1), 0);
    }

    public void onPageSelected(int position) {
        int currentIndex = setCurrentindex(position);
        if (mScrollListener != null) {
            mScrollListener.onSelected(mCurrentIndex, mTotalCount);
            mDeselectedItems.put(mCurrentIndex, true);
            for (int i = 0; i < mTotalCount
                    ; i++) {
                if (i == mCurrentIndex) {
                    continue;
                }
                boolean disSelected = mDeselectedItems.get(i);
                if (!disSelected) {
                    mScrollListener.onSelected(i, mTotalCount);
                    mDeselectedItems.put(i, true);
                }
            }
        }
    }

    public void onPageScrollStatedChange(int scrollstate) {
        mScrollState = scrollstate;
    }

    public void setTotalCount(int mTotalCount) {
        this.mTotalCount = mTotalCount;
    }

    private int setCurrentindex(int index) {
        mLastIndex = mCurrentIndex;
        mCurrentIndex = getSafeIndex(index);
        return mCurrentIndex;
    }

    public int getScrollState() {
        return mScrollState;
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }
}
