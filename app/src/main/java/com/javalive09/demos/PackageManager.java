package com.javalive09.demos;

import android.content.pm.PackageInfo;

import com.javalive09.codebag.Play;
import com.javalive09.codebag.PlayerActivity;


public class PackageManager {

    @Play(name = "应用版本versionName")
    public void appVersionName() {
        android.content.pm.PackageManager manager = PlayerActivity.context().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(PlayerActivity.context().getPackageName(), 0);
            PlayerActivity.context().showText(info.versionName);
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
