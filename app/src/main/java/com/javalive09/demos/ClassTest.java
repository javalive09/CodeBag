package com.javalive09.demos;

import com.javalive09.codebag.CodeBag;
import com.javalive09.annotation.Tester;
import com.javalive09.annotation.Test;

/**
 * Class 测试
 */

@Tester(name = "Class对象(Java类型信息)", point = "equalClass")
public class ClassTest {

    /**
     * 前大后小， 前面的类是否可以从后面的类转换
     */
    @Test(name = "判断Class相等")
    public void equalClass() {
        boolean result = Object.class.isAssignableFrom(ClassTest.class);
        CodeBag.showText(result + "");
    }
}
