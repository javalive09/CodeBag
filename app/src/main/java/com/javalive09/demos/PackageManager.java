package com.javalive09.demos;

import android.content.pm.PackageInfo;

import com.javalive09.codebag.CodeBag;
import com.javalive09.annotation.Test;

public class PackageManager {

    @Test(name = "应用版本versionName")
    public void appVersionName() {
        android.content.pm.PackageManager manager = CodeBag.context().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(CodeBag.context().getPackageName(), 0);
            CodeBag.showText(info.versionName);
        } catch (android.content.pm.PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
