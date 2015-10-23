package com.codebag.code.mycode.view.button;

import android.widget.Button;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
		super(act);
	}

	@Entry
	public void show() {
		Button btn = new Button(getActivity());
		btn.setText("click me");
		showView(btn);
	}
}
