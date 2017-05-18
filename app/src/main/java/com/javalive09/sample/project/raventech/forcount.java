package com.javalive09.sample.project.raventech;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

/**
 * Created by peter on 2016/12/13.
 */

public class forcount extends Entry {

    public void action() {
        for(int i= 0; i < 1; i++) {
            Log.e(i + "");
        }
    }

    public void sqlite() {
        String a = "a";
        String[] s = a.split(",");

        Log.e(s.length + "");
    }

    public void subString() {
        String s = "abc,";

        String sa = s.substring(0, s.length() -1);
        String sa1 = s.substring(1, s.length() -1);
        Log.e(sa);
        Log.e(sa1);
    }

}
