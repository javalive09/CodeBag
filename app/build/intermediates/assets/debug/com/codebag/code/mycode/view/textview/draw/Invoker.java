package com.codebag.code.mycode.view.textview.draw;

import android.graphics.Color;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoker extends MyCode {

	public Invoker(InovkedViewActivity context) {
		super(context);
	}
	
	@Entry
	public void show() {
		MyTextView mv = new MyTextView(getActivity());
		mv.setText("123");
		mv.setTextColor(Color.BLUE);
		showView(mv);
	}

}
