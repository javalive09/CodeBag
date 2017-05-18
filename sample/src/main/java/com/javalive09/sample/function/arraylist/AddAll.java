package com.javalive09.sample.function.arraylist;

import com.javalive09.codebag.Entry;

import java.util.ArrayList;

/**
 * Created by peter on 2017/5/18.
 */

public class AddAll extends Entry {

    public void v() {

        ArrayList arrayList = new ArrayList();
        Object o = new Object();
        arrayList.add(o);

        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Object());
        arrayList2.add(new Object());
        arrayList2.add(new Object());

        arrayList.addAll(arrayList2);

        int index = arrayList.indexOf(o);
        showTxt(index + "");

    }

}
