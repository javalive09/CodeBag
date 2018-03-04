package com.javalive09.demos;

import com.javalive09.codebag.CaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * main activity
 *
 * Created by peter on 2018/3/4.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CaseActivity.Launch(this);

        finish();
    }
}
