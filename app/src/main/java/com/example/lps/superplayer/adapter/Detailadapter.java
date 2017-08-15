package com.example.lps.superplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lps.superplayer.R;

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
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, null);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
