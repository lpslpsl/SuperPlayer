package com.example.lps.superplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lps.superplayer.R;
import com.example.lps.superplayer.model.Album;
import com.example.lps.superplayer.model.Site;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lps on 2017/8/15.
 *
 * @version 1
 * @see
 * @since 2017/8/15 10:31
 */


public class Detailadapter extends RecyclerView.Adapter<Detailadapter.ViewHolder> {
    List<Album> mAlbumList;
    private Context mContext;

    public Detailadapter(List<Album> mAlbumList) {
        this.mAlbumList = mAlbumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Album data = mAlbumList.get(position);
        holder.mTitle.setText(data.getTitle());
        Glide.with(mContext).load(data.getHorImgUrl()).into(holder.mImageView);
        holder.mSubtitle.setText(data.getAlbumDesc());
        if (data.getSite().getSiteId() == Site.SOHU) {
            holder.mUpdated.setText(data.getTip());
            holder.mUpdated.setVisibility(View.VISIBLE);
        } else {
            holder.mUpdated.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.click(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlbumList == null ? 0 : mAlbumList.size();
    }

    public void setData(List<Album> mData) {
        mAlbumList = mData;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.conver)
        ImageView mImageView;
        @BindView(R.id.subtitle)
        TextView mSubtitle;
        @BindView(R.id.updated)
        TextView mUpdated;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
OnItemClickListener mListener;

    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public  interface OnItemClickListener{
     void click(int position);
 }
}
