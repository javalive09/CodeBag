package com.codebag.code.mycode.view.expandablelisview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class Cell extends LinearLayout {
	public Cell(Context context) {
		super(context);
	}

	public Cell(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
	
}
