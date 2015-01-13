package com.codebag.code.mycode.pattern.structural.flyweight;

import android.util.Log;

public class TechnologyBook implements Book {

    @Override
    public void read() {
        Log.i("peter", "read TechnologyBook!");
    }

}
