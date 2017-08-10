package com.example.lps.superplayer.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
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
        ObjectAnimator mAnimatorAlpha = ObjectAnimator.ofFloat(mRootview, "alpha", 0.1f, 1.0f);
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(mRootview, "scaleX", 0.7f, 1.2f);
        ObjectAnimator mAnimatorScaleY= ObjectAnimator.ofFloat(mRootview, "scaleY", 0.7f, 1.2f);
        AnimatorSet mAnimator=new AnimatorSet();
        mAnimator.play(mAnimatorAlpha).with(mAnimatorScaleX).with(mAnimatorScaleY);
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
