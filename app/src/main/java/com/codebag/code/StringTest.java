package com.codebag.code;

import com.codebag.bag.Entry;
import com.codebag.bag.LogUtil;

/**
 * Created by peter on 16/9/13.
 */
public class StringTest extends Entry {

    public void textVideoProgressEnd() {
        int a = 1140000;
        int b = 1139861;

        int m = (int)(a * 1.0f / 10000);
        int n = (int)(b * 1.0f / 10000);

        LogUtil.e( "m =" + m + "; n=" + n);
    }
}
