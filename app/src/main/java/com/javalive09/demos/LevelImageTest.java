package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.ImageView;

/**
 * Created by peter on 2018/11/22
 */
public class LevelImageTest {

    @Run
    public void show0(CodeActivity codeActivity) {
        int[] STATE_SECURED = {
                R.attr.state_encrypted
        };

        ImageView imageView = new ImageView(codeActivity);

        StateListDrawable wifiSld = (StateListDrawable) codeActivity.getResources().getDrawable(R.drawable
                .wifi_signal, null);

        wifiSld.setState(STATE_SECURED);

        Drawable drawable = wifiSld.getCurrent();
//        drawable.setLevel(0);
        imageView.setImageDrawable(drawable);
        imageView.setImageLevel(0);

        codeActivity.setContentView(imageView);
    }

    @Run
    public void show1(CodeActivity codeActivity) {
        int[] STATE_SECURED = {
                R.attr.state_encrypted
        };
        ImageView imageView = new ImageView(codeActivity);

        StateListDrawable wifiSld = (StateListDrawable) codeActivity.getResources().getDrawable(R.drawable
                .wifi_signal, null);

        wifiSld.setState(STATE_SECURED);
        Drawable drawable = wifiSld.getCurrent();
//        drawable.setLevel(1);
        imageView.setImageDrawable(drawable);
        imageView.setImageLevel(1);


        codeActivity.setContentView(imageView);
    }

    @Run
    public void show2(CodeActivity codeActivity) {
        int[] STATE_SECURED = {
                R.attr.state_encrypted
        };
        ImageView imageView = new ImageView(codeActivity);

        StateListDrawable wifiSld = (StateListDrawable) codeActivity.getResources().getDrawable(R.drawable
                .wifi_signal, null);

        wifiSld.setState(STATE_SECURED);
        Drawable drawable = wifiSld.getCurrent();
//        drawable.setLevel(2);
        imageView.setImageDrawable(drawable);
        imageView.setImageLevel(2);

        codeActivity.setContentView(imageView);
    }

    @Run
    public void show3(CodeActivity codeActivity) {
        int[] STATE_SECURED = {
                R.attr.state_encrypted
        };
        ImageView imageView = new ImageView(codeActivity);

        StateListDrawable wifiSld = (StateListDrawable) codeActivity.getResources().getDrawable(R.drawable
                .wifi_signal, null);
        wifiSld.setState(STATE_SECURED);
        Drawable drawable = wifiSld.getCurrent();
//        drawable.setLevel(3);
        imageView.setImageDrawable(drawable);
        imageView.setImageLevel(3);

        codeActivity.setContentView(imageView);
    }

    @Run
    public void show4(CodeActivity codeActivity) {
        int[] STATE_SECURED = {
                R.attr.state_encrypted
        };
        ImageView imageView = new ImageView(codeActivity);

        StateListDrawable wifiSld = (StateListDrawable) codeActivity.getResources().getDrawable(R.drawable
                .wifi_signal, null);
        wifiSld.setState(new int[]{});
        Drawable drawable = wifiSld.getCurrent();
//        drawable.setLevel(4);
        imageView.setImageDrawable(drawable);

        imageView.setImageLevel(4);


        codeActivity.setContentView(imageView);
    }

    @Run
    public void show5(CodeActivity codeActivity) {
        int[] STATE_SECURED = {
                R.attr.state_encrypted
        };
        ImageView imageView = new ImageView(codeActivity);

        StateListDrawable wifiSld = (StateListDrawable) codeActivity.getResources().getDrawable(R.drawable
                .wifi_signal, null);
        wifiSld.setState(new int[]{});
        Drawable drawable = wifiSld.getCurrent();
        //        drawable.setLevel(4);
        imageView.setImageDrawable(drawable);

        imageView.setImageLevel(0);


        codeActivity.setContentView(imageView);
    }

}
