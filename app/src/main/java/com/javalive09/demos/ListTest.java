package com.javalive09.demos;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

import java.util.ArrayList;
import java.util.HashMap;

import android.animation.ObjectAnimator;
import android.util.Log;

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

    HashMap<String, Object> hashMap = new HashMap<>();


    @Run(name = "hashmap delete value")
    public void deleteSameValue(CodeActivity activity) {

        Object o = new Object();

        hashMap.put("a", o);
        hashMap.put("b", o);
        hashMap.put("c", o);

        Log.i("peter", hashMap.toString());


        hashMap.remove("a");
        Log.i("peter", hashMap.toString());

        hashMap.remove("b");
        Log.i("peter", hashMap.toString());

        hashMap.remove("c");
        Log.i("peter", hashMap.toString());
    }

}
