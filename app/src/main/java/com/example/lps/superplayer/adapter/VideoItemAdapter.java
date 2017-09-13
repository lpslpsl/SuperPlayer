package com.example.lps.superplayer.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.model.souhu.Video;

import java.util.ArrayList;

/**
 * Created by lps on 2017/9/12.
 *
 * @version 1
 * @see
 * @since 2017/9/12 10:26
 */


public class VideoItemAdapter extends BaseAdapter {

    ArrayList<Video> mVideos = new ArrayList<>();
    private Context mContext;
    private int mTotal;
    private OnVideoSelectedListener mListener;
    boolean showTitleContent;


    public VideoItemAdapter(Context mContext, int total, OnVideoSelectedListener mListener) {
        this.mContext = mContext;
        mTotal = total;
        this.mListener = mListener;
    }

    public boolean isShowTitleContent() {
        return showTitleContent;
    }

    public void setShowTitleContent(boolean mShowTitleContent) {
        showTitleContent = mShowTitleContent;
    }

    @Override
    public int getCount() {
        return mVideos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (isShowTitleContent()) {
            if (!TextUtils.isEmpty(mVideos.get(position).getVideoName())) {
                holder.mVideoItem.setText(mVideos.get(position).getVideoName());
            } else {
                holder.mVideoItem.setText(position + 1 + "");
            }
        } else {
            holder.mVideoItem.setText(position + 1 + "");

        }
        holder.mVideoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onVideoSelected(mVideos.get(position), position);
                }
            }
        });
        return convertView;
    }

    public void addData(Video mVideo) {
        mVideos.add(mVideo);

    }

    public interface OnVideoSelectedListener {
        void onVideoSelected(Video mVideo, int position);
    }

    static class ViewHolder {
        View view;
        Button mVideoItem;

        ViewHolder(View view) {
            this.view = view;
            this.mVideoItem = (Button) view.findViewById(R.id.video_item);
        }
    }
}
