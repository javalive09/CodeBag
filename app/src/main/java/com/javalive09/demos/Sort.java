package com.javalive09.demos;

import java.util.Arrays;
import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by peter on 2019/1/2
 */
public class Sort {

    private int[] arrays;

    private void init() {
        arrays = new int[2147483];
        for(int i = 0; i < 2147483; i++) {
            arrays[i] = i;
        }
    }

    @Run
    public void fastSort(CodeActivity activity) {
        init();
        long start = System.currentTimeMillis();
        Arrays.sort(arrays);
        long delta = System.currentTimeMillis() - start;
        activity.showText("delta = " + delta);
    }

}
