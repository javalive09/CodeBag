
package com.codebag.code.mycode.test;

import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Example extends MyCode {

	public Example(MainActivity context) {
		super(context);
	}

	@Entry()
	public void functionA() {
		Toast.makeText(getActivity(), "functionA", Toast.LENGTH_SHORT).show();
	}
	
	@Entry()
	public void functionB() {
		Toast.makeText(getActivity(), "functionB", Toast.LENGTH_SHORT).show();
	}
	
	@Entry()
	public void functionC() {
		Toast.makeText(getActivity(), "functionB", Toast.LENGTH_SHORT).show();
		TextView v = new TextView(getActivity());
		v.setText("functionC");
		v.setTextColor(Color.BLUE);
		v.setTextSize(30);
		showView(v);
	}
	
}

