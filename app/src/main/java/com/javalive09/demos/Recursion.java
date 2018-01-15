package com.javalive09.demos;

import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;

@Player(name = "递归模型")
public class Recursion {

    @Play(name = "阶乘")
    public void factorial() {
        int result = factorial(5);
        CaseActivity.showText("result = " + result);
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
