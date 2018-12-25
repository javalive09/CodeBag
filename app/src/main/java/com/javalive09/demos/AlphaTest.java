package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by peter on 2018/12/25
 */
public class AlphaTest {

    @Run
    public void show(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.alpha);
    }

}
