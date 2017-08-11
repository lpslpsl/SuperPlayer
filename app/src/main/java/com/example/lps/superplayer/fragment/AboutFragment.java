package com.example.lps.superplayer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lps.superplayer.R;
import com.example.lps.superplayer.util.SystemUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    /** V1.0.0 */
    private TextView mTvVersion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview= inflater.inflate(R.layout.fragment_about, container, false);
        initview(rootview);
        return rootview;
    }

    private void initview(View rootView) {
        mTvVersion= (TextView) rootView.findViewById(R.id.tv_version);
        mTvVersion.setText("Version\t"+SystemUtil.getAppVersion(getActivity()));
    }

}
