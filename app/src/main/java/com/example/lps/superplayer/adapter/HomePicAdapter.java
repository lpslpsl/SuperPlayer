package com.example.lps.superplayer.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lps.superplayer.R;

import java.util.ArrayList;

/**
 * Created by lps on 2017/8/11.
 *
 * @version 1
 * @see
 * @since 2017/8/11 10:21
 */


public class HomePicAdapter extends PagerAdapter {

    private Context mContext;
 private int[] mtv={
         R.string.a_name,
         R.string.b_name,
         R.string.c_name,
         R.string.d_name,
         R.string.e_name
 };

 private  int mImg[]={
         R.drawable.a,
         R.drawable.b,
         R.drawable.c,
         R.drawable.d,
         R.drawable.e
 };
    public HomePicAdapter(Context mContext) {
   this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return mtv.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View pagerView = LayoutInflater.from(mContext).inflate(R.layout.item_home_pic, null);
    ImageView conver= (ImageView) pagerView.findViewById(R.id.conver);
    TextView desc= (TextView) pagerView.findViewById(R.id.desc);
conver.setImageResource(mImg[position]);
        desc.setText(mtv[position]);
        container.addView(pagerView);
        return pagerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
container.removeView((View) object);
    }
}
