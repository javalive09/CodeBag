package com.codebag.code.mycode.view.graphics;

import android.content.Context;
import android.view.View;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Invoker extends MyCode {

	public Invoker(MainActivity context) {
		super(context);
	}
	
	@Entry
	public void showXfer() {
		View view = new Xfermodes.SampleView(getActivity());
		showView(view);
	}
	
	@Entry
	public void showXferClip() {
		View view = new Xfermodes_clip.SampleView(getActivity());
		showView(view);
	}

}
