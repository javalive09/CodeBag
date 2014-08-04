package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.cleanmasterdemo.RingView.AniminationListener;
import com.codebag.code.mycode.utils.Log;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void showCircle() {
		RingView view = new RingView(getContext(), 80, 300);
		view.setColor(0xE624a0ff, 0x19000000);
		view.setBackgroundColor(Color.WHITE);
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
	
	@Entry
	public void showCleanDial() {
		CleanDial clean = new CleanDial(getContext());
		clean.setDialMarkResource(R.drawable.memery);
		clean.setRoatingBg(R.drawable.fan);
		clean.start();
		
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-2, -2);
		clean.setLayoutParams(params);
		showView(clean);
	}

}
