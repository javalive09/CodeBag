package com.javalive09.sample.function.autocontroll;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.SystemClock;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.utils.ShellUtil;

import java.util.ArrayList;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by peter on 2017/6/5.
 */

public class PlayAQIY extends Entry {

    public void play() {
        Observable.just(1).subscribeOn(Schedulers.newThread())
                .flatMap(integer -> {
                    launchapp(getActivity(), "com.qiyi.video.pad");//open
                    return Observable.just(1);
                })
                .flatMap(integer -> {
                    SystemClock.sleep(2000);
                    txt("欢乐颂2");
                    return Observable.just(1);
                });
    }

    private void key(int key) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("keyevent");
            commands.add(key + "");
            ShellUtil.cmd(commands);
        });
    }

    private void txt(String content) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("text");
            commands.add(content);
            ShellUtil.cmd(commands);
        });
    }


    public static void launchapp(Context context, String APP_PACKAGE_NAME) {
        // 判断是否安装过App，否则去市场下载
        if (isAppInstalled(context, APP_PACKAGE_NAME)) {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE_NAME));
        } else {
            goToMarket(context, APP_PACKAGE_NAME);
        }
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 去市场下载页面
     */
    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
        }
    }


}
