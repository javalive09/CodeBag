package com.codebag.code.mycode.z_StressTesting.a;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.bag.CaseListView;

public class Copy_2_of_Copy_2_of_Copy_2_of_j extends CaseListView {

	public Copy_2_of_Copy_2_of_Copy_2_of_j(Context context) {
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
