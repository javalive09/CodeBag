package com.javalive09.demos;

import android.content.pm.PackageInfo;

import com.javalive09.codebag.Play;
import com.javalive09.codebag.CaseActivity;


public class PackageManager {

    @Play(name = "应用版本versionName")
    public void appVersionName() {
        android.content.pm.PackageManager manager = CaseActivity.context().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(CaseActivity.context().getPackageName(), 0);
            CaseActivity.showText(info.versionName);
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
