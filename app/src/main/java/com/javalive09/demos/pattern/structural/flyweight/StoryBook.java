package com.javalive09.demos.pattern.structural.flyweight;

import android.util.Log;

public class StoryBook implements Book {

    @Override
    public void read() {
        Log.i("peter", "read StoryBook!");
    }

}
