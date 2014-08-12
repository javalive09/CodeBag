package com.codebag.code.mycode.cleanmasteranim_wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class CakeWaveView extends ImageView {

	private static final float FLOAT_SPEED = 1.28f;
	private static final int DEFAULT_SPEED = 11;
	private static final int DELAY = 50;
	private float mWaveHRate = 0.02f;//浪高和View高度的比率
	private Paint mUpWavePaint;
	private Paint mDownWavePaint;
	private Path mWavePath;
	private Path mClipPath;
	private int mWaveH;
	private float mOffset;
	private boolean mUpDownAnim;
	private boolean mFloatAnim;
	private int mDiameter;
	private int mWaterH;
	private int mEndWaterH;
	private RectF mCircleRect;
	private Paint mCirclePaint;
	private int mUpDownSpeed;
	private AniminationListener mListener;

	public CakeWaveView(Context context) {
		this(context, 0, DEFAULT_SPEED);
	}
	
    public CakeWaveView(Context context, AttributeSet attrs) {
    	super(context, attrs);
    	init(0, DEFAULT_SPEED);
    }
	
	public CakeWaveView(Context context, int diameter, int speed) {
		super(context);
		init(diameter, speed);
	}

	private void init(int diameter, int speed) {
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		mWavePath = new Path();
		mUpWavePaint = new Paint();
		mDownWavePaint = new Paint();
		mCirclePaint = new Paint();
		mCircleRect = new RectF();
		mClipPath = new Path();
		mUpWavePaint.setStyle(Paint.Style.FILL);
		mUpWavePaint.setAntiAlias(true);
		mDownWavePaint.setStyle(Paint.Style.FILL);
		mDownWavePaint.setAntiAlias(true);
		setData(diameter, speed);
	}
	
	public void setData(int diameter, int upDownspeed) {
		mDiameter = diameter;
		mWaveH = (int) (mDiameter * mWaveHRate);
		mCircleRect.set(0, 0, mDiameter, mDiameter);
		if (upDownspeed >= 1 && upDownspeed <= 11) {
			mUpDownSpeed = upDownspeed;
		}
	}
	
	public void setColor(int waveColor, int cirCleColor) {
		mUpWavePaint.setColor(waveColor);
		mDownWavePaint.setColor(waveColor);
		mCirclePaint.setColor(cirCleColor);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		clipCircle(canvas);
		canvas.drawArc(mCircleRect, 0, 360, false, mCirclePaint);
		drawWave(canvas);
		canvas.restore();
	}

	private void clipCircle(Canvas canvas) {
		mClipPath.reset();
		canvas.clipPath(mClipPath);
		mClipPath.addArc(mCircleRect, 0, 360);
		canvas.clipPath(mClipPath, Region.Op.REPLACE);
	}

	private void drawWave(Canvas canvas) {
		//下层波浪
		mWavePath.reset();
		mWavePath.moveTo(0, mWaterH);
		for (float i = 0 + mOffset; i <= mDiameter + mOffset; i++) {
			mWavePath.lineTo((i - mOffset),
					(float) (Math.sin(i * Math.PI * 2 / mDiameter)) * mWaveH
							+ (mDiameter - mWaterH));
		}
		mWavePath.lineTo(getRight(), getHeight());
		mWavePath.lineTo(0, getHeight());
		mWavePath.close();
		canvas.drawPath(mWavePath, mDownWavePaint);
		
		//上层波浪
		mWavePath.reset();
		mWavePath.moveTo(0, mWaterH);
		for (float i = 0 + mOffset; i <= mDiameter + mOffset; i++) {
			mWavePath.lineTo((i - mOffset),
					(float) (Math.sin(i * Math.PI * 2 / mDiameter)) * (mWaveH * 3)
							+ (mDiameter - (mWaterH)));
		}
		mWavePath.lineTo(getRight(), getHeight());
		mWavePath.lineTo(0, getHeight());
		mWavePath.close();
		canvas.drawPath(mWavePath, mUpWavePaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mDiameter, mDiameter);
	}

	@Override
	protected void onDetachedFromWindow() {
		mUpDownAnim = false;
		mFloatAnim = false;
		super.onDetachedFromWindow();
	}

	public void startAnimination(int percent) {
		mUpDownAnim = true;
		mFloatAnim = true;
		mEndWaterH = mDiameter * percent / 100;
		mWaterH = mDiameter;
		reduceHeight();
		if(mListener != null) {
			mListener.start();
		} 
	}

	public void setProgress(int percent) {
		mUpDownAnim = false;
		mFloatAnim = true;
		mEndWaterH = mDiameter * percent / 100;
		mWaterH = mEndWaterH;
		floatWave();
	}

	private void floatWave() {
		if (mFloatAnim) {
			postDelayed(floatWaveRunnable, DELAY);
		}
	}
	
	public void cancleAnim() {
		mFloatAnim = false;
		mUpDownAnim = false;
	}

	private void reduceHeight() {
		if (mUpDownAnim) {
			if (mWaterH <= 0) {
				mWaterH = 0;
				increaseHeight();
			} else if (mWaterH > 0) {
				postDelayed(reduceHeightRunnable, DELAY);
			}
		}
	}
	
	private void increaseHeight() {
		if (mUpDownAnim) {
			postDelayed(increaseHeightRunnable, DELAY);
		}
	}

	private Runnable reduceHeightRunnable = new Runnable() {
		@Override
		public void run() {
			mWaterH -= mUpDownSpeed;
			reduceHeight();
			invalidate();
		}
	};

	private Runnable increaseHeightRunnable = new Runnable() {
		@Override
		public void run() {
			if (mWaterH < mEndWaterH) {
				mWaterH += mUpDownSpeed;
				increaseHeight();
				invalidate();
			} else if (mWaterH > mEndWaterH) {
				mWaterH = mEndWaterH;
				increaseHeight();
				invalidate();
			}
			
			if (mWaterH == mEndWaterH) {
				floatWave();
				if(mListener != null) {
					mListener.end();
				}
			}
		}
	};

	private Runnable floatWaveRunnable = new Runnable() {
		@Override
		public void run() {
			if (mOffset < Integer.MAX_VALUE - mDiameter) {
				mOffset += FLOAT_SPEED;
			} else {
				mOffset = 0;
			}
			floatWave();
			invalidate();
		}
	};
	
	public interface AniminationListener {
		public void start();
		public void end();
	}

}
