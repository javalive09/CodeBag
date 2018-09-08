package com.javalive09.demos;

import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;

/**
 * a sample for entry
 * <p>
 * Created by peter on 16/9/21.
 */

public class HelloWorld {

    @Run
    public void log(CodeActivity codeActivity) {
        Log.e("HelloWorld", "log");
    }

    @Run
    public void showView(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.shape);
    }

    @Run
    public void showText(CodeActivity codeActivity) {
        codeActivity.showText("hello world!!");
    }


    @Run(name = "启动其他activity")
    public void startActivity(CodeActivity activity) {
        activity.startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
    }

}
