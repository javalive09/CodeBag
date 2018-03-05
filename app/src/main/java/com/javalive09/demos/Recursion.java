package com.javalive09.demos;

import com.javalive09.codebag.CodeBag;
import com.javalive09.codebag.Test;
import com.javalive09.codebag.Tester;

@Tester(name = "递归模型")
public class Recursion {

    @Test(name = "阶乘")
    public void factorial() {
        int result = factorial(5);
        CodeBag.showText("result = " + result);
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
