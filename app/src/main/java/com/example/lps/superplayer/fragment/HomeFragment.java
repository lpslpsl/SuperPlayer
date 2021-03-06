package com.example.lps.superplayer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.adapter.HomeGridAdapter;
import com.example.lps.superplayer.adapter.HomePicAdapter;
import com.example.lps.superplayer.model.Channel;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.loopviewpager)
    LoopViewPager mLoopviewpager;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.gridview)
    GridView mGridview;
    private View rootView;
    private Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

        mLoopviewpager.setAdapter(new HomePicAdapter(getActivity()));
        mLoopviewpager.setLooperPic(true);
        mIndicator.setViewPager(mLoopviewpager);

        mGridview.setAdapter(new HomeGridAdapter(getActivity(), Channel.getChannelList()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
