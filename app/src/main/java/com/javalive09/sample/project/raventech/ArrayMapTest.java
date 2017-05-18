package com.javalive09.sample.project.raventech;

import android.support.v4.util.ArrayMap;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

/**
 * Created by peter on 2017/3/31.
 */

public class ArrayMapTest extends Entry {

    ArrayMap<String, String> map;

    public void test() {
        map = new ArrayMap<>();
        map.put("123", "abc");
        map.put("456", "def");
        map.put("789", "ghj");
        map.put("123", "xxx");

        Log.i(map.toString());
    }

}
