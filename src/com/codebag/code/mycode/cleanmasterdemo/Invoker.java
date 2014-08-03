package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.graphics.Color;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void show() {
		RingView view = new RingView(getContext(), 0 , 300 , 30 , 500);
		view.setColor(Color.BLUE);
		view.setBackgroundColor(Color.BLACK);
		view.startAnimination(10);
		showView(view);
	}

}
