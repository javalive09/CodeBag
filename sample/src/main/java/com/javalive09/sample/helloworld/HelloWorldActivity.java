package com.javalive09.sample.helloworld;

import android.app.Activity;
import android.os.Bundle;

import com.javalive09.sample.R;

/**
 * Created by peter on 16/9/22.
 */

public class HelloWorldActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helloworld_activity_layout);
    }
}
