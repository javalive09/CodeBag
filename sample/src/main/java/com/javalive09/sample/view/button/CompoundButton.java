package com.javalive09.sample.view.button;

import android.widget.Button;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class CompoundButton extends Entry {

	public void button() {
		Button btn = new Button(getActivity());
		btn.setText("click me");
		showView(btn);
	}

	public void compoundButton() {
		showView(R.layout.compound_button);
	}
}
