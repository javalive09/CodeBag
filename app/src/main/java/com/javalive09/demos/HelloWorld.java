package com.javalive09.demos;

import android.content.Intent;
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
    public void showView(CodeActivity activity) {
        TextView textView = new TextView(activity);
        textView.setText("hello world!!");
        activity.setContentView(textView);
    }

    @Run
    public void showText(CodeActivity activity) {
        activity.showText("hello world!!");
    }

    @Run(name = "显示3.5秒toast")
    public void showToastLong(CodeActivity activity) {
        activity.toastLong("hello world !!");
    }

    @Run(name = "显示2秒toast")
    public void showToastShort(CodeActivity activity) {
        activity.toastShort("hello world !!");
    }

    @Run(name = "启动其他activity")
    public void startActivity(CodeActivity activity) {
        Intent intent = new Intent(activity, CodeActivity.class);
        activity.startActivity(intent);
    }

}
