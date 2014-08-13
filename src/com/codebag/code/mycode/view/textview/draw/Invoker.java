package com.codebag.code.mycode.view.textview.draw;

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
		MyTextView mv = new MyTextView(getContext());
		mv.setText("123");
		mv.setTextColor(Color.BLUE);
		showView(mv);
	}

}
