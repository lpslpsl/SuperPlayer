package com.example.lps.superplayer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.lps.superplayer.activity.BaseActivity;
import com.example.lps.superplayer.fragment.AboutFragment;
import com.example.lps.superplayer.fragment.BlogFragment;
import com.example.lps.superplayer.fragment.HomeFragment;
import com.example.lps.superplayer.util.FragmentManagerWraper;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_main_content)
    FrameLayout mFlMainContent;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;

    FragmentManager mFragmentManager;
    MenuItem mPreMenu;
    Fragment mCurrentFragment;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_drawer_home);
        mToolbar.setTitle("首页");
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerlayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerlayout.addDrawerListener(mActionBarDrawerToggle);

initFragment();
        mPreMenu=mNavigationView.getMenu().getItem(0);
        mPreMenu.setChecked(true);
        handleNavigationView();
    }

    private void handleNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (mPreMenu!=null)mPreMenu.setChecked(false);
                switch (item.getItemId()){
                 case    R.id.navigation_item_video:
                    switchFragment(HomeFragment.class);
                     mToolbar.setTitle("视频");
                    break;
                 case    R.id.navigation_item_blog:
                     switchFragment(BlogFragment.class);
                     mToolbar.setTitle("博客");
                    break;
               case      R.id.navigation_item_about:
                   switchFragment(AboutFragment.class);
                   mToolbar.setTitle("关于");

                    break;
                }
                mPreMenu=item;
                mDrawerlayout.closeDrawer(Gravity.LEFT);
                item.setChecked(true);
                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        Fragment mFragment = FragmentManagerWraper.getInstance().createFragment(clazz);
        if (mFragment.isAdded()){
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(mFragment).commitAllowingStateLoss();
        }else {
         mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.fl_main_content,mFragment).commitAllowingStateLoss();
        }
        mCurrentFragment=mFragment;
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment= FragmentManagerWraper.getInstance().createFragment(HomeFragment.class);
        mFragmentManager.beginTransaction().add(R.id.fl_main_content,mCurrentFragment).commitAllowingStateLoss();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
