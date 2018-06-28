package com.javalive09.demos;

import android.content.pm.PackageInfo;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;

public class PackageManager {

    @Run(name = "应用版本versionName")
    public void appVersionName(CodeActivity activity) {
        android.content.pm.PackageManager manager = activity.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            activity.showText(info.versionName);
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
