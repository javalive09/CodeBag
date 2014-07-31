package com.codebag.code.mycode.view.framelike;

import com.codebag.code.mycode.utils.Log;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.LinearLayout;

public class LinearLayoutView extends LinearLayout {

	public LinearLayoutView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public void draw(Canvas canvas) {
		Log.startCountTime(this);
		super.draw(canvas);
		Log.endCountTime(this);
	}
	

}
