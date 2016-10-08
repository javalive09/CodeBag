package com.javalive09.sample.view.framelike;

import com.javalive09.codebag.LogUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;

public class FrameLayoutView extends FrameLayout {

	public FrameLayoutView(Context context) {
		super(context);
	}
	
	int totalCost = 0;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		LogUtil.startCountTime( "onMeasure");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		totalCost +=LogUtil.endCountTime( "onMeasure");
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		LogUtil.startCountTime( "onLayout");
		super.onLayout(changed, left, top, right, bottom);
		totalCost += LogUtil.endCountTime( "onLayout");
	}
	
	protected void dispatchDraw(Canvas canvas) {
		LogUtil.startCountTime( "dispatchDraw");
		super.dispatchDraw(canvas);
		totalCost += LogUtil.endCountTime( "dispatchDraw");
		LogUtil.i( "totalCost = " + totalCost);
	}
	

}
