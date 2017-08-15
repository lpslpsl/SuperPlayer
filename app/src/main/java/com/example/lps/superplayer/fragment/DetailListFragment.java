package com.example.lps.superplayer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lps.commonlib.listener.PullloadListener;
import com.example.lps.commonlib.weight.PullloadRecyclerView;
import com.example.lps.superplayer.R;
import com.example.lps.superplayer.adapter.Detailadapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListFragment extends Fragment {
public static final String CHANNEL="channel";
public static final String TITLE="title";

    @BindView(R.id.recyclerview)
    PullloadRecyclerView mRecyclerView;
    private View view;
    private Unbinder unbinder;

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
        String mchannel = getArguments().getString(CHANNEL);
        Log.e(TAG, "initdata: "+mchannel );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
