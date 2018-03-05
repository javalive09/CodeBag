package com.javalive09.demos;

import com.javalive09.codebag.CodeBag;
import com.javalive09.codebag.annotation.Tester;
import com.javalive09.codebag.annotation.Test;

import java.util.ArrayList;

/**
 * List的一些特性测试
 */
@Tester(name = "List特性")
public class ListTest {

    @Test(name = "addAll之后原来元素的index")
    public void addAllIndex() {
        ArrayList arrayList = new ArrayList();
        Object o = new Object();
        arrayList.add(o);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Object());
        arrayList2.add(new Object());
        arrayList2.add(new Object());
        arrayList.addAll(arrayList2);

        int index = arrayList.indexOf(o);
        CodeBag.showText(index + "");
    }

    @Test(name = "add不同元素在0位置上")
    public void AddDifferentObject() {
        ArrayList a = new ArrayList();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add(0, "e");
        CodeBag.showText(a.toString());
    }

    @Test(name = "add相同元素在0位置上")
    public void AddSameObject() {
        ArrayList a = new ArrayList();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add(0, "a");
        CodeBag.showText(a.toString());
    }

}
