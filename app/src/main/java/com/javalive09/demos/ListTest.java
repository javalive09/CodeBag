package com.javalive09.demos;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

import java.util.ArrayList;

/**
 * List的一些特性测试
 */
@Code(name = "List")
public class ListTest {

    @Run(name = "addAll之后,原来元素的index(不变)")
    public void addAllIndex(CodeActivity activity) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Object o = new Object();
        arrayList.add(o);
        ArrayList<Object> arrayList2 = new ArrayList<>();
        arrayList2.add(new Object());
        arrayList2.add(new Object());
        arrayList2.add(new Object());
        arrayList.addAll(arrayList2);

        int index = arrayList.indexOf(o);
        activity.showText(index + "");
    }

    @Run(name = "add不同元素在0位置上(旧元素整体后移)")
    public void AddDifferentObject(CodeActivity activity) {
        ArrayList<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add(0, "e");
        activity.showText(a.toString());
    }

    @Run(name = "add相同元素在0位置上")
    public void AddSameObjectPositionZero(CodeActivity activity) {
        ArrayList a = new ArrayList();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add(0, "a");
        activity.showText(a.toString());
    }

    @Run(name = "add相同元素")
    public void AddSameObject(CodeActivity activity) {
        ArrayList<String> a = new ArrayList<>();
        String str = "a";
        a.add(str);
        a.add(str);
        a.add(str);
        a.add(str);
        activity.showText(a.toString());
    }

}
