package com.javalive09.sample.function.debug;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

/**
 * Created by peter on 2017/6/15.
 */

public class Condition extends Entry {

    public void debug() {
        for(int i = 0 ; i < 100; i++) {
            Log.i(i + "");
        }
    }

}
