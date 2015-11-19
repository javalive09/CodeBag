package com.codebag.code.mycode.view.graphics;

import android.view.View;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoker extends MyCode {

	public Invoker(InovkedViewActivity context) {
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
	
	@Entry
	public void showCustomLine() {
		showView(R.layout.custom_line);
	}

}
