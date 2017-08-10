package com.example.lps.superplayer.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.lps.superplayer.MainActivity;
import com.example.lps.superplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.rootview)
    LinearLayout mRootview;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ObjectAnimator mAnimator = ObjectAnimator.ofFloat(mRootview, "alpha", 0.4f, 1.0f);
        mAnimator.setDuration(2000);
        mAnimator.start();
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

}
