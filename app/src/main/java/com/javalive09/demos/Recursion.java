package com.javalive09.demos;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

@Code(name = "递归模型")
public class Recursion {

    @Run(name = "阶乘")
    public void factorial(CodeActivity activity) {
        int result = factorial(5);
        activity.showText("result = " + result);
    }

    private int factorial(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

}
