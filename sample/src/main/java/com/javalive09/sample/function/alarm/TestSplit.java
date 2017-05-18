package com.javalive09.sample.function.alarm;

import com.javalive09.codebag.Entry;

/**
 * Created by peter on 2017/3/14.
 */

public class TestSplit extends Entry {

    public void show() {
        String str = "33 19 * * *";
        String [] strings = str.split(" ");
        showTxt(strings[0] + strings[1] + strings[2] + strings[3] + strings[4]);
    }

}
