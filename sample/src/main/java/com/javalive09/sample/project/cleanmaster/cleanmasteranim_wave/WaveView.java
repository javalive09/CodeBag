package com.javalive09.sample.project.cleanmaster.cleanmasteranim_wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class WaveView extends View {

	private Path aboveWavePath = new Path();
	private Paint aboveWavePaint = new Paint();
	private float mFloatSpeed = 1.28f;
	private int distance = 50;
	private int mUpDownSpeed = 11;
	private float mOffset = 0;
	private boolean mStartAnim = false;
	private int mDelay = 50;
	private int waveH = 20;
	private int mHeight = 0;
	private int mWaterH = 0;
	private int mEndWateH = 0;
	
	private int mPrecent;

	
	public WaveView(Context context) {
		super(context);
		initializePainters();
		setBackgroundColor(Color.WHITE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		calculatePath();
		canvas.drawPath(aboveWavePath, aboveWavePaint);

	}

	private void initializePainters() {
		aboveWavePaint.setColor(Color.BLUE);
		aboveWavePaint.setStyle(Paint.Style.FILL);
		aboveWavePaint.setAntiAlias(true);
		aboveWavePaint.setAlpha(100);
	}

	private void calculatePath() {

		aboveWavePath.reset();
		int right = getRight();
		//
		aboveWavePath.moveTo(0, mHeight);
		aboveWavePath.lineTo(0, mHeight - mWaterH);
		//
		for (float i = 0 + mOffset; i <= right + mOffset; i++) {
			aboveWavePath.lineTo((i - mOffset), (float) (Math.sin((i + distance) * Math.PI * 2 / right)) * waveH + (mHeight - mWaterH));
		}
		aboveWavePath.lineTo(getRight(), getHeight());
		aboveWavePath.close();

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
