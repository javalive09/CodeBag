package com.javalive09.sample.function.arraylist;

import java.util.ArrayList;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

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
        Log.i(a.toString());
    }
    
    public void addSame() {
        a.add(0, "a");
        Log.i(a.toString());
    }
    

}
