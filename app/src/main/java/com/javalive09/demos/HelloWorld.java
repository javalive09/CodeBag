package com.javalive09.demos;

import android.content.Intent;
import android.widget.TextView;

import com.javalive09.codebag.CodeBag;
import com.javalive09.codebag.Test;

/**
 * a sample for entry
 * <p>
 * Created by peter on 16/9/21.
 */

public class HelloWorld {

    @Test
    public void showView() {
        TextView textView = new TextView(CodeBag.context());
        textView.setText("hello world!!");
        CodeBag.showView(textView);
    }

    @Test
    public void showText() {
        CodeBag.showText("hello world!!");
    }

    @Test(name = "显示3.5秒toast")
    public void showToastLong() {
        CodeBag.context().toastLong("hello world !!");
    }

    @Test(name = "显示2秒toast")
    public void showToastShort() {
        CodeBag.context().toastShort("hello world !!");
    }

    @Test(name = "启动其他activity")
    public void startActivity() {
        Intent intent = new Intent(CodeBag.context(), CodeBag.class);
        CodeBag.context().startActivity(intent);
    }

}
