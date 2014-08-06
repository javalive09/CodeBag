package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.graphics.Color;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
		clean= new CleanDial(context);
		clean.setBackgroundColor(Color.WHITE);
	}
	
	CleanDial clean;
	
	@Entry
	public void setDialMarkResource() {
		clean.setDialMarkResource(R.drawable.memery);
		showView(clean);
	}
	
	@Entry
	public void showProgress() {
		clean.setProgress(85);
		showView(clean);
	}
	
	@Entry
	public void showRoket() {
		clean.showRoket();
		showView(clean);
	}
	
	@Entry
	public void showMagnifier() {
		clean.showMagnifier();
		showView(clean);
	}
	
	@Entry
	public void showCleanBrush() {
		clean.setDialMarkResource(R.drawable.memery);
		clean.showBrush();
		showView(clean);
	}

}
