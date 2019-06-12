package com.javalive09.demos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by peter on 2019-06-10
 */
public class Random {

    @Run
    public void threadlocalRandom(CodeActivity codeActivity) {
        int i = ThreadLocalRandom.current().nextInt(3);
        codeActivity.showText(String.valueOf(i));
    }

    @Run
    public void threadlocalRandom2(CodeActivity codeActivity) {
        int i = ThreadLocalRandom.current().nextInt(3, 5);
        codeActivity.showText(String.valueOf(i));
    }

    public void observable() {

        Observable observable = new Observable(){
            @Override
            public synchronized boolean hasChanged() {
                return true;
            }
        };

        List<String> list = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};

        Collections.sort(list, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        Collections.sort(list, (o1, o2) -> Integer.compare(o1.length(), o2.length()));

        Collections.sort(list, Comparator.comparingInt(String::length));
    }

}
