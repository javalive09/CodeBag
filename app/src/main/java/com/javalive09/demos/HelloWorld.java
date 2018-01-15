package com.javalive09.demos;

import android.content.Intent;
import android.widget.TextView;

import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Play;

/**
 * a sample for entry
 * <p>
 * Created by peter on 16/9/21.
 */

public class HelloWorld {

    @Play
    public void showView() {
        TextView textView = new TextView(CaseActivity.context());
        textView.setText("hello world!!");
        CaseActivity.showView(textView);
    }

    @Play
    public void showText() {
        CaseActivity.showText("hello world!!");
    }

    @Play(name = "显示3.5秒toast")
    public void showToastLong() {
        CaseActivity.context().toastLong("hello world !!");
    }

    @Play(name = "显示2秒toast")
    public void showToastShort() {
        CaseActivity.context().toastShort("hello world !!");
    }

    @Play(name = "启动其他activity")
    public void startActivity() {
        Intent intent = new Intent(CaseActivity.context(), CaseActivity.class);
        CaseActivity.context().startActivity(intent);
    }

}
