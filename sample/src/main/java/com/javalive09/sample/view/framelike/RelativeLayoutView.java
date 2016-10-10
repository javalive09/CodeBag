package com.javalive09.sample.view.framelike;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.RelativeLayout;

public class RelativeLayoutView extends RelativeLayout {

	public RelativeLayoutView(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
	
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}
	
}
