package com.javalive09.sample.project.raventech;

import com.javalive09.codebag.Entry;

import java.util.HashMap;

/**
 * Created by peter on 2016/12/28.
 */

public class HashMapTest extends Entry {

    public void test() {
        HashMap<String , Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("a", 3);
        showTxt(map.toString());
    }

}
