package com.codebag.code.mycode.view.expandablelisview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Cell3 extends LinearLayout {
	public Cell3(Context context) {
		super(context);
	}

	public Cell3(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}
	
	public ViewGroup.LayoutParams getLayoutParams() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		params.weight = 1;
        return params;
    }
}
