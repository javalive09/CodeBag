package com.javalive09.demos;

import java.io.IOException;
import java.net.HttpURLConnection;
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

    public void threadSort(CodeActivity activity) {
        init();

//        HttpURLConnection httpURLConnection = new HttpURLConnection() {
//            @Override
//            public void disconnect() {
//
//            }
//
//            @Override
//            public boolean usingProxy() {
//                return false;
//            }
//
//            @Override
//            public void connect() throws IOException {
//
//            }
//        };
//        httpURLConnection.setConnectTimeout(500);
//        httpURLConnection.setReadTimeout(500);
//        httpURLConnection.setChunkedStreamingMode(0);
    }


}
