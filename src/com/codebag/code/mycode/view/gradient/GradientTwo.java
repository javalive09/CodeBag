package com.codebag.code.mycode.view.gradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.view.View;

public class GradientTwo extends View {

	int mStartColor;
	int mEndColor;
	Paint mPaint;
	
	public GradientTwo(Context context) {
		super(context);
		mPaint = new Paint();
	}
	
	public void setGradient(int startColor, int endColor) {
		mStartColor = startColor;
		mEndColor = endColor;
	}
 
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		LinearGradient lg = new LinearGradient(0, 0 , getMeasuredWidth(), getMeasuredHeight(), mStartColor, mEndColor, TileMode.MIRROR);
		mPaint.setShader(lg);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
	}
	

}
