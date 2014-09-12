package com.codebag.code.mycode.view.compoundbutton;

import android.content.Context;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void show() {
		showView(R.layout.compound_button);
	}

}
