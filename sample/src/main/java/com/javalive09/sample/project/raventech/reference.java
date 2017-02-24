package com.javalive09.sample.project.raventech;

import android.util.Log;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * Created by peter on 2017/2/8.
 */

public class reference extends Entry{

    static final ReferenceQueue rq= new ReferenceQueue();

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Reference ref= rq.poll();
                    Log.i("ref", "-");
                    while(ref != null) {
                        LogUtil.e("ref = " + ref);
                        LogUtil.e("ref == " + ref.get());
                        return;
                    }
                }
            }
        }).start();
    }

    public void phantomReference() {
        A a = new A();
        a.s="hello";
        Reference r = new PhantomReference(a,rq);
        a = null;
        System.gc();
    }

    class A{
        String s;
    }

}
