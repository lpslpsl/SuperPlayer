package com.example.lps.superplayer.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lps on 2017/9/12.
 *
 * @version 1
 * @see
 * @since 2017/9/12 10:40
 */


public class CustomGridView extends GridView {
    private Context mContext;
    List<ViewHolder> mFooterList = new ArrayList<>();
    private boolean isLoading;
    private boolean hasMore;

    public CustomGridView(Context context) {
        super(context);

        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LoadingView mLoadingView = new LoadingView(mContext);

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
                if (totalItemCount > 0) {
                    int lastViewvisable = firstVisibleItem + visibleItemCount;
                    if (!isLoading && hasMore) {
                        if (mOnLoadMoreListener != null) mOnLoadMoreListener.onLoadMoreItems();
                    }
                }
            }
        });
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public void addFooterView(View view, Object data, boolean isSelected) {
        ViewHolder h = new ViewHolder();
        FUllWithViewLayout fl = new FUllWithViewLayout(mContext);
        fl.addView(view);
        h.mView = view;
        h.data = data;
        h.isseleced = isSelected;
        h.mViewGroup = fl;

        mFooterList.add(h);
    }

    public void addFooterView(View view) {
        addFooterView(view, null, true);
    }

    public void removeFooterView(View v) {
        if (mFooterList.size() > 0) {
            for (int i = 0; i < mFooterList.size(); i++) {
                if (v == mFooterList.get(i).mView) {
                    mFooterList.remove(mFooterList.get(i));
                }
            }
        }
    }

    class ViewHolder {
        public View mView;
        public ViewGroup mViewGroup;
        public Object data;
        public boolean isseleced;

    }

    class FUllWithViewLayout extends FrameLayout {
        public FUllWithViewLayout(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int targetWidth = this.getWidth() - getPaddingLeft() - getPaddingRight();
            MeasureSpec.makeMeasureSpec(targetWidth, MeasureSpec.getMode(widthMeasureSpec));
        }
    }

    OnLoadMoreListener mOnLoadMoreListener;

    public interface OnLoadMoreListener {
        void onLoadMoreItems();
    }

    onScrollListener mOnScrollListener;

    public interface onScrollListener {
        void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public boolean isLoading() {

        return isLoading;
    }

    public void setLoading(boolean mLoading) {
        isLoading = mLoading;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean mHasMore) {
        hasMore = mHasMore;
    }

    public OnLoadMoreListener getOnLoadMoreListener() {
        return mOnLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public onScrollListener getOnScrollListener() {
        return mOnScrollListener;
    }

    public void setOnScrollListener(onScrollListener mOnScrollListener) {
        this.mOnScrollListener = mOnScrollListener;
    }
}
