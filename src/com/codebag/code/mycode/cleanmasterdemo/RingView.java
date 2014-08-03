package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class RingView extends View {

	private Paint mPaint; 
	private RectF mRect;
	private float mAngle;
	
	/**
	 * @param context
	 * @param startX    圆环左上角X坐标
	 * @param startY    圆环左上角Y坐标
	 * @param ply		圆环厚度
	 * @param diameter	圆环直径
	 */
	public RingView(Context context, int startX, int startY, int ply, int diameter) {
		super(context);
		mPaint = new Paint();
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(ply);
		mPaint.setAntiAlias(true);
		mRect = new RectF(startX + ply/2, startY + ply/2, startX + diameter , startY + diameter);
	}
	
	public void setColor(int color) {
		mPaint.setColor(color);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawArc(mRect, 270, mAngle, false, mPaint);
	}
	
	public void setProgress(int progress) {
		mAngle = 360 / 100f * progress;
		invalidate();
	}
	
	
	public void startAnimination(int endProgress) {
		decreaseAnimination(100, endProgress);
	}
	
	public void decreaseAnimination(final int currentProgress, final int endProgress) {
		postDelayed(new Runnable() {

			@Override
			public void run() {
				if(currentProgress > endProgress) {
					int progress = currentProgress-1;
					setProgress(progress);
					decreaseAnimination(progress, endProgress);
				}
			}
			
		}, 20);
		
	}

}
