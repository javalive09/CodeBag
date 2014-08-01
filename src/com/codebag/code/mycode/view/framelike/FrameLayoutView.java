package com.codebag.code.mycode.view.framelike;

import com.codebag.code.mycode.utils.Log;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;

public class FrameLayoutView extends FrameLayout {

	public FrameLayoutView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.startCountTime(this);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.endCountTime(this);
	}

	@Override
	public void draw(Canvas canvas) {
		Log.startCountTime(this);
		super.draw(canvas);
		Log.endCountTime(this);
	}
	

}
