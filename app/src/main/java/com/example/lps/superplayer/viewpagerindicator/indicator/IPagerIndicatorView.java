package com.example.lps.superplayer.viewpagerindicator.indicator;

import com.example.lps.superplayer.viewpagerindicator.IPagerChangeListener;
import com.example.lps.superplayer.viewpagerindicator.PositionData;

import java.util.List;

/**
 * Created by lps on 2017/8/17.
 *
 * @version 1
 * @see
 * @since 2017/8/17 14:37
 */


public interface IPagerIndicatorView extends IPagerChangeListener {
    void setPositionData(List<PositionData> list);

}
