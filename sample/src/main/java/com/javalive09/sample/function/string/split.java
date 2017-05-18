package com.javalive09.sample.function.string;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

import java.util.StringTokenizer;

/**
 * Created by peter on 16/2/26.
 */
public class split extends Entry {

    public void split() {
        String str = "com.codebag.code.function.fragment.MyFragmentActivity";
        String [] s = str.split("\\.");
        Log.i("" + s[s.length - 1]);
    }

    public void token() {
        String str = "com.codebag.code.function.fragment.MyFragmentActivity";
        StringTokenizer token = new StringTokenizer(str, ".");
        while(token.hasMoreTokens()) {
            Log.i(token.nextToken());
        }
    }

}
