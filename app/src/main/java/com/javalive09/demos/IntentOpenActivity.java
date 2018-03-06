package com.javalive09.demos;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.javalive09.codebag.CodeBag;
import com.javalive09.annotation.Tester;
import com.javalive09.annotation.Test;

/**
 * Intent action 相关测试
 */

@Tester(name = "Intent Action")
public class IntentOpenActivity {

    @Test(name = "打开设置")
    public void setting() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.SubSettings");
        intent.setComponent(componentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CodeBag.context().startActivity(intent);
    }

    @Test(name = "打开GPS")
    public void gps() {
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        CodeBag.context().startActivity(intent);
    }

    @Test(name = "打开辅助功能")
    public void accessibility() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        CodeBag.context().startActivity(intent);
    }

    @Test(name = "打开账号")
    public void addAccount() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_ADD_ACCOUNT));
    }

    @Test(name = "打开APN")
    public void apn() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_APN_SETTINGS));
    }

    @Test(name = "打开应用详情")
    public void applicationDetails() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS));
    }

    @Test
    public void captioning() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_CAPTIONING_SETTINGS));
    }

    @Test(name = "打开Home设置")
    public void homeSetting() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_HOME_SETTINGS));
    }

    @Test(name = "打开搜索设置")
    public void searchSetting() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_SEARCH_SETTINGS));
    }

    @Test(name = "打开字典设置")
    public void directionarySetting() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS));
    }

    @Test(name = "打开WIFI设置")
    public void wifiSetting() {
        CodeBag.context().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    @Test(name = "打开浏览器")
    public void openBrowser() {
        Uri uri = Uri.parse("https://www.baidu.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        CodeBag.context().startActivity(intent);
    }

    /**
     * 跨应用调用其他activity的两种方式: componentName, action
     * <p>
     * 注意：被调用组件必须，android:exported="true"
     */
    @Test(name = "打开其他app的activity1")
    public void openOtherAppActivity() {
        Intent intent = new Intent();
        ComponentName component = new ComponentName("com.peter.appmanager", "com.peter.appmanager.SettingActivity");
        intent.setComponent(component);
        CodeBag.context().startActivity(intent);
    }

    @Test(name = "打开其他app的activity2")
    public void openOtherAppActivity2() {
        Intent intent = new Intent();
        intent.setAction("com.peter.foo");
        CodeBag.context().startActivity(intent);
    }

    /**
     * 跨应用调用其他程序的service
     */
    @Test(name = "跨应用调用其他程序的service")
    public void openOtherAppService() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.lockscreen", "com.lockscreen.LockService");
        intent.setComponent(componentName);
        CodeBag.context().startService(intent);
    }

    /**
     * 去市场下载页面
     */
    @Test(name = "去市场下载页面")
    public void openMarket() {
        String packageName = CodeBag.context().getPackageName();
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            CodeBag.context().startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启下载explorer
     */
    @Test(name = "开启下载explorer")
    public void openDownloadExplore() {
        Intent intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CodeBag.context().startActivity(intent);
    }

}
