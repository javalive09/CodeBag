package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.graphics.Color;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.cleanmasterdemo.RingView.AniminationListener;
import com.codebag.code.mycode.utils.Log;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void show() {
		RingView view = new RingView(getContext(), 0 , 300 , 30 , 500);
		view.setColor(Color.BLUE);
		view.setBackgroundColor(Color.BLACK);
		view.setAniminationListener(new AniminationListener() {
			
			@Override
			public void start() {
				Log.addLog(this, "start ----");
			}
			
			@Override
			public void end() {
				Log.addLog(this, "end ----");
			}
		});
		view.startAnimination(0);
		showView(view);
	}

}
