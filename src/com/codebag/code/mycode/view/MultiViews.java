package com.codebag.code.mycode.view;

import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class MultiViews extends LinearLayout{

	private int mColumnCount = 1;
	
	public MultiViews(Context context) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
	}
	
	
	public MultiViews(Context context, int columnCount) {
		this(context);
		mColumnCount = columnCount;
	}
	
	public void setAdapter(MyAdapter adapter) {
		
		int count = adapter.getCount();
		
		int row = count / mColumnCount;
		
		if(row == 0) {
			row = 1;
		}
		
		HashMap<Integer, LinearLayout> rowContainer = new HashMap<Integer, LinearLayout>(row);
		
		LinearLayout.LayoutParams params_view = new LinearLayout.LayoutParams(0, -2);
		params_view.weight = 1;
		
		for(int i = 0; i < row + 1; i++) {
			LinearLayout rowView = new LinearLayout(getContext());
			rowContainer.put(i, rowView);
			addView(rowView);
		}
		
		int rowIndex = -1;
		
		for(int i = 0; i < count; i++) {
			int index = i / mColumnCount;
			
			if(index > rowIndex) {
				rowIndex = index;
			}
			
			View v = adapter.getView(i);
			v.setId(i);
			rowContainer.get(index).addView(v, params_view);
		}
	}
	
	public static interface MyAdapter {
		public int getCount();
		public View getView(int position);
	}

}
