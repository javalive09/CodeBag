package com.javalive09.demos;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by peter on 2019-08-23
 */
public class AtomicTest {

    @Run
    public void booleanTestTrue(CodeActivity codeActivity) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);

        if(atomicBoolean.compareAndSet(true, false)) {
            codeActivity.showText("true - " + atomicBoolean.get());
        }else {
            codeActivity.showText("false");
        }
    }

    @Run
    public void booleanTestFalse(CodeActivity codeActivity) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        if(atomicBoolean.compareAndSet(true, false)) {
            codeActivity.showText("true");
        }else {
            codeActivity.showText("false - " + atomicBoolean.get());
        }

        WeakHashMap<Integer, String> map = new WeakHashMap<>();

    }

}
