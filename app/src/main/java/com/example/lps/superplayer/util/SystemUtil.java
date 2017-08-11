package com.example.lps.superplayer.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by lps on 2017/8/11.
 *
 * @version 1
 * @see
 * @since 2017/8/11 10:02
 */


public class SystemUtil {
    public static String getAppVersion(Context mContext){
        try {
            PackageManager mPackageManager = mContext.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
            return mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException mE) {
            mE.printStackTrace();
        }
        return "";
    }
}
