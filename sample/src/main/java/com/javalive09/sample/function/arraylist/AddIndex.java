package com.javalive09.sample.function.arraylist;

import java.util.ArrayList;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

public class AddIndex extends Entry {

    ArrayList a = new ArrayList();
    
    public AddIndex() {
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
    }
    
    /**
     * 增加item
     */
    public void addDiff() {
        a.add(0, "e");
        LogUtil.i(a.toString());
    }
    
    public void addSame() {
        a.add(0, "a");
        LogUtil.i(a.toString());
    }
    

}
