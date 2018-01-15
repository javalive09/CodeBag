package com.javalive09.demos;

import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Player;
import com.javalive09.codebag.Play;

/**
 * Class 测试
 */

@Player(name = "Class对象(Java类型信息)", point = "equalClass")
public class ClassTest {

    /**
     * 前大后小， 前面的类是否可以从后面的类转换
     */
    @Play(name = "判断Class相等")
    public void equalClass() {
        boolean result = Object.class.isAssignableFrom(ClassTest.class);
        CaseActivity.showText(result + "");
    }
}
