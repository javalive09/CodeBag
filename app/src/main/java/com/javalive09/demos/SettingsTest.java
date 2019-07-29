package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.provider.Settings;

/**
 * Created by peter on 2019-07-24
 */
public class SettingsTest {

    @Run
    public void getglobal(CodeActivity codeActivity) {
        int i = Settings.Global.getInt(codeActivity.getContentResolver(), "dock_audio_media_enable", -1);
        codeActivity.showText(String.valueOf(i));
    }

    @Run
    public void putGlobal(CodeActivity codeActivity) {
        boolean b = Settings.Global.putInt(codeActivity.getContentResolver(), "dock_audio_media_enable", 1);
        codeActivity.showText(String.valueOf(b));
    }

    @Run
    public void getSystem(CodeActivity codeActivity) {
        int i = Settings.System.getInt(codeActivity.getContentResolver(), "volume_voice", -1);
        codeActivity.showText(String.valueOf(i));
    }

    @Run
    public void putSystem(CodeActivity codeActivity) {
        boolean b = Settings.System.putInt(codeActivity.getContentResolver(), "volume_voice", 30);
        codeActivity.showText(String.valueOf(b));
    }

    @Run
    public void getSecure(CodeActivity codeActivity) {
        int i = Settings.Secure.getInt(codeActivity.getContentResolver(), "long_press_timeout", -1);
        codeActivity.showText(String.valueOf(i));
    }

    @Run
    public void putSecure(CodeActivity codeActivity) {
        boolean b = Settings.Secure.putInt(codeActivity.getContentResolver(), "long_press_timeout", 500);
        codeActivity.showText(String.valueOf(b));
    }

}
