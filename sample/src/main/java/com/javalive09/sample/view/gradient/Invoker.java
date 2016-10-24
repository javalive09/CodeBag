package com.javalive09.sample.view.gradient;

import android.widget.FrameLayout;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoker extends Entry {

	public void showGradient() {
		Gradient g = new Gradient(getViewActivity());
		g.setGradient(0xffffffff, 0);
		showView(g);
	}

	public void showGradientShape() {
		FrameLayout fl = new FrameLayout(getViewActivity());
		fl.setBackgroundResource(R.drawable.gradient);
		showView(fl);
	}


}
