package com.codebag.code.mycode.view.framelike;

import com.codebag.code.mycode.utils.Log;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.RelativeLayout;

public class RelativeLayoutView extends RelativeLayout {

	public RelativeLayoutView(Context context) {
		super(context);
	}
	
	int totalCost = 0;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.startCountTime(this, "onMeasure");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		totalCost +=Log.endCountTime(this , "onMeasure");
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		Log.startCountTime(this, "onLayout");
		super.onLayout(changed, left, top, right, bottom);
		totalCost += Log.endCountTime(this , "onLayout");
	}
	
	protected void dispatchDraw(Canvas canvas) {
		Log.startCountTime(this, "dispatchDraw");
		super.dispatchDraw(canvas);
		totalCost += Log.endCountTime(this, "dispatchDraw");
		Log.addLog("peter", this, "totalCost = " + totalCost);
	}
	
}
