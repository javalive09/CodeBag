package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class TestPathView extends View {

	public TestPathView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int r = 100;
		int center_X = 200;
		int center_Y = 200;
		Path path = new Path();
		RectF rectF = new RectF(center_X-r,center_Y-r,center_X+r,center_Y+r);
		
		path.addArc(rectF, 0, 60);
		
		canvas.clipPath(path);
		
	}
	
	

}
