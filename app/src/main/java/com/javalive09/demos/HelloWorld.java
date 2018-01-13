package com.javalive09.demos;

import android.content.Intent;
import android.widget.TextView;

import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;

/**
 * a sample for entry
 * <p>
 * Created by peter on 16/9/21.
 */

public class HelloWorld {

    @Play
    public void showView() {
        TextView textView = new TextView(PlayerActivity.context());
        textView.setText("hello world!!");
        PlayerActivity.context().showView(textView);
    }

    @Play
    public void showText() {
        PlayerActivity.context().showText("hello world!!");
    }

    @Play(name = "显示3.5秒toast")
    public void showToastLong() {
        PlayerActivity.context().toastLong("hello world !!");
    }

    @Play(name = "显示2秒toast")
    public void showToastShort() {
        PlayerActivity.context().toastShort("hello world !!");
    }

    @Play(name = "启动其他activity")
    public void startActivity() {
        Intent intent = new Intent(PlayerActivity.context(), PlayerActivity.class);
        PlayerActivity.context().startActivity(intent);
    }

}
