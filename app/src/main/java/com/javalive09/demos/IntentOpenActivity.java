package com.javalive09.demos;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

/**
 * Intent action 相关测试
 */

@Code(name = "Intent Action")
public class IntentOpenActivity {

    @Run(name = "打开设置")
    public void setting(CodeActivity activity) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.SubSettings");
        intent.setComponent(componentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Run(name = "打开GPS")
    public void gps(CodeActivity activity) {
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivity(intent);
    }

    @Run(name = "打开辅助功能")
    public void accessibility(CodeActivity activity) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        activity.startActivity(intent);
    }

    @Run(name = "打开账号")
    public void addAccount(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_ADD_ACCOUNT));
    }

    @Run(name = "打开APN")
    public void apn(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_APN_SETTINGS));
    }

    @Run(name = "打开应用详情")
    public void applicationDetails(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS));
    }

    @Run
    public void captioning(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_CAPTIONING_SETTINGS));
    }

    @Run(name = "打开Home设置")
    public void homeSetting(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_HOME_SETTINGS));
    }

    @Run(name = "打开搜索设置")
    public void searchSetting(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_SEARCH_SETTINGS));
    }

    @Run(name = "打开字典设置")
    public void directionarySetting(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS));
    }

    @Run(name = "打开WIFI设置")
    public void wifiSetting(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    @Run(name = "打开浏览器")
    public void openBrowser(CodeActivity activity) {
        Uri uri = Uri.parse("https://www.baidu.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }

    /**
     * 跨应用调用其他activity的两种方式: componentName, action
     * <p>
     * 注意：被调用组件必须，android:exported="true"
     */
    @Run(name = "打开其他app的activity1")
    public void openOtherAppActivity(CodeActivity activity) {
        Intent intent = new Intent();
        ComponentName component = new ComponentName("com.peter.appmanager", "com.peter.appmanager.SettingActivity");
        intent.setComponent(component);
        activity.startActivity(intent);
    }

    @Run(name = "打开其他app的activity2")
    public void openOtherAppActivity2(CodeActivity activity) {
        Intent intent = new Intent();
        intent.setAction("com.peter.foo");
        activity.startActivity(intent);
    }

    /**
     * 跨应用调用其他程序的service
     */
    @Run(name = "跨应用调用其他程序的service")
    public void openOtherAppService(CodeActivity activity) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.lockscreen", "com.lockscreen.LockService");
        intent.setComponent(componentName);
        activity.startService(intent);
    }

    /**
     * 去市场下载页面
     */
    @Run(name = "去市场下载页面")
    public void openMarket(CodeActivity activity) {
        String packageName = activity.getPackageName();
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启下载explorer
     */
    @Run(name = "开启下载explorer")
    public void openDownloadExplore(CodeActivity activity) {
        Intent intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
