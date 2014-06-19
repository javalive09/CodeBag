package com.codebag.code.mycode.z_StressTesting.b;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.bag.CaseListView;

public class Copy_3_of_CopyOfCopy_2_of_a extends CaseListView {

	public Copy_3_of_CopyOfCopy_2_of_a(Context context) {
		super(context);
	}

	public void functionA() {
		Toast.makeText(getContext(), "functionA", Toast.LENGTH_SHORT).show();
	}
	
	public void functionB() {
		Toast.makeText(getContext(), "functionB", Toast.LENGTH_SHORT).show();
	}
	
	public void functionC() {
		Toast.makeText(getContext(), "functionB", Toast.LENGTH_SHORT).show();
		TextView v = new TextView(getContext());
		v.setText("functionC");
		v.setTextColor(Color.BLUE);
		v.setTextSize(30);
		showView(v);
	}
}
