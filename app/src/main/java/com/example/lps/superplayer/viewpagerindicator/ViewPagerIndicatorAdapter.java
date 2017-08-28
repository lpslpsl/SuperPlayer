package com.example.lps.superplayer.viewpagerindicator;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

import com.example.lps.superplayer.viewpagerindicator.indicator.IPagerIndicatorView;
import com.example.lps.superplayer.viewpagerindicator.title.IPagerTitle;
import com.example.lps.superplayer.viewpagerindicator.title.IViewPagerTitleView;

/**
 * Created by lps on 2017/8/21.
 *
 * @version 1
 * @see
 * @since 2017/8/21 14:18
 */


public abstract class ViewPagerIndicatorAdapter {
 public abstract   int getCount();
   public abstract IPagerTitle getTitle(Context mContext,int index);
    public  abstract IPagerIndicatorView getIndicator(Context mContext);

    public float getTitleWeight(){return 1;}
    private final DataSetObservable  mDataSetObservable=new DataSetObservable();
    public final  void registerObservable(DataSetObserver mObserver){
        mDataSetObservable.registerObserver(mObserver);
    }

    public final void unRegisterObservable(DataSetObserver mObserver){
        mDataSetObservable.unregisterObserver(mObserver);
    }

    public final void notifySetDataChanged(){
        mDataSetObservable.notifyChanged();
    }
}
