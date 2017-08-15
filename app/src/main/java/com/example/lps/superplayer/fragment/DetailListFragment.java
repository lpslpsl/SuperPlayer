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
import com.example.lps.superplayer.adapter.Detailadapter;
import com.example.lps.superplayer.api.ApiCallBack;
import com.example.lps.superplayer.api.SiteApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListFragment extends Fragment {
public static final String CHANNEL="channel";
public static final String CATEGARY ="categary";

    @BindView(R.id.recyclerview)
    PullloadRecyclerView mRecyclerView;
    private View view;
    private Unbinder unbinder;
    SiteApi mSiteApi;
    int page=1;
    String mchannel;
    String mcategray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_detail_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initview();
        initdata();
        return view;
    }

    private void initview() {
        mRecyclerView.setGridLayout(3);
        mRecyclerView.setAdapter(new Detailadapter());
        mRecyclerView.setPullloadListener(new PullloadListener() {
            @Override
            public void refresh() {

            }

            @Override
            public void loadmore() {

            }
        });
    }

    private static final String TAG = "DetailListFragment";
    private void initdata() {

        mchannel = getArguments().getString(CHANNEL);
        mcategray = getArguments().getString(CATEGARY);
        mSiteApi = new SiteApi();
        loadNetWorkData();
        Log.e(TAG, "initdata: "+mchannel );
        Log.e(TAG, "initdata: "+mcategray );
    }

    private void loadNetWorkData( ) {
        mSiteApi.onGetChannelAlbums(mchannel, page, new ApiCallBack() {
            @Override
            public void onsuccess() {

            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
