package com.example.lps.superplayer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lps.commonlib.listener.PullloadListener;
import com.example.lps.commonlib.weight.PullloadRecyclerView;
import com.example.lps.superplayer.R;
import com.example.lps.superplayer.activity.AblumDetailActivity;
import com.example.lps.superplayer.adapter.Detailadapter;
import com.example.lps.superplayer.api.ApiCallBack;
import com.example.lps.superplayer.api.SiteApi;
import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.Channel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListFragment extends Fragment {
    public static final String CHANNEL = "channel";


    @BindView(R.id.recyclerview)
    PullloadRecyclerView mRecyclerView;
    private View view;
    private Unbinder unbinder;
    SiteApi mSiteApi;
    int page = 1;
    int msiteId;

    Channel mchannel;
    public static final String SITE_ID = "site_id";
    Detailadapter mAdapter;
    List<Album> mAlbumList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detail_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initview();
        initdata();
        return view;
    }

    private void initview() {
        mAlbumList = new ArrayList<>();
        mRecyclerView.setGridLayout(3);
        mAdapter = new Detailadapter(mAlbumList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setPullloadListener(new PullloadListener() {
            @Override
            public void refresh() {
                mAlbumList.clear();
            }

            @Override
            public void loadmore() {
                page++;
                loadNetWorkData(page);
            }
        });
      mAdapter.setListener(new Detailadapter.OnItemClickListener() {
          @Override
          public void click(int position) {
//              纪录片，电影，综艺，音乐等作为一种（他们没有分集的）
              if (mchannel.getChannelId()==Channel.DOCUMENTRY
                      ||mchannel.getChannelId()==Channel.MOVIE
                      ||mchannel.getChannelId()==Channel.VARIETY
                      ||mchannel.getChannelId()==Channel.MUSIC){
                  AblumDetailActivity.launch(getActivity(),mAlbumList.get(position),0,true);
              }
//              有分集的作为一类。传递的参数不太一样
              else if (mchannel.getChannelId()==Channel.COMIC
                      ||mchannel.getChannelId()==Channel.SHOW){
                  AblumDetailActivity.launch(getActivity(),mAlbumList.get(position));
              }
          }
      });
    }

    private static final String TAG = "DetailListFragment";

    private void initdata() {

        mchannel = getArguments().getParcelable(CHANNEL);
        msiteId = getArguments().getInt(SITE_ID);
        mSiteApi = new SiteApi();
        loadNetWorkData(page);
        Log.e(TAG, "initdata: " + mchannel);
    }

    private void loadNetWorkData(int mPage) {
        mSiteApi.onGetChannelAlbums(msiteId, mchannel, mPage, new ApiCallBack<List<Album>>() {


            @Override
            public void onsuccess(List<Album> data) {
                mAlbumList.addAll(data);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(Exception mE) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
