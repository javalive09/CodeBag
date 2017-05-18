package com.javalive09.sample.function.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;

import com.javalive09.codebag.Entry;

/**
 * Created by peter on 2017/4/11.
 */

public class openSetting extends Entry {

    public void open() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.android.settings",
                "com.android.settings.SubSettings");
        intent.setComponent(componentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }

    public void open2() {
        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        getActivity().startActivity(callGPSSettingIntent);
    }

    public void ACTION_ACCESSIBILITY_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        getActivity().startActivity(intent);
    }

    public void ACTION_ADD_ACCOUNT() {
        Intent intent = new Intent(Settings.ACTION_ADD_ACCOUNT);
        getActivity().startActivity(intent);
    }

    public void ACTION_APN_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_APN_SETTINGS);
        getActivity().startActivity(intent);
    }

    public void ACTION_APPLICATION_DETAILS_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        getActivity().startActivity(intent);
    }

    public void ACTION_CAPTIONING_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_CAPTIONING_SETTINGS);
        getActivity().startActivity(intent);
    }

    public void ACTION_CAST_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_CAST_SETTINGS);
        getActivity().startActivity(intent);
    }
    public void ACTION_DREAM_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_DREAM_SETTINGS);
        getActivity().startActivity(intent);
    }
    public void ACTION_HOME_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_HOME_SETTINGS);
        getActivity().startActivity(intent);
    }
    public void ACTION_MANAGE_DEFAULT_APPS_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS);
        getActivity().startActivity(intent);
    }
    public void ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        getActivity().startActivity(intent);
    }
    public void ACTION_SEARCH_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_SEARCH_SETTINGS);
        getActivity().startActivity(intent);
    }
    public void ACTION_USER_DICTIONARY_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);
        getActivity().startActivity(intent);
    }

    public void ACTION_WEBVIEW_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_WEBVIEW_SETTINGS);
        getActivity().startActivity(intent);
    }
    public void ACTION_WIFI_SETTINGS() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        getActivity().startActivity(intent);
    }








}
