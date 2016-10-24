package com.javalive09.sample.view.graphics;

import android.view.View;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoker extends Entry {

    public void showXfer() {
        View view = new Xfermodes.SampleView(getViewActivity());
        showView(view);
    }

    public void showXferClip() {
        View view = new Xfermodes_clip.SampleView(getViewActivity());
        showView(view);
    }

    public void showCustomLine() {
        showView(R.layout.custom_line);
    }

}
