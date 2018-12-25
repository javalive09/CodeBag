package com.javalive09.demos;

import org.joor.Reflect;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by peter on 2018/12/10
 */
public class Joor {

    @Run(name = "静态内部类属性")
    public void getFile(CodeActivity codeActivity) {
        String zenMode = Reflect.on("com.javalive09.demos.Reflection$InnerClass").get("ZEN_MODE");
        codeActivity.showText(zenMode);
    }

}
