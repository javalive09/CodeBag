package com.codebag.code.mycode.test.arraylist;

import java.util.ArrayList;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.utils.Log;

public class AddIndex extends MyCode {

    ArrayList a = new ArrayList();
    
    public AddIndex(InovkedViewActivity act) {
        super(act);
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
    }
    
    /**
     * 增加item
     */
    @Entry
    public void addDiff() {
        a.add(0, "e");
        Log.addLog("AddIndex", this, a.toString());
    }
    
    @Entry
    public void addSame() {
        a.add(0, "a");
        Log.addLog("AddIndex", this, a.toString());
    }
    

}
