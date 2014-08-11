package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;

public class CakeWaveView extends View {

	private static final float FLOAT_SPEED = 1.28f;
	private static final int UPDOWN_SPEED = 11;
	private static final int DELAY = 50;
	private float mWaveHRate = 0.04f;//浪高和View高度的比率
	private Paint mAboveWavePaint;
	private Path mWavePath;
	private Path mClipPath;
	private int mWaveH;
	private float mOffset;
	private boolean mCanAnim;
	private int mHeight;
	private int mWaterH;
	private int mEndWaterH;
	private RectF mCircleRect;
	private Paint mCirclePaint;
	private int mPrecent;

	public CakeWaveView(Context context) {
		super(context);
		initData();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.save();
		clipCircle(canvas);
		canvas.drawArc(mCircleRect, 0, 360, false, mCirclePaint);
		drawWave(canvas);
		canvas.restore();
	}

	private void initData() {
		mWavePath = new Path();
		mAboveWavePaint = new Paint();
		mCirclePaint = new Paint();
		mCircleRect = new RectF();
		mClipPath = new Path();
		
		mAboveWavePaint.setColor(Color.BLUE);
		mAboveWavePaint.setStyle(Paint.Style.FILL);
		mAboveWavePaint.setAntiAlias(true);
		mAboveWavePaint.setAlpha(100);
		
		setBackgroundColor(Color.WHITE);
		mCirclePaint.setColor(Color.CYAN);
	}

	private void clipCircle(Canvas canvas) {
		mClipPath.reset();
		canvas.clipPath(mClipPath);
		mClipPath.addArc(mCircleRect, 0, 360);
		canvas.clipPath(mClipPath, Region.Op.REPLACE);
	}

	private void drawWave(Canvas canvas) {
		mWavePath.reset();
		int right = getRight();
		mWavePath.moveTo(0, mWaterH);
		for (float i = 0 + mOffset; i <= right + mOffset; i++) {
			mWavePath.lineTo((i - mOffset),
					(float) (Math.sin(i * Math.PI * 2 / right)) * mWaveH
							+ (mHeight - mWaterH));
		}
		mWavePath.lineTo(getRight(), getHeight());
		mWavePath.lineTo(0, getHeight());
		mWavePath.close();
		canvas.drawPath(mWavePath, mAboveWavePaint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mHeight = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(mHeight, mHeight);
		mEndWaterH = mHeight / 100 * mPrecent;
		mWaterH = mHeight;
		mWaveH = (int) (mHeight * mWaveHRate);
		mCircleRect.set(0, 0, mHeight, mHeight);
		reduceHeight();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mCanAnim = false;
	}

	public void startAnim(int percent) {
		mCanAnim = true;
		mPrecent = percent;
	}

	public void setProgress(int percent) {
		mPrecent = percent;
	}

	private void floatWave() {
		if (mOffset < Integer.MAX_VALUE - getWidth()) {
			mOffset += FLOAT_SPEED;
		} else {
			mOffset = 0;
		}
	}

	private void reduceHeight() {
		if (mCanAnim) {
			if (mWaterH <= 0) {
				mWaterH = 0;
				plusHeightOrFloat();
			} else if (mWaterH > 0) {
				postDelayed(reduceHeightRunnable, DELAY);
			}
		}
	}

	private Runnable reduceHeightRunnable = new Runnable() {
		@Override
		public void run() {
			mWaterH -= UPDOWN_SPEED;
			reduceHeight();
			invalidate();
		}
	};

	private Runnable plusOrFloatRunnable = new Runnable() {
		@Override
		public void run() {
			if (mWaterH < mEndWaterH) {
				mWaterH += UPDOWN_SPEED;
			} else if (mWaterH > mEndWaterH) {
				mWaterH = mEndWaterH;
			} else if (mWaterH == mEndWaterH) {
				floatWave();
			}
			plusHeightOrFloat();
			invalidate();
		}
	};

	private void plusHeightOrFloat() {
		if (mCanAnim) {
			postDelayed(plusOrFloatRunnable, DELAY);
		}
	}

}
