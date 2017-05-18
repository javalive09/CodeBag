package com.javalive09.sample.project.xui.progress;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.javalive09.sample.R;

public class XuiCircularProgressBar extends View {

	private int mCircleStrokeWidth;

	private float mProgress;

	private int mProgressColor;

	private Paint mProgressColorPaint;

	private float mInnerRadius;

	private int mOuterRadius;

	private Paint mThumbColorPaint;

	private boolean mThumbVisiable = true;
	
	private Bitmap mBitmap;

	private int mCell;

	public XuiCircularProgressBar(final Context context) {
		this(context, null);
	}

	public XuiCircularProgressBar(final Context context,
			final AttributeSet attrs) {
		this(context, attrs, R.attr.circularProgressBarStyle);

	}

	public XuiCircularProgressBar(final Context context,
			final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);

		final TypedArray attributes = context.obtainStyledAttributes(attrs,
				R.styleable.XuiCircularProgressBar, defStyle, 0);
		if (attributes != null) {
			try {
				setProgressColor(attributes.getColor(
						R.styleable.XuiCircularProgressBar_progress_color,
						Color.CYAN));
				setProgress(attributes.getFloat(
						R.styleable.XuiCircularProgressBar_progress, 0.0f));
				setThumbVisiable(attributes
						.getBoolean(
								R.styleable.XuiCircularProgressBar_thumb_visible,
								true));
				setCircleStrockWidth(attributes.getDimensionPixelSize(
						R.styleable.XuiCircularProgressBar_stroke_width, 1));
				setRadius(attributes.getDimensionPixelSize(
						R.styleable.XuiCircularProgressBar_progress_radius,
						100));
			} finally {
				attributes.recycle();
			}
		}
		updateProgressColor();

	}

	Runnable mRunnable = new Runnable() {

		@Override
		public void run() {
			float progress = mProgress;
			progress += 0.05f;
			if (progress >= 1.0f) {
				progress = 0f;
			}
			setProgress(progress);
			postDelayed(mRunnable, 50);
		}
	};

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		removeCallbacks(mRunnable);
	}

	public void startAnimination() {
		ObjectAnimator mRadiusAnimator = ObjectAnimator.ofFloat(this, "radius", 20.0f, 344f);
		mRadiusAnimator.setDuration(1000);
		mRadiusAnimator.setInterpolator(new BounceInterpolator());
		mRadiusAnimator.start();
	}

	private float getCurrentRotation() {
		return mProgress * 360;
	}

	public float getProgress() {
		return mProgress;
	}

	public int getProgressColor() {
		return mProgressColor;
	}

	public void setCircleStrockWidth(int strokeWidth) {
		mCircleStrokeWidth = strokeWidth;
	}

	public void setRadius(float radius) {
		mInnerRadius = radius;
		invalidate();
	}

	@Override
	protected void onDraw(final Canvas canvas) {
		canvas.drawCircle(mOuterRadius, mOuterRadius, mInnerRadius,
				mProgressColorPaint);
		canvas.translate(mOuterRadius, mOuterRadius);
		if (mThumbVisiable) {
			final float progressRotation = getCurrentRotation();
			canvas.save();
			canvas.rotate(progressRotation);
			canvas.drawBitmap(mBitmap, -mCell * 0.5f, mOuterRadius - mCell,
					mThumbColorPaint);
			canvas.restore();
		}
	}

	public void setThumbVisiable(boolean visiable) {
		mThumbVisiable = visiable;
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec,
			final int heightMeasureSpec) {
		int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
		int diameter = Math.min(width, height);
//		mInnerRadius = diameter * 0.5f;
		int outerDiameter = diameter + mCell;
		mOuterRadius = (int) (outerDiameter * 0.5f);
		setMeasuredDimension(outerDiameter, outerDiameter);
	}

	public void setProgress(float progress) {
		if (progress == mProgress) {
			return;
		}

		if (progress == 1) {
			mProgress = 1;
		} else {
			mProgress = progress % 1.0f;
		}

		invalidate();
	}

	public void setProgressColor(final int color) {
		mProgressColor = color;
		updateProgressColor();
	}

	private void updateProgressColor() {
		mBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.progress_point);
		mCell = Math.max(mBitmap.getWidth(), mBitmap.getHeight());
		setBackgroundColor(Color.BLACK);
		mProgressColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mProgressColorPaint.setColor(mProgressColor);
		mProgressColorPaint.setStyle(Paint.Style.STROKE);
		mProgressColorPaint.setStrokeWidth(mCircleStrokeWidth);
		mProgressColorPaint.setAntiAlias(true);

		mThumbColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mThumbColorPaint.setColor(mProgressColor);
		mThumbColorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mThumbColorPaint.setStrokeWidth(mCircleStrokeWidth);
		mThumbColorPaint.setAntiAlias(true);
		invalidate();
	}

}
