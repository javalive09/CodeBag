package com.javalive09.sample;

import android.app.Activity;
import android.os.Bundle;

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
