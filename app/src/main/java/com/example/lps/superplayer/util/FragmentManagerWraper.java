package com.example.lps.superplayer.util;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by lps on 2017/8/10.
 *
 * @version 1
 * @see
 * @since 2017/8/10 14:43
 */


public class FragmentManagerWraper {
    private FragmentManagerWraper() {
    }

    private volatile static FragmentManagerWraper mInstance=null;
    public static FragmentManagerWraper getInstance(){
        if (mInstance==null){
            synchronized (FragmentManagerWraper.class){
                if (mInstance==null){
                    mInstance=new FragmentManagerWraper();
                }
            }
        }
        return mInstance;
    }

    HashMap<String,Fragment> mHashMap=new HashMap<>();

    public Fragment createFragment(Class clazz){
        Fragment  mFragment=null;
        String clazzName = clazz.getName();
        if (mHashMap.containsKey(clazzName)){
            mFragment=mHashMap.get(clazzName);
        }else {
            try {
                mFragment= (Fragment) Class.forName(clazzName).newInstance();
                mHashMap.put(clazzName,mFragment);
            } catch (InstantiationException mE) {
                mE.printStackTrace();
            } catch (IllegalAccessException mE) {
                mE.printStackTrace();
            } catch (ClassNotFoundException mE) {
                mE.printStackTrace();
            }
        }

        return mFragment;
    }
}
