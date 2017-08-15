package com.example.lps.commonlib.weight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.example.lps.commonlib.R;
import com.example.lps.commonlib.listener.PullloadListener;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 11:01
 */


public class PullloadRecyclerView extends LinearLayout {
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private View mFooterView;
    private boolean isRefresh;
    private boolean isLoadMore;
    public static final int REFRESH_TIME = 4000;


    private void initview() {
        View mInflate = LayoutInflater.from(mContext).inflate(R.layout.pull_load_layout, null);
        mRecyclerView = (RecyclerView) mInflate.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mInflate.findViewById(R.id.swiprefreshlayout);
        mFooterView = mInflate.findViewById(R.id.footer_view);
        mSwipeRefreshLayout.setOnRefreshListener(new RefreshListener());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        正在刷新或者加载更多。不让滑动
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isRefresh || isLoadMore;
            }
        });
        mRecyclerView.setScrollbarFadingEnabled(true);

        mFooterView.setVisibility(GONE);
        addView(mInflate);

        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener());
    }

    public PullloadRecyclerView(Context context) {
        super(context);
        mContext = context;
        initview();

    }

    public PullloadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initview();

    }

    public PullloadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initview();

    }

    PullloadListener mPullloadListener;

    public void setPullloadListener(PullloadListener mPullloadListener) {
        this.mPullloadListener = mPullloadListener;
    }

    /**
     * 设置GridLayoutmanager
     * @param spanCount
     */
    public void setGridLayout(int spanCount){
      mRecyclerView.setLayoutManager(  new GridLayoutManager(mContext,spanCount));
    }

    public void setLinearLayou(){
        new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
    }
    public void setLinearLayou(int  orientation){
        new LinearLayoutManager(mContext,orientation,false);
    }

    /**
     * 刷新
     */
    private void refreshData() {
        // TODO: 2017/8/15
        isRefresh=true;
        if (mPullloadListener != null) {
            mPullloadListener.refresh();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopRefresh();
                }
            }, REFRESH_TIME);
        }
    }

    /**
     * 停止刷新
     */
    private void stopRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        isRefresh=false;
    }

    /**
     * 加载更多
     */
    private void loadMoreData() {
        // TODO: 2017/8/15
        isLoadMore = true;
        mSwipeRefreshLayout.setRefreshing(false);
        mFooterView.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mFooterView.setVisibility(VISIBLE);
                        if (mPullloadListener != null) {
                            mPullloadListener.loadmore();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    stopLoadMore();
                                }
                            }, 4000);}
                    }
                }).start();
        invalidate();
    }

    /**
     * 停止加载更多
     */
    private void stopLoadMore() {
        isLoadMore = false;
        mFooterView.animate().translationY(mFooterView.getHeight()).setDuration(300).start();
    }

    public void setAdapter(RecyclerView.Adapter mAdapter) {
        mRecyclerView.setAdapter(mAdapter);
    }

    class RefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if (!isRefresh) {

                refreshData();
            }
        }
    }

    class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int firstitem = 0;
            int lastitem = 0;
            RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
            int totalcount = mLayoutManager.getItemCount();
            if (mLayoutManager instanceof GridLayoutManager) {
                GridLayoutManager mGridLayoutManager = (GridLayoutManager) mLayoutManager;
                firstitem = mGridLayoutManager.findFirstCompletelyVisibleItemPosition();
                lastitem = mGridLayoutManager.findLastCompletelyVisibleItemPosition();
                if (firstitem == 0 || lastitem == RecyclerView.NO_POSITION) {
                    lastitem = mGridLayoutManager.findLastVisibleItemPosition();
                }
            }else if (mLayoutManager instanceof LinearLayoutManager){
                LinearLayoutManager mLinearLayoutManager= (LinearLayoutManager) mLayoutManager;
                firstitem=mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
                lastitem=mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (firstitem==0||lastitem==RecyclerView.NO_POSITION){
                    lastitem=mLinearLayoutManager.findLastVisibleItemPosition();
                }
            }else {
                StaggeredGridLayoutManager mStaggeredGridLayoutManager= (StaggeredGridLayoutManager) mLayoutManager;
                // since may lead to the final item has more than one StaggeredGridLayoutManager the particularity of the so here that is an array
                // this array into an array of position and then take the maximum value that is the last show the position value
                int[] lastPositions = new int[mStaggeredGridLayoutManager.getSpanCount()];
                mStaggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
                lastitem = findMax(lastPositions);
                firstitem = mStaggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0];
            }
            // TODO: 2017/8/15  其他类型manager处理
            if (mSwipeRefreshLayout.isEnabled()) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
            if (!isLoadMore &&
                    totalcount-1 == lastitem
                    && !isRefresh
                    && mSwipeRefreshLayout.isEnabled()
                    && (dx > 0 || dy > 0)) {
                loadMoreData();
            }
        }

    }

    private int findMax(int[] mLastPositions) {
        int result=mLastPositions[0];
        for (int temp:mLastPositions) {
            if (temp>result){
                result =temp;
            }
        }
        return result;
    }
}
