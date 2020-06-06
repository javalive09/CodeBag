package com.javalive09.demos;

import com.javalive09.codebag.CodeActivity;

/**
 * Created by zhangrui on 20-6-6
 */
public class UI {

    public static void show(CodeActivity activity, String... line) {
        StringBuilder stringBuffer = new StringBuilder();
        for(String str : line) {
            stringBuffer.append(str).append("\n");
        }
        activity.showText(stringBuffer.toString());
    }

    public static <T> void show(CodeActivity activity, T... t) {
        StringBuilder stringBuffer = new StringBuilder();
        for(T str : t) {
            stringBuffer.append(String.valueOf(str)).append("\n");
        }
        activity.showText(stringBuffer.toString());
    }

}
