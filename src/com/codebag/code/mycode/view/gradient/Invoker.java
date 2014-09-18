package com.codebag.code.mycode.view.gradient;

import android.content.Context;
import android.widget.FrameLayout;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Invoker extends MyCode {

	public Invoker(MainActivity context) {
		super(context);
	}

	@Entry()
	public void showGradient() {
		Gradient g = new Gradient(getActivity());
		g.setGradient(0xffffffff, 0);
		showView(g);
	}
	
	@Entry()
	public void showGradientTwo() {
		FrameLayout fl = new FrameLayout(getActivity());
		fl.setBackgroundResource(R.drawable.gradient);
		showView(fl);
	}

}
