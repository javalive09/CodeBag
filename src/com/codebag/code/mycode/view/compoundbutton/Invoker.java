package com.codebag.code.mycode.view.compoundbutton;

import android.content.Context;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoker extends MyCode {

	public Invoker(InovkedViewActivity context) {
		super(context);
	}
	
	@Entry
	public void show() {
		showView(R.layout.compound_button);
	}

}
