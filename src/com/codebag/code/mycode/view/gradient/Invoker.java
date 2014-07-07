package com.codebag.code.mycode.view.gradient;

import android.content.Context;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}

	@Entry()
	public void showGradient() {
		Gradient g = new Gradient(getContext());
		g.setGradient(0xffffffff, 0);
		showView(g);
	}
	
	@Entry()
	public void showGradientTwo() {
		GradientTwo g = new GradientTwo(getContext());
		g.setGradient(0xffffffff, 0);
		showView(g);
	}

}
