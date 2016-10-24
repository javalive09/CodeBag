package com.javalive09.sample.function.timer;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.javalive09.codebag.Entry;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Created by peter on 2016/10/19.
 */

public class TimerTest extends Entry {

    int count = 0;

    public void test() {
        final TextView textView = new TextView(getViewActivity());
        showView(textView);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count ++;
                getViewActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("start:" + count);
                    }
                });
            }
        },0, 2000);

    }
}
