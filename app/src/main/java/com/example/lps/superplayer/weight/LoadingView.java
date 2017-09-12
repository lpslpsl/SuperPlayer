package com.example.lps.superplayer.weight;

import android.content.Context;
import android.view.View;

import com.example.lps.superplayer.R;

/**
 * Created by lps on 2017/9/12.
 *
 * @version 1
 * @see
 * @since 2017/9/12 10:56
 */


public class LoadingView extends View {
    public LoadingView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.load_view_layout,null);
    }
}
