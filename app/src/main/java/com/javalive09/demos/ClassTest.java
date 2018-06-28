package com.javalive09.demos;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

/**
 * Class 测试
 */

@Code(name = "Class对象(Java类型信息)", point = "equalClass")
public class ClassTest {

    /**
     * 前大后小， 前面的类是否可以从后面的类转换
     */
    @Run(name = "判断Class相等")
    public void equalClass(CodeActivity activity) {
        boolean result = Object.class.isAssignableFrom(ClassTest.class);
        activity.showText(String.valueOf(result));
    }
}
