package com.javalive09.sample.view.anim.pathanim;


import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class Chain extends View implements AnimatorUpdateListener{

	RectF ovalUp = new RectF();
	RectF ovalDown = new RectF();
	Paint paint = new Paint();
	Path path = new Path();
	RectF rect = new RectF();
	
//	int width = 36;
//	int height = 111;
//	int strokeW = 10;
	int width = 40;
	int height = 111;
	int strokeW = 10;
	int bottom = 0;
	
	public Chain(Context context) {
		super(context);
		ovalUp.set(strokeW /2, strokeW /2, width - strokeW /2, height / 2 - strokeW / 2); 
		ovalDown.set(strokeW /2, strokeW /2 + height / 2, width - strokeW / 2, height - strokeW / 2);
		rect.set(width / 2, height / 4, width / 2, height / 4 + height/2);
		
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setAntiAlias(true);  
		paint.setStrokeWidth(10);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.WHITE);
//		path.addArc(ovalUp, 120, 300);
//		path.addArc(ovalDown, -60, 300);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
		canvas.drawLine(width / 2, height / 4, width / 2, height / 4 + bottom, paint);
	}

	@Override
	public void onAnimationUpdate(ValueAnimator arg0) {
		int angle = (Integer) arg0.getAnimatedValue();
		
		if(angle <= 300) {
			path.addArc(ovalUp, 120, angle);
		}else if(angle <= 600) {
			path.addArc(ovalDown, 240 - (angle - 300), angle - 300);
			
			Log.i("peter", "circle angle =" + angle);
		}else if(angle <= 900) {
			bottom = height / 2 * (angle - 600) / 300;
		}
		
		invalidate();
	}
	
	

}
