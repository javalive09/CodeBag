
package com.codebag.code.mycode.test;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Example extends CaseListView {

	public Example(Context context) {
		super(context);
	}

	@Entry()
	public void functionA() {
		Toast.makeText(getContext(), "functionA", Toast.LENGTH_SHORT).show();
	}
	
	@Entry()
	public void functionB() {
		Toast.makeText(getContext(), "functionB", Toast.LENGTH_SHORT).show();
	}
	
	@Entry()
	public void functionC() {
		Toast.makeText(getContext(), "functionB", Toast.LENGTH_SHORT).show();
		TextView v = new TextView(getContext());
		v.setText("functionC");
		v.setTextColor(Color.BLUE);
		v.setTextSize(30);
		popWindowView(v);
	}
}

