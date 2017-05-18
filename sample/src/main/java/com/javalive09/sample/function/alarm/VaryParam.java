package com.javalive09.sample.function.alarm;

import com.javalive09.codebag.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peter on 2017/3/14.
 */

public class VaryParam extends Entry {

    public void show() {
        String[] strings = new String[] {"1", "2", "3"};
        action(1, strings);
    }

    private void action(int a, String... str) {

        List<String> strings = Arrays.asList(str);

    }

}
