package com.example.lps.superplayer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.adapter.VideoItemAdapter;
import com.example.lps.superplayer.api.ApiCallBack;
import com.example.lps.superplayer.api.SiteApi;
import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.souhu.Video;
import com.example.lps.superplayer.weight.CustomGridView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumPlayGridFragment extends Fragment implements VideoItemAdapter.OnVideoSelectedListener {
    public static final String ALBUM = "album";
    public static final String IS_SHOW_DESC = "isshowdesc";
    public static final String VIDEO_POSITION = "videoposition";
    private static final String TAG = "AlbumPlayGridFragment";
    private View view;
    private CustomGridView mGridview;

    /**
     * 初始化
     *
     * @param mAlbum            信息
     * @param isShowdesc        是否显示详情
     * @param initvideoposition 初始化选择的剧集
     * @return
     */
    public static AlbumPlayGridFragment newInstance(Album mAlbum, boolean isShowdesc, int initvideoposition) {

        Bundle args = new Bundle();
        args.putSerializable(ALBUM, mAlbum);
        args.putBoolean(IS_SHOW_DESC, isShowdesc);
        args.putInt(VIDEO_POSITION, initvideoposition);

        AlbumPlayGridFragment fragment = new AlbumPlayGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Album mAlbum;
    boolean isSHowDesc;
    int video_position;
int pagetotal;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAlbum = (Album) getArguments().getSerializable(ALBUM);
            isSHowDesc = getArguments().getBoolean(IS_SHOW_DESC);
            video_position = getArguments().getInt(VIDEO_POSITION);
            mAdapter = new VideoItemAdapter(getActivity(), mAlbum.getVideoTotal(), this);
            mAdapter.setShowTitleContent(isSHowDesc);
            pagetotal= (int) Math.ceil(mAlbum.getVideoTotal()/50);
            loadData();
        }
    }

    int pagenum = 0;

    private void loadData() {
        if (pagenum<=pagetotal) {
            pagenum++;
            SiteApi.ongetVideo(pagenum, mAlbum, new ApiCallBack<ArrayList<Video>>() {

                @Override
                public void onsuccess(ArrayList<Video> data) {
                    for (Video video : data) {
                        Log.e(TAG, "onsuccess: " + video.toString());
                        mAdapter.addData(video);
                    }
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(Exception mE) {

                }
            });
        }else {
            if (mGridview!=null)
            mGridview.setHasMore(false);
        }
    }

    VideoItemAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_album_play_grid, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {

        mGridview = (CustomGridView) root.findViewById(R.id.gridview);
//        isSHowDesc表示同样是剧集。但是综艺是期，而电视是数字
        mGridview.setNumColumns(isSHowDesc ? 1 : 6);
        mGridview.setAdapter(mAdapter);
        if (mAlbum.getVideoTotal() > 0 && mAlbum.getVideoTotal() > 50) {
            mGridview.setHasMore(true);
        } else {
            mGridview.setHasMore(false);
        }
        mGridview.setOnLoadMoreListener(new CustomGridView.OnLoadMoreListener() {
            @Override
            public void onLoadMoreItems() {
                loadData();
            }
        });
    }

    private void initVIew() {
        mAdapter = new VideoItemAdapter(getActivity(), mAlbum.getVideoTotal(), this);
    }

    @Override
    public void onVideoSelected() {

    }
}
