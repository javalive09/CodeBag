package com.codebag.code.mycode.view.textview.draw;

import android.graphics.Color;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Invoker extends MyCode {

	public Invoker(MainActivity context) {
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
