package com.example.lps.superplayer.viewpagerindicator.indicator;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.viewpagerindicator.PositionData;
import com.example.lps.superplayer.viewpagerindicator.ViewPagerIndicatorAdapter;
import com.example.lps.superplayer.viewpagerindicator.title.IPagerTitle;
import com.example.lps.superplayer.viewpagerindicator.title.IViewPagerTitleView;
import com.example.lps.superplayer.viewpagerindicator.title.ViewPagerTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lps on 2017/8/18.
 *
 * @version 1
 * @see
 * @since 2017/8/18 14:40
 */


public class ViewPagerIndicatorLayout extends FrameLayout implements IPagerIndicatorLayout, IPagerTitle {
    Context mContext;
    ViewPagerIndcatorHelper mViewPagerIndcatorHelper;
    HorizontalScrollView mHorizontalScrollView;
    LinearLayout mTitleContainer;
    LinearLayout mIndicatorContainer;
    ViewPagerIndicatorAdapter mAdapter;
    IPagerIndicatorView mIndicator;
    List<PositionData> mPositionDatas = new ArrayList<>();
    float mScrollPivotX = 0.5f;

    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            mViewPagerIndcatorHelper.setTotalCount(mAdapter.getCount());
            init();
        }

        @Override
        public void onInvalidated() {

        }
    };

    public ViewPagerIndicatorLayout(@NonNull Context context) {
        super(context);
        mContext = context;
        mViewPagerIndcatorHelper = new ViewPagerIndcatorHelper();
        mViewPagerIndcatorHelper.setScrollListener(this);

    }

    private void init() {

        removeAllViews();
        initView();

    }

    private void initView() {
        View mChildView = View.inflate(mContext, R.layout.pager_indicator_layout, this);
        mHorizontalScrollView = (HorizontalScrollView) mChildView.findViewById(R.id.horizontalscrollview);
        mIndicatorContainer = (LinearLayout) mChildView.findViewById(R.id.ll_indicator_contaier);
        mTitleContainer = (LinearLayout) mChildView.findViewById(R.id.title_indicator_contaier);
        for (int i = 0; i < mViewPagerIndcatorHelper.getTotalCount(); i++) {
            if (mAdapter != null) {
                IPagerTitle v = mAdapter.getTitle(mContext, i);
                if (v instanceof ViewPagerTitle) {
                    View view = (View) v;
                    LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    mTitleContainer.addView(view, mParams);
                }
            }
        }
        if (mAdapter != null) {
            mIndicator = mAdapter.getIndicator(mContext);
            if (mIndicator instanceof View) {
                LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                mIndicatorContainer.addView((View) mIndicator, mParams);
            }
        }
    }

    @Override
    public void onAttachCoolIndicatorLayout() {
        init();
    }

    @Override
    public void onDetachCoolIndicatorLayout() {

    }

    @Override
    public void onSelected(int index, int totalCount) {
        if (mAdapter != null) {
            if (mTitleContainer == null) return;
            View mChildAt = mTitleContainer.getChildAt(index);
            if (mChildAt instanceof IPagerTitle) {
                ((IPagerTitle) mChildAt).onSelected(index, totalCount);
            }
        }
    }

    @Override
    public void onDisSelected(int index, int totalCount) {
        if (mAdapter != null) {
            if (mTitleContainer == null) return;
            View mChildAt = mTitleContainer.getChildAt(index);
            if (mChildAt instanceof IPagerTitle) {
                ((IPagerTitle) mChildAt).onDisSelected(index, totalCount);
            }
        }
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean isLeftToRight) {
        if (mAdapter != null) {
            if (mTitleContainer == null) return;
            View mChildAt = mTitleContainer.getChildAt(index);
            if (mChildAt instanceof IPagerTitle) {
                ((IPagerTitle) mChildAt).onLeave(index, totalCount, leavePercent, isLeftToRight);
            }
        }
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean isLeftToRight) {
        if (mAdapter != null) {
            if (mTitleContainer == null) return;
            View mChildAt = mTitleContainer.getChildAt(index);
            if (mChildAt instanceof IPagerTitle) {
                ((IPagerTitle) mChildAt).onEnter(index, totalCount, enterPercent, isLeftToRight);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mAdapter != null) {
            preParePositionData();
            if (mIndicator != null) {
                mIndicator.setPositionData(mPositionDatas);
            }
            if (mViewPagerIndcatorHelper.getScrollState() == ViewPager.SCROLL_STATE_IDLE) {
                onPagerScrolled(mViewPagerIndcatorHelper.getCurrentIndex(), 0.0f, 0);
                onPagerSelected(mViewPagerIndcatorHelper.getCurrentIndex());
            }
        }
    }

    private void preParePositionData() {
        mPositionDatas.clear();
        for (int i = 0; i < mViewPagerIndcatorHelper.getTotalCount(); i++) {
            PositionData mData = new PositionData();
            View v = mTitleContainer.getChildAt(i);
            if (v != null) {
                mData.mLeft = v.getLeft();
                mData.mTop = v.getTop();
                mData.mRight = v.getRight();
                mData.mBottom = v.getBottom();
            }
            if (v instanceof IViewPagerTitleView) {
                mData.mContentLeft = ((IViewPagerTitleView) v).getContentLeft();
                mData.mContentTop = ((IViewPagerTitleView) v).getContentTop();
                mData.mContentRight = ((IViewPagerTitleView) v).getContentRight();
                mData.mContentBottom = ((IViewPagerTitleView) v).getContentBottom();
            } else {
                mData.mContentLeft = v.getLeft();
                mData.mContentTop = v.getTop();
                mData.mContentRight = v.getRight();
                mData.mContentBottom = v.getBottom();
            }
            mPositionDatas.add(mData);
        }
    }

    public void setAdapter(ViewPagerIndicatorAdapter adapter) {
        if (this.mAdapter == adapter) return;
        if (mAdapter != null) {
            mAdapter.unRegisterObservable(mDataSetObserver);
        }
        this.mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerObservable(mDataSetObserver);
            mAdapter.notifySetDataChanged();
        } else {
            mViewPagerIndcatorHelper.setTotalCount(0);
            init();
        }
    }

    @Override
    public void onPagerSelected(int position) {
        if (mAdapter != null) {
            mViewPagerIndcatorHelper.onPageSelected(position);
            if (mIndicator != null) {
                mIndicator.onPagerSelected(position);
            }
        }
    }

    @Override
    public void onPagerScrolled(int position, float positionOffsetPersent, int positionOffsetPixel) {
        if (mAdapter != null) {
            mViewPagerIndcatorHelper.onPageScrolled(position, positionOffsetPersent, positionOffsetPixel);
if (mIndicator!=null){
    mIndicator.onPagerScrolled(position,positionOffsetPersent,positionOffsetPixel);
}
        }
        if (mHorizontalScrollView != null && mPositionDatas.size() > 0) {
            int currentposition = Math.min(mPositionDatas.size() - 1, position);
            int nextPosition = Math.min(mPositionDatas.size() - 1, position + 1);
            PositionData current = mPositionDatas.get(currentposition);
            PositionData next = mPositionDatas.get(nextPosition);
            float scrollTo = current.horizonalCenter() - mHorizontalScrollView.getWidth() * mScrollPivotX;
            float nextscrollTo = next.horizonalCenter() - mHorizontalScrollView.getWidth() * mScrollPivotX;
            mHorizontalScrollView.scrollTo((int) (scrollTo + (nextscrollTo - scrollTo) * positionOffsetPersent), 0);
        }
    }

    @Override
    public void onPagerScrollStateChanaged(int state) {
        if (mAdapter != null) {
            mViewPagerIndcatorHelper.onPageScrollStatedChange(state);
            if (mIndicator != null) {
                mIndicator.onPagerScrollStateChanaged(state);
            }
        }

    }
}
