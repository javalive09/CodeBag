package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;

public class CakeWaveView extends View {

	private Path aboveWavePath = new Path();
	private Paint aboveWavePaint = new Paint();
	private float mFloatSpeed = 1.28f;
	private int mUpDownSpeed = 11;
	private float mOffset = 0;
	private boolean mStartAnim = false;
	private int mDelay = 50;
	private int waveH = 20;
	private int mHeight = 0;
	private int mWaterH = 0;
	private int mEndWateH = 0;
	private RectF oval = new RectF();
	
	private int mPrecent;
	
	private Paint mPaint = new Paint(); 
	private RectF mBgRect = new RectF();
	
	Path mPath = new Path(); 

	public CakeWaveView(Context context) {
		super(context);
		initializePainters();
		setBackgroundColor(Color.WHITE);
		mPaint.setColor(Color.CYAN);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.save();
		
		
		mPath.reset();
		canvas.clipPath(mPath);
		mPath.addArc(mBgRect, 0, 360);
		canvas.clipPath(mPath, Region.Op.REPLACE); 
		
		
		canvas.drawArc(mBgRect, 0, 360, false, mPaint);
		
		
		
		calculatePath();
		canvas.drawPath(aboveWavePath, aboveWavePaint);

		
		canvas.restore(); 


		
	}

	private void initializePainters() {
		aboveWavePaint.setColor(Color.BLUE);
		aboveWavePaint.setStyle(Paint.Style.FILL);
		aboveWavePaint.setAntiAlias(true);
		aboveWavePaint.setAlpha(100);
	}

	float startX = 0;
	float waterW = 0;
	
	private void calculatePath() {

		aboveWavePath.reset();
		int right = getRight();
//		mWaterH = 600;
//		startX = getStartX();
//		startX = 100;

		float waterW = right - 2 * startX;
		//
		
//		float sdddd = (float) (Math.sin(startX * Math.PI * 2 / waterW)) * waveH + (mHeight - mWaterH);
		aboveWavePath.moveTo(0, mWaterH);
//		aboveWavePath.lineTo(0, mHeight - mWaterH);
		//
		for (float i = 0 + startX + mOffset; i <= right - startX + mOffset; i++) {
			aboveWavePath.lineTo((i - mOffset), (float) (Math.sin(i * Math.PI * 2 / waterW)) * waveH + (mHeight - mWaterH));
		}
//		aboveWavePath.lineTo(getRight(), getHeight());
//		
//		aboveWavePath.arcTo(oval, 0, 360 );
		
		aboveWavePath.lineTo(getRight(), getHeight());
		aboveWavePath.lineTo(0, getHeight());
		
		aboveWavePath.close();
		

	}
	
	public float getstartAngle() {
		float r = oval.right/2;
		float a = r - mWaterH; 
		double radian =  Math.asin(a/r);
		return (float) Math.toDegrees(radian);
	}
	
	public float getStartX() {
		float r = oval.right/2;
		float temp = mWaterH*(2*r - mWaterH);
		float x = (float) (r - Math.sqrt(temp));
		return x;
	}
	
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mStartAnim = true;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mHeight = getMeasuredHeight();
		mEndWateH = mHeight/ 100 * mPrecent;
		mWaterH = mHeight;
		int width = getMeasuredWidth();
		oval.set(0, mHeight - width, getRight(), mHeight);
		mBgRect.set(0, mHeight - width, getRight(), mHeight);
		reduceWaveHeight();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mStartAnim = false;
	}

	public void startAnim(int percent) {
		mPrecent = percent;
	}
	
	public void setProgress(int percent) {
		mPrecent = percent;
	}
	
	private void floatWave() {
		if(mOffset < Integer.MAX_VALUE - getWidth()) {
			mOffset += mFloatSpeed;
		}else {
			mOffset = 0;
		}
	}
	
	private void reduceWaveHeight() {
		if(mWaterH <= 0){
			mWaterH = 0;
			plusWaveHeight();
		}else if(mWaterH > 0) {
			postDelayed(reduceWaveRunnable, mDelay);
		}
	}
	
	private Runnable reduceWaveRunnable= new Runnable() {

		@Override
		public void run() {
			if(mStartAnim) {
				mWaterH -= mUpDownSpeed;
				reduceWaveHeight();
				invalidate();
			}
		}
		
	};
	
	private Runnable plusWaveRunnable = new Runnable() {
		@Override
		public void run() {
			if(mStartAnim) {
				 if(mWaterH < mEndWateH) {
					mWaterH += mUpDownSpeed;
				}else if(mWaterH > mEndWateH){
					mWaterH = mEndWateH;
				}else if(mWaterH == mEndWateH) {
					floatWave();
				}
				 
				plusWaveHeight();
				invalidate();
			}
		}
	};
	
	private void plusWaveHeight() {
		postDelayed(plusWaveRunnable, mDelay);
	}

}
