package com.example.lps.superplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.activity.DetailListActivity;
import com.example.lps.superplayer.model.Channel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lps on 2017/8/11.
 *
 * @version 1
 * @see
 * @since 2017/8/11 14:46
 */


public class HomeGridAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Channel> mChannelList;

    public HomeGridAdapter(Context mContext, List<Channel> mChannelList) {

        this.mContext = mContext;
        this.mChannelList = mChannelList;
    }

    @Override
    public int getCount() {
        return mChannelList.size();
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
        ViewHolder mHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_grid, null);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mChannelImage.setImageResource(mChannelList.get(position).getImgRes());
        mHolder.mTextChannelName.setText(mChannelList.get(position).getChanlName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 6:
                        // TODO: 2017/8/11 跳转到直播
                        break;
                    case 7:
                        // TODO: 2017/8/11 跳转到收藏
                        break;
                    case 8:
                        // TODO: 2017/8/11 跳转到观看记录
                        break;
                    default:
                        // TODO: 2017/8/11 跳转到对应分类页面
                        Intent mIntent=new Intent(mContext, DetailListActivity.class);
                        mIntent.putExtra(DetailListActivity.TITLE,mChannelList.get(position).getChanlName());
                        mContext.startActivity(mIntent);
                        break;
                }
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.channel_image)
        ImageView mChannelImage;
        @BindView(R.id.text_channel_name)
        TextView mTextChannelName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
