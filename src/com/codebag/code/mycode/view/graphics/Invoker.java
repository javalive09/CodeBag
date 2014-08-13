package com.codebag.code.mycode.view.graphics;

import android.content.Context;
import android.view.View;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void showXfer() {
		View view = new Xfermodes.SampleView(getContext());
		showView(view);
	}
	
	@Entry
	public void showXferClip() {
		View view = new Xfermodes_clip.SampleView(getContext());
		showView(view);
	}

}
